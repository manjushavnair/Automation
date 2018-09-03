package com.automation.ui.base.common.api.clientimpl.restassured;

import com.automation.ui.base.common.api.adapter.AbstractAdapter;
import com.automation.ui.base.common.api.adapter.RestAdapter;
import com.automation.ui.base.common.api.secure.HttpsTrustManager;
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
import java.util.HashMap;
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




    public String getName() {
        return name;
    }


    @Override
    public JsonPath execute() {
        System.out.println("!!!!!!!!!!!POST!!!!!!!!!!! ");
        HashMap hmap=new HashMap( );
        hmap.put("id","openwkey");
        hmap.put("APPID", "3757978f62c331da8278ccc1804c7012");



        Response response = given()
                .baseUri(getEndPoint())
                .contentType(getContentType().getContentType())
                .body(getObject().toString())
                .formParams(hmap)
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log()
                .all()
                .when()
                .post(getMethod());

        String json = response.asString();
        System.out.println("json: "+json);
        //  List<String> jsonResponse = response.jsonPath().getList("$");
        return new JsonPath(json);
    }

    @Override
    public <T> T execute(Class<T> responseClass) {
        //RestAssured.port = Integer.valueOf(443);
        System.out.println("############POST############# ");
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
