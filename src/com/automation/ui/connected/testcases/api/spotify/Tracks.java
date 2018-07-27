package com.automation.ui.connected.testcases.api.spotify;
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
import java.util.*;

public class Tracks {

    @Test
    public void getTrack() {

        RestAssured.baseURI = "https://api.spotify.com/v1";

        JsonPath response = given()
                .contentType("application/json")
                .pathParam("id", "46Wv5hDZud87ECEnqCkNlm")
                .expect()
                .statusCode(200)

                .when()
                .get("/albums/{id}/tracks").jsonPath();

        List<String> winnerIds = response.getList("items.name");
        winnerIds.forEach(System.out::println);

    }
}
