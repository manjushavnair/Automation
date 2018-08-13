package com.automation.ui.githubtesting.testcases.api.weather;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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
