package com.automation.ui.base.common.rest.restclient.util;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.internal.mapper.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public enum Repository {

    USER_NAME("username"),
    PASSWORD("password"),
    METHOD("method"),
    ENDPOINT("endpoint");

    public static final String PROPERTIES = "src/test/resources/api.properties";
    private String value;

    private Repository(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}