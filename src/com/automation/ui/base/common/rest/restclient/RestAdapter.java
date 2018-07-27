package com.automation.ui.base.common.rest.restclient;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.internal.mapper.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;


public interface RestAdapter {

    JsonPath execute();

    <T> T execute(Class<T> responseClass);
}