package com.automation.ui.base.common.api.clientimpl.apacheimpl;

import com.automation.ui.base.common.api.util.MethodType;
import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.logging.Log;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.CloseableHttpClient;
import com.automation.ui.base.common.api.clientimpl.apacheimpl.secure.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.*;
import org.apache.http.*;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.entity.StringEntity;
import org.openqa.selenium.WebDriverException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
public abstract class ApiCall {

    protected static String url = null;

    private CloseableHttpClient httpClient=null;

    private MethodType httpVerb;

    private HttpRequestBase httpRequest;

    private HttpResponse response;

    private static String ERROR_MESSAGE = "Problem with API call";

    protected ApiCall() {
    }

    /**
     * Return null if API call doesn't require to be logged in as specific user
     *
     * @return User to be logged in while executing API call
     */
    abstract protected User getUser();

    /**
     * Return null when no params should be added to API call
     *
     * @return params
     */
    abstract protected ArrayList<BasicNameValuePair> getParams();

    /**
     * This enables passing username as string instead from ENUM.
     *
     * @return Username to log in.
     */
    abstract protected String getUserName();

    private void setRequestType(HttpRequestOptions options) {
        this.url = options.url;
        this.httpVerb = options.httpVerb;

        this.httpClient = createHttpClient(options.ignoreCert);

        switch (this.httpVerb) {
            case GET:
                this.httpRequest = new HttpGet(url);
                break;
            case HEAD:
                this.httpRequest = new HttpHead(url);
                break;
            case OPTIONS:
                this.httpRequest = new HttpOptions(url);
                break;
            case PATCH:
                this.httpRequest = new HttpPatch(url);
                break;
            case POST:
                this.httpRequest = new HttpPost(url);
                break;
            case PUT:
                this.httpRequest = new HttpPut(url);
                break;
            case DELETE:
                this.httpRequest = new HttpDelete(url);
                break;
            case DELETE_WITH_BODY:
                this.httpRequest = new HttpDeleteWithBody(url);
                break;
            default:
                throw new RuntimeException(String.format("HTTP verb \"%s\" is not supported", this.httpVerb));
        }

        if (options.proxy != null && !options.proxy.trim().isEmpty()) {
            this.setProxy(options.proxy);
        }
    }


    private CloseableHttpClient createHttpClient(boolean ignoreCert) {
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();

            CloseableHttpClient client=null;

            if (ignoreCert) {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(new KeyManager[0], new TrustManager[]{new NoopTrustManager()}, new SecureRandom());
                SSLContext.setDefault(sslContext);

                SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                        sslContext, NoopHostnameVerifier.INSTANCE);

                client = HttpClients.custom()
                        .disableRedirectHandling()
                        .setDefaultRequestConfig(requestConfig)
                        .setSSLSocketFactory(sslSocketFactory)
                        .build();
            } else {
                client = HttpClientBuilder.create()
                        .disableRedirectHandling().disableAutomaticRetries()
                        .setDefaultRequestConfig(requestConfig)
                        .build();
            }

