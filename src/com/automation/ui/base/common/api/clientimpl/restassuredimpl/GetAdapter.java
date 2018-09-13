package com.automation.ui.base.common.api.clientimpl.restassuredimpl;


import com.automation.ui.base.common.api.adapter.AbstractAdapter;
import com.automation.ui.base.common.api.adapter.RestAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class GetAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected GetAdapter(GetBuilder<?, ?> builder) {
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
        System.out.println("@@@@@@@@@@@@GET@@@@@@@@@@@@@@"+getObject().toString());
        ValidatableResponse  response = given()
                .baseUri(getEndPoint())
                .params(getParams())
                .contentType(getContentType().getContentType())
                .body(getObject().toString())
                .expect()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(getMethod())
                .then()
                .assertThat()
                .statusCode(200);

           String json = response.toString();
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
                .port(Integer.valueOf(443))
                .basePath("/")
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log().all()

                .when()
                .get(getMethod()).as(responseClass, ObjectMapperType.GSON);
    }

    public static abstract class GetBuilder<S extends GetAdapter, B extends GetBuilder<S, B>> extends AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultGetBuilder extends GetBuilder<GetAdapter, DefaultGetBuilder> {
        @Override
        public GetAdapter build() {
            return new GetAdapter(this);
        }
    }
}