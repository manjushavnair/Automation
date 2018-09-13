package com.automation.ui.connected.testcases.api;

import com.automation.ui.base.common.api.adapter.RestAdapter;
import com.automation.ui.base.common.api.clientimpl.restassuredimpl.GetAdapter;

import com.automation.ui.base.common.api.util.ContentType;
import com.automation.ui.base.common.api.util.PropertiesUtil;
import com.automation.ui.base.common.api.util.Repository;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.templates.core.CoreTestTemplate;

import io.restassured.path.json.JsonPath;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.JsonObject;

import java.io.File;
import java.io.IOException;

import java.util.*;


import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;

public class Get
        //extends CoreTestTemplate
{

    private static Logger logger = Logger
            .getLogger(CoreTestTemplate.class);
    File file = null;
    private PropertiesUtil factory;

    @Parameters("properties")
    public Get(@Optional(Repository.PROPERTIES)
                       String properties) throws IOException {
        factory = PropertiesUtil.create(properties);
    }

    /*

    @Override
    protected void prepareURLs() {

    }

    @Override
    protected void loadFirstPage() {

    }

    @Override
    protected void getDataReaders() {

    }*/

    @BeforeClass(alwaysRun = true)
    public void initTestClass() {


        file = new File(Configuration.getRestConfigFilePath());
    }


    @Test(groups = "get")
    public void verifyContentType() {
        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");

        given().params(hmap)
                .when()
                .baseUri(XMLReader.getValue(file, "service1.resources.resource1"))
                .get(XMLReader.getValue(file, "service1.resources.method1"))
                .then()
                .assertThat()
                .statusCode(200)
                .and().contentType(ContentType.JSON.toString());
    }

    @Test(groups = "get")
    public void verifySingleSpecificDataInResponseBody() {
        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");

        given().params(hmap)
                .when()
                .baseUri(XMLReader.getValue(file, "service1.resources.resource1"))
                .get(XMLReader.getValue(file, "service1.resources.method1"))
                .then().body("base",equalTo("stations"));

    }



    @Test(groups = "get")
    public void getRequest() {

   /* PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName("admin");
    authScheme.setPassword("admin");
    RestAssured.authentication = authScheme;
     RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";
    //https://techbeacon.com/how-perform-api-testing-rest-assured

    */

        JsonObject request = Json.createObjectBuilder()
                .build();

        HashMap hmap = new HashMap();
        hmap.put("id", XMLReader.getValue(file, "service1.resources.appid"));
        hmap.put("APPID", XMLReader.getValue(file, "service1.resources.appkey"));
        hmap.put("q", "New York,New York");
        RestAdapter response = GetAdapter.builder()
                .setContentType(ContentType.JSON)
                .setRequestObject(request)
                .setParams(hmap)
                .setEndPoint(XMLReader.getValue(file, "service1.resources.resource1"))
                .setMethodName(XMLReader.getValue(file, "service1.resources.method1"))
                .build();
        JsonPath jsonPath = response.execute();

    }




}