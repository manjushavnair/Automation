package com.automation.ui.base.common.rest.restclient;


import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.internal.mapper.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.mapper.*;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class DeleteAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected DeleteAdapter(DeleteBuilder<?, ?> builder) {
        super(builder);
        this.name = builder.name;

    }

    public static DeleteBuilder<?, ?> builder() {
        return new DefaultDeleteBuilder();
    }

    public String getName() {
        return name;
    }

    @Override
    public JsonPath execute() {
        Response response = given()
                .baseUri(getEndPoint())
                .contentType(getContentType().getContentType())
                .body(getObject().toString())
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()

                .when()
                .delete(getMethod());

        String json = response.asString();
        return new JsonPath(json);
    }

    @Override
    public <T> T execute(Class<T> responseClass) {
        return given()
                .config(RestAssured.config().encoderConfig(encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(true)))
                .baseUri(getEndPoint())
                .contentType(getContentType().getContentType())
                .body(getObject(), ObjectMapperType.GSON)
                .basePath("/")
                .port(Integer.valueOf(443))
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()

                .when()
                .delete(getMethod()).as(responseClass, ObjectMapperType.GSON);
    }

    public static abstract class DeleteBuilder<S extends DeleteAdapter, B extends DeleteBuilder<S, B>> extends AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultDeleteBuilder extends DeleteBuilder<DeleteAdapter, DefaultDeleteBuilder> {
        @Override
        public DeleteAdapter build() {
            return new DeleteAdapter(this);
        }
    }
}