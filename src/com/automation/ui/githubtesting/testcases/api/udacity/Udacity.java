package com.automation.ui.githubtesting.testcases.api.udacity;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Udacity {

    @Test
    public void getCourses() {
        RestAssured.baseURI = "https://www.udacity.com/public-api/v0";

        JsonPath response = given()
                .contentType("application/json")
                .expect()
                .statusCode(200)

                .when()
                .get("/courses").jsonPath();

        List<String> winnerIds = response.get("courses.project_name");
        winnerIds.parallelStream()
                .filter(s -> s.contains("Android"))
                .forEach(System.out::println);
    }
}
