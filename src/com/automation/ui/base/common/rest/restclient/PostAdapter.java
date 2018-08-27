package com.automation.ui.base.common.rest.restclient;

import com.automation.ui.base.common.rest.restclient.adapter.AbstractAdapter;
import com.automation.ui.base.common.rest.restclient.adapter.RestAdapter;
import com.automation.ui.base.common.rest.restclient.secure.HttpsTrustManager;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;


public class PostAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected PostAdapter(GetBuilder<?, ?> builder) {
        super(builder);
        this.name = builder.name;

    }

    public static GetBuilder<?, ?> builder() {
        return new DefaultGetBuilder();
    }


    @Test
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

            Response response = given().config(rac).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                    when().get("https://jsonplaceholder.typicode.com/users").
                    then().contentType(ContentType.JSON).extract().response();

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

    public String getName() {
        return name;
    }

    //    Response response = given()
    // .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
    // .when()
    // .get("https://jsonplaceholder.typicode.com/users")
    // .then()
    // .contentType(ContentType.JSON)
    // .extract()
    // .response();

    @Override
    public JsonPath execute() {
        Response response = given()
                .baseUri(getEndPoint())
                .contentType(getContentType().getContentType())
                .body(getObject().toString())
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log()
                .all()
                .when()
                .post(getMethod());

        String json = response.asString();
        //  List<String> jsonResponse = response.jsonPath().getList("$");
        return new JsonPath(json);
    }

    @Override
    public <T> T execute(Class<T> responseClass) {
        //RestAssured.port = Integer.valueOf(443);
        return given()
                .config(RestAssured.config().encoderConfig(encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(true)))
                .baseUri(getEndPoint())
                .port(Integer.valueOf(443))
                .basePath("/")
                .contentType(getContentType().getContentType())
                .body(getObject(), ObjectMapperType.GSON)
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()

                .when()
                .post(getMethod()).as(responseClass, ObjectMapperType.GSON);
    }

    public static abstract class GetBuilder<S extends PostAdapter, B extends GetBuilder<S, B>> extends AbstractAdapter.AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultGetBuilder extends GetBuilder<PostAdapter, DefaultGetBuilder> {
        @Override
        public PostAdapter build() {
            return new PostAdapter(this);
        }
    }
}
