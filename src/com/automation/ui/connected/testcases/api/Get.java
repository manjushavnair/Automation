package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.adapter.RestAdapter;
import com.automation.ui.base.common.api.clientimpl.restassured.GetAdapter;
import com.automation.ui.base.common.api.secure.HttpsTrustManager;
import com.automation.ui.base.common.api.util.ContentType;
import com.automation.ui.base.common.api.util.PropertiesUtil;
import com.automation.ui.base.common.api.util.Repository;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.templates.core.CoreTestTemplate;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;

import static org.hamcrest.Matchers.equalTo;

public class Get
        //extends CoreTestTemplate
{

    private static Logger logger = Logger
            .getLogger(CoreTestTemplate.class);
    File file = null;
    private PropertiesUtil factory;

    @Parameters("properties")
    public Get(@Optional(Repository.PROPERTIES)
                       String properties) throws IOException {
        factory = PropertiesUtil.create(properties);
    }

    /*

    @Override
    protected void prepareURLs() {

    }

    @Override
    protected void loadFirstPage() {

    }

    @Override
    protected void getDataReaders() {

    }*/

    @BeforeClass(alwaysRun = true)
    public void initTestClass() {


        file = new File(Configuration.getRestConfigFilePath());
    }


    @Test(groups = "get")
    public void verifyContentType() {
        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");

        given().params(hmap)
                .when()
                .baseUri(XMLReader.getValue(file, "service1.resources.resource1"))
                .get(XMLReader.getValue(file, "service1.resources.method1"))
                .then()
                .assertThat()
                .statusCode(200)
                .and().contentType(ContentType.JSON.toString());
    }

    @Test(groups = "get")
    public void verifySingleSpecificDataInResponseBody() {
        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");

        given().params(hmap)
                .when()
                .baseUri(XMLReader.getValue(file, "service1.resources.resource1"))
                .get(XMLReader.getValue(file, "service1.resources.method1"))
                .then().body("base",equalTo("stations"));

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

        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");
        RestAdapter response = GetAdapter.builder()
                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setParams(hmap)
                .setEndPoint(XMLReader.getValue(file, "service1.resources.resource1"))
                .setMethodName(XMLReader.getValue(file, "service1.resources.method1"))
                .build();
        JsonPath jsonPath = response.execute();

    }


    @Test(enabled = false, groups = "post")
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