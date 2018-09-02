package com.automation.ui.base.common.api.clientimpl.http;

import com.automation.ui.base.common.auth.SignUpUser;
import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.logging.Log;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;
import com.automation.ui.base.common.core.url.*;
import com.automation.ui.base.common.constants.*;
import com.automation.ui.base.common.auth.User;

public class UserRegistration extends  ApiCall{


    private String baseURL = UrlBuilder.createUrlBuilderForSite("community")
            .getUrl()
                .replace(BASEConstants.HTTPS_PREFIX, BASEConstants.HTTP_PREFIX)
            + "/api.php";
    private ArrayList<BasicNameValuePair> params = new ArrayList<>();
    private User user = User.ANONYMOUS;
    private String username = user.getUserName();


    private UserRegistration() {
    }

    public  void registerUserEmailConfirmed(SignUpUser suser) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();

        URL url = null;


        try {
            url = new URL(getURL() + "user-registration/users/emailconfirmed");
        } catch (MalformedURLException e) {
            Log.logError("Wrong internal services URL", e);
        }
        try {
            HttpPost httpPost = new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
                    url.getPort(), url.getPath(), url.getQuery(), url.getRef()));

            httpPost.setHeader(BASEConstants.X_CLIENT_IP, "8.8.8.8");
            httpPost.setHeader(BASEConstants.X_SITE_INTERNAL_REQUEST, "1");
         call();
        } catch ( Exception   e) {
            Log.logError("Error during registering user", e);
        }
    }

      protected String getUserName()

    {
        return null;

    }
      protected String getURL()
    {

        String env = Configuration.getEnvType().getKey();
         String baseURL = XMLReader.getValue("services_internal." + env + ".base_url");
        return baseURL;

    }

    /**
     * Return null if API call doesn't require to be logged in as specific user
     *
     * @return User to be logged in while executing API call
     */
     protected User getUser()
    {
 return null;
    }

    protected ArrayList<BasicNameValuePair> getParams(){


            params.add(new BasicNameValuePair("username", user.getUserName()));
            params.add(new BasicNameValuePair("password", user.getPassword()));

            params.add(new BasicNameValuePair("marketingAllowed", "on"));
            params.add(new BasicNameValuePair("registrationId", "00000"));
       return params;

	}
}
