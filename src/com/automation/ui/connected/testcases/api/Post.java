package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.clientimpl.restlet.PostAdapter;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.ui.base.common.api.util.*;
import com.automation.ui.base.common.api.adapter.*;


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

    @Test(groups = "post")
    public void postRequest() {

        JsonObject request = Json.createObjectBuilder()
                .add("UserName", factory.data(Repository.USER_NAME))
                .add("Password", factory.data(Repository.PASSWORD))
                .build();

        RestAdapter response = PostAdapter.builder()
                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setEndPoint(factory.data(Repository.ENDPOINT))
                .setMethodName(factory.data(Repository.METHOD))
                .build();

        JsonPath jsonPath = response.execute();
        jsonPath.prettyPrint();

    }
}