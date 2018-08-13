package com.automation.ui.githubtesting.testcases.api.spotify;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Album {

    @Test
    public void getSeveralAlbums() {

        RestAssured.baseURI = "https://api.spotify.com/v1";

        JsonPath response = given()
                .contentType("application/json")
                .pathParam("album1", "0CZUbYCniBFqvgfTiaWPoz")
                .pathParam("album2", "7o9Sz4AdbwlPWGjymLtdLj")
                .pathParam("album3", "5ulJcaRDyIySeb7SrZpmjN")
                .expect()
                .statusCode(200)

                .when()
                .get("/albums/?ids={album1},{album2},{album3}").jsonPath();

        List<String> winnerIds = response.getList("albums.name");
        winnerIds.forEach(System.out::println);

    }


    @Test
    public void getAlbum() {

        RestAssured.baseURI = "https://api.spotify.com/v1";

        JsonPath response = given()
                .contentType("application/json")
                .pathParam("id", "0CZUbYCniBFqvgfTiaWPoz")
                .expect()
                .statusCode(200)

                .when()
                .get("/albums/{id}").jsonPath();

        String winnerIds = response.getString("artists.name");
        System.out.println(winnerIds);

    }
}
