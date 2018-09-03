package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.secure.HttpsTrustManager;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.ui.base.common.api.util.*;
import io.restassured.path.json.JsonPath;
import com.automation.ui.base.common.api.adapter.*;
import com.automation.ui.base.common.api.clientimpl.restassured.*;

import io.restassured.*;


import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;


import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

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
                .add("external_id","SF_TEST001")
                .add("name", "San Francisco Test Station")
                .add("latitude", 37.76)
                .add("longitude", -122.43)
                .add("altitude", 150)
                .build();

        HashMap hmap=new HashMap( );
        hmap.put("id","openwkey");
        hmap.put("APPID", "3757978f62c331da8278ccc1804c7012");




        System.out.println("%%%%%%%%POST%%%%%%%%%%%");
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



}