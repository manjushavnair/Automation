package com.automation.ui.connected.testcases.api.petstore;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.mapper.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

import org.testng.annotations.Test;

public class Login {

    @Test(groups = {"login"}, dependsOnGroups = {"user"})
    public void login() {
        RestAssured.baseURI = "http://petstore.swagger.io/v2";

        given()
                .contentType("application/json")
                .queryParam("sayem")
                .queryParam("passw0rd")
                .expect()
                .statusCode(200)
                .log().all().response()

                .when()
                .get("/user/login").asString();
    }

}
