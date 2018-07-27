package com.automation.ui.connected.testcases.api.petstore;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.mapper.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;


public class User {

    @Test(groups = {"user"})
    public void user() {
        RestAssured.baseURI = "http://petstore.swagger.io/v2";

        JsonObject request = Json.createObjectBuilder()
                .add("id", "0")
                .add("username", "sayem")
                .add("firstName", "Syed")
                .add("lastName", "Sayem")
                .add("email", "syed@sayem.org")
                .add("password", "passw0rd")
                .add("phone", "7189568116")
                .build();

        given()
                .contentType("application/json")
                .body(request.toString())
                .expect()
                .statusCode(200)
                .log().all().request().and().response()

                .when()
                .post("/user").asString();
    }
}
