package com.automation.ui.base.common.api.clientimpl.apacheimpl;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.logging.Log;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.*;

import org.openqa.selenium.WebDriverException;


import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public abstract class ApiCall {

    protected static String URL_STRING = null;
    private static String ERROR_MESSAGE = "Problem with API call";

    protected ApiCall() {
    }

    abstract protected String getURL();

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

    public void call() {
        CloseableHttpClient httpClient=null;
        HttpPost httpPost=null;
        URL url = null;

        try {
            httpClient = HttpClientBuilder.create().disableAutomaticRetries()
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
            httpPost = new HttpPost(getURL());

           /*
             url = new URL(getURL() + "user-registration/users/emailconfirmed");
             httpPost = new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
                    url.getPort(), url.getPath(), url.getQuery(), url.getRef()));
            */

            httpPost.setHeader(BASEConstants.X_CLIENT_IP, "8.8.8.8");
            httpPost.setHeader(BASEConstants.X_SITE_INTERNAL_REQUEST, "1");

            // set header

            if (getUserName() != null) {
                httpPost.addHeader(BASEConstants.X_Site_AccessToken, Helios.getAccessToken(getUserName()));
            } else if (getUser() != null) {
                httpPost.addHeader(BASEConstants.X_Site_AccessToken, Helios.getAccessToken(getUser().getUserName()));
            }
            // set query params
            if (getParams() != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
            }

            CloseableHttpResponse resp = httpClient.execute(httpPost);

            HttpResponse response = null;
            response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            //return EntityUtils.toString(entity);

            Log.info("CONTENT: ", "Content posted to: " + httpPost.toString());
            Log.info("CONTENT: ",
                    "Response: " + EntityUtils.toString(resp.getEntity(), "UTF-8"));


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
}
