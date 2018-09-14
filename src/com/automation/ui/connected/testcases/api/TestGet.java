package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.clientimpl.apacheimpl.factory.HttpClientFactory;
import com.automation.ui.base.common.api.clientimpl.apacheimpl.secure.HttpsTrustManager;
import com.automation.ui.base.common.api.clientimpl.apacheimpl.*;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.testng.annotations.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class TestGet {

    @Test(priority = 0,enabled = true, groups = "post")
    public void testAPI() throws Throwable{

        System.out.println("testAPI");
        UserRegistration ur=new UserRegistration();
        ur.registerUserEmailConfirmed();

    }

    @Test(priority = 0,enabled = false, groups = "post")
    public void simpleTest() throws Throwable{


        HttpClient client = new HttpClientFactory().getHttpsClient();
        // client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://lifecharger.org/3-tips-for-a-better-life/");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }

    }


    @Test(priority = 1,enabled = false, groups = "post")
    public void basicPingTest() {

        RestAssured.port = 443;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssuredConfig rac = null;

        try {

            SSLContext sslContext = SSLContexts.custom().useSSL().build();
            //SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            // SSLSocketFactory factory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            //  rac = RestAssured.config().sslConfig(SSLConfig.sslConfig().sslSocketFactory(factory));

            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            rac = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("TLSv1.2"));
            RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
            //  rac = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("TLSv1.2"));

            RestAssured.config.getHttpClientConfig().reuseHttpClientInstance();

            Response response = given().config(rac).headers("Content-Type", io.restassured.http.ContentType.JSON, "Accept", io.restassured.http.ContentType.JSON).
                    when().get("https://jsonplaceholder.typicode.com/users").
                    then().contentType(io.restassured.http.ContentType.JSON).extract().response();

            List<String> jsonResponse = response.jsonPath().getList("$");

            System.out.println(jsonResponse.size());

            String usernames = response.jsonPath().getString("username");
            System.out.println(usernames);

            String usernames1 = response.jsonPath().getString("username[0]");
            System.out.println(usernames1);

            List<String> jsonResponse1 = response.jsonPath().getList("username");
            System.out.println(jsonResponse1.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        /* try {


            // Use our custom socket factory
          //  SSLContext sslContext = SSLContexts.custom().useSSL().build();
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new X509TrustManager[]{new com.rest.HttpsTrustManager()}, new SecureRandom());
            org.apache.http.conn.ssl.SSLSocketFactory customSslFactory = new com.rest.Sslv3SocketFactory(
                    sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            rac = RestAssured.config().sslConfig(
                    SSLConfig.sslConfig().sslSocketFactory(customSslFactory));

            RestAssured.config.getHttpClientConfig().reuseHttpClientInstance();
               RestAssured.given().config(rac).when().get("https://reqres.in/api/users/2").then().statusCode(200);
             System.out.println("here");
        } catch (Exception e) {
             System.out.println("there");
            e.printStackTrace();
        }

        // RestAssuredConfig rac = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("TLSv1.2"));

         try {
             RestAssured.get("https://reqres.in/api/users/2").then().spec(helloWorldSpec());
           // RestAssured.given().config(RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation())).when().get("https://reqres.in/api/users/2").then().spec(helloWorldSpec());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            RestAssured.reset();
        }

          RestAssured.given()
                .config(RestAssured.config().sslConfig(
                        new SSLConfig().allowAllHostnames())).when().get("/posts").then().statusCode(200);;

        //RestAssured.given().config(rac).when().get("/posts").then().statusCode(200);
        // RestAssured.given().relaxedHTTPSValidation("TLSv1.2").when().get("/posts").then().statusCode(200);

        */

    }

}