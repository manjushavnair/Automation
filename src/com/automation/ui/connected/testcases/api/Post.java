package com.automation.ui.connected.testcases.api;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.ui.base.common.api.util.*;

import io.restassured.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


import java.io.IOException;

public class Post {
    private PropertiesUtil factory;



    @Parameters("properties")
    public Post(@Optional(Repository.PROPERTIES)
                        String properties) throws IOException {
        factory = PropertiesUtil.create(properties);
    }
/*

    public void postRequest() {

        JsonObject request = Json.createObjectBuilder()
                .add("UserName", factory.data(Repository.USER_NAME))
                .add("Password", factory.data(Repository.PASSWORD))
                .build();

        System.out.println("request:"+request.toString());

        RestAdapter response = PostAdapter.builder()
                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setEndPoint(factory.data(Repository.ENDPOINT))
                .setMethodName(factory.data(Repository.METHOD))
                .build();

        System.out.println("response:"+response.toString());

        JsonPath jsonPath = response.execute();
        jsonPath.prettyPrint();

    }
    */

/*
 //3757978f62c331da8278ccc1804c7012
    //openwkey

    api.openweathermap.org/data/2.5/forecast?
 */

//https://techbeacon.com/how-perform-api-testing-rest-assured
@Test(groups = "post")
public void byCityName() {
    RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";

   /* PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName("admin");
    authScheme.setPassword("admin");
    RestAssured.authentication = authScheme;

    */


    given()
            .contentType(ContentType.JSON)
            .param("id",factory.data(Repository.ID))
            .param("APPID", factory.data(Repository.APPID)).expect()

            .when()
            .get("/weather?q=New York,New York")

            .then()
            .assertThat()
            .statusCode(200)
            .log().all();



}
}