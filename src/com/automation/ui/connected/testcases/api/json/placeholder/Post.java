package com.automation.ui.connected.testcases.api.json.placeholder;

import com.automation.ui.base.common.rest.restclient.util.Repository;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.automation.ui.base.common.rest.restclient.util.PropertiesUtil;
import com.automation.ui.base.common.rest.restclient.adapter.RestAdapter;
import com.automation.ui.base.common.rest.restclient.PostAdapter;



import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import com.automation.ui.base.common.core.api.apicore.*;
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