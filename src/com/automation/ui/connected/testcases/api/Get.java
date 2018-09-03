package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.adapter.*;
import com.automation.ui.base.common.api.clientimpl.restassured.*;
import com.automation.ui.base.common.api.secure.HttpsTrustManager;
import com.automation.ui.base.common.api.util.ContentType;
import com.automation.ui.base.common.api.util.PropertiesUtil;
import com.automation.ui.base.common.api.util.Repository;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class Get {
    private PropertiesUtil factory;



    @Parameters("properties")
    public Get(@Optional(Repository.PROPERTIES)
                        String properties) throws IOException {
        factory = PropertiesUtil.create(properties);
    }

    @Test(groups = "get")
    public void getRequest() {

   /* PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName("admin");
    authScheme.setPassword("admin");
    RestAssured.authentication = authScheme;
     RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
    //https://techbeacon.com/how-perform-api-testing-rest-assured

    */

        JsonObject request = Json.createObjectBuilder()
                .build();

        HashMap hmap=new HashMap( );
        hmap.put("id","openwkey");
        hmap.put("APPID", "3757978f62c331da8278ccc1804c7012");
        hmap.put("q", "New York,New York");


        System.out.println("request:"+request.toString());

        RestAdapter response = GetAdapter.builder()

                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setParams(hmap)
                .setEndPoint(factory.data(Repository.ENDPOINT))
                .setMethodName(factory.data(Repository.METHOD))
                .build();

        System.out.println("response:"+response.toString());

        JsonPath jsonPath = response.execute();
        jsonPath.prettyPrint();

    }



    @Test(enabled = false,groups = "post")
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