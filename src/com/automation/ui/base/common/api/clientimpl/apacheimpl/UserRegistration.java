package com.automation.ui.base.common.api.clientimpl.apacheimpl;

import com.automation.ui.base.common.api.clientimpl.apacheimpl.ApiCall;
import com.automation.ui.base.common.auth.SignUpUser;
import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.logging.Log;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import com.automation.ui.base.common.api.util.MethodType;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;




import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import com.automation.ui.base.common.core.url.*;
import com.automation.ui.base.common.api.util.MethodType;
public class UserRegistration extends ApiCall {


   // private String baseURL = UrlBuilder.createUrlBuilderForSite("community")
     //       .getUrl()
       //         .replace(BASEConstants.HTTPS_PREFIX, BASEConstants.HTTP_PREFIX)
         //   + "/api.php";
    private ArrayList<BasicNameValuePair> params = new ArrayList<>();
    private User user = User.ANONYMOUS;
    private String username = user.getUserName();


    public UserRegistration() {
        super();
    }

    public  void registerUserEmailConfirmed( ) {

        try {




            HttpRequestOptions hro= new HttpRequestOptions(getURL()  ,MethodType.POST);
            hro.ignoreCert=true;


            url = new URIBuilder(getURL())
                    .setParameter("text", "text")
                    .setParameter("summary", "SUMMARY_QM")
                    .build()
                    .toASCIIString();

               /*
             url = new URL(getURL() + "user-registration/users/emailconfirmed");
             httpPost = new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
                    url.getPort(), url.getPath(), url.getQuery(), url.getRef()));
             */

         /*   HttpPost httpPost = new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
                    url.getPort(), url.getPath(), url.getQuery(), url.getRef()));

            httpPost.setHeader(BASEConstants.X_CLIENT_IP, "8.8.8.8");
            httpPost.setHeader(BASEConstants.X_SITE_INTERNAL_REQUEST, "1");
            */
             String response=call(hro);
             System.out.println("response"+response);

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
         String baseURL = XMLReader.getValue("servicesinternal." + env + "base_url");
        return "http://lifecharger.org/3-tips-for-a-better-life/";

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


       return params;

	}
}
