package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.clientimpl.restlet.PostAdapter;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.ui.base.common.api.util.*;
import com.automation.ui.base.common.api.adapter.*;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;

public class Post {
    private PropertiesUtil factory;



    @Parameters("properties")
    public Post(@Optional(Repository.PROPERTIES)
                        String properties) throws IOException {
        factory = PropertiesUtil.create(properties);
    }
/*
    @Test(groups = "post")
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