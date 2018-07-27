package com.automation.ui.connected.testcases.api.weather;
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

public class OpenWeatherMap {

    @Test
    public void byCityName() {
        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";

        given()
                .contentType("application/json; charset=utf-8")
                .expect()
                .statusCode(200)
                .log().all()

                .when()
                .get("/weather?q=New York,New York");

    }
}
