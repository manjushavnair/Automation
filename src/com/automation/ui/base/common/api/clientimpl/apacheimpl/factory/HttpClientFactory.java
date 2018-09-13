//https://prasans.info/2014/06/making-https-call-using-apache-httpclient/

package com.automation.ui.base.common.api.clientimpl.apacheimpl.factory;


import com.automation.ui.base.common.api.clientimpl.apacheimpl.secure.HttpsTrustManager;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;

public class HttpClientFactory {

    private   CloseableHttpClient client;

    public   HttpClient getHttpsClient() throws Exception {


        if (client != null) {
            return client;
        }


        SSLContext sslcontext = SSLContexts.custom().useSSL().build();
        sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        client = HttpClients.custom().setSSLSocketFactory(factory).build();

        return client;
    }

    public   void releaseInstance() {
        this.client = null;
    }
}