            return client;
        } catch (Throwable ex) {
            throw new RuntimeException(String.format(
                    "Failed to create http client (ignoreCert = %s)",
                    ignoreCert), ex);
        }
    }

    public String call(HttpRequestOptions options) {

        HttpEntityEnclosingRequestBase request=null;
        try {


             //set verbs and create client object
            setRequestType(options);

            this.httpRequest.setHeader(BASEConstants.X_CLIENT_IP, "8.8.8.8");
            this.httpRequest.setHeader(BASEConstants.X_SITE_INTERNAL_REQUEST, "1");
            // set header
            if (getUserName() != null) {
                this.httpRequest.addHeader(BASEConstants.X_Site_AccessToken, Helios.getAccessToken(getUserName()));
            } else if (getUser() != null) {
                this.httpRequest.addHeader(BASEConstants.X_Site_AccessToken, Helios.getAccessToken(getUser().getUserName()));
            }
            // set query params
            if (getParams() != null) {


                 //  HttpPost httpPost = new HttpPost(getURL());
                 //  httpRequest.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));

             }
             // TODO: Take a timeout value and throw an exception in case the HTTP server doesn't respond in due time
            CloseableHttpResponse response = this.httpClient.execute(this.httpRequest);

            HttpEntity entity = response.getEntity();
            System.out.println( "Content posted to: " + this.httpRequest.toString());
            System.out.println(
                    "Response: " + EntityUtils.toString(response.getEntity(), "UTF-8"));
            return EntityUtils.toString(entity);
        }
        catch (UnsupportedEncodingException e) {
            throw new WebDriverException(ERROR_MESSAGE);
        }
        catch (ClientProtocolException e) {
            Log.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        } catch (IOException e) {
            Log.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }
        finally
        {
        }
    }
    protected String getURL()
    {
        return this.url;
    }

    public int getResponseStatusCode() {
        return this.response.getStatusLine().getStatusCode();
    }

    public InputStream getResponseAsStream() throws IOException {
        return this.response.getEntity().getContent();
    }
     public Map<String, String> getResponseHeaders() {
        Header[] headers = this.response.getAllHeaders();
        Map<String, String> headersMap = new HashMap<String, String>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }

    public MethodType getHttpVerb() {
        return this.httpVerb;
    }


    public String getResponseAsString() {
        HttpEntity responseEntity = this.response.getEntity();

        if (responseEntity != null) {
            try {
                InputStream contentStream = responseEntity.getContent();

                if (contentStream != null) {
                    return IOUtils.toString(contentStream, "UTF-8");
                } else {
                    return "";
                }
            } catch (Exception ex) {
                throw new RuntimeException(String.format("Failed to get the response content for HTTP request %s %s",
                        this.httpVerb,
                        this.url), ex);
            }
        } else {
            throw new RuntimeException(String.format("Failed to get a response for HTTP request %s %s",
                    this.httpVerb,
                    this.url));
        }
    }

     public String getFirstHeader(String headerName) {
        Header header = this.response.getFirstHeader(headerName);
        return header != null ? header.getValue() : null;
    }

    public void setContent(String content, String contentType) {
        if (content == null) {
            content = "";
        }

        //TODO: Improve the validation logic
        if (contentType.indexOf('/') <= 0) {
            throw new RuntimeException(String.format("Content type \"%s\" is not a valid MIME type", contentType));
        }

        if (HttpEntityEnclosingRequestBase.class.isInstance(httpRequest)) {
            try {
                StringEntity requestEntity = new StringEntity(content, Charset.forName("UTF-8"));
                ((HttpEntityEnclosingRequestBase) this.httpRequest).setEntity(requestEntity);
                this.httpRequest.setHeader("Content-Type", contentType);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to set HTTP request content", ex);
            }
        }
    }

    public void setHeader(String headerName, String headerValue) {
        this.httpRequest.setHeader(headerName, headerValue);
    }

    public void setProxy(String proxyServer) {
        String proxy = null;
        String proxyPort = null;
        Pattern pattern = Pattern.compile("(?<proxy>.+?)(:(?<port>.+))?");
        Matcher matcher = pattern.matcher(proxyServer.trim());
        if (matcher.matches()) {
            proxy = matcher.group("proxy");
            proxyPort = matcher.group("port");
        } else {
            throw new RuntimeException(String.format("Invalid proxy server:", proxyServer));
        }

        HttpHost proxyHost;
        if (proxyPort != null) {
            proxyHost = new HttpHost(proxy, Integer.valueOf(proxyPort));
        } else {
            proxyHost = new HttpHost(proxy);
        }
        RequestConfig oldConfig = this.httpRequest.getConfig();
        RequestConfig.Builder configBuilder = null;

        if (oldConfig != null) {
            configBuilder = RequestConfig.copy(oldConfig);
        } else {
            configBuilder = RequestConfig.custom().setProxy(proxyHost);
        }

        this.httpRequest.setConfig(configBuilder.build());
    }
}
