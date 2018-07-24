//https://github.com/rest-assured/rest-assured/wiki/usage
//https://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/#Verifying_Cookies
//https://rohanpalkar.wordpress.com/2017/09/26/restassured-with-tlsv1-2/
package com.automation.ui.base.common.rest.restserver;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

//import org.hamcrest.Matchers.equalTo;


public class FunctionalTest {

    @BeforeClass
    public void setUp() throws Exception {

        System.out.println("setup" + RestAssured.baseURI);
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(443);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/posts/";
        }
        RestAssured.basePath = basePath;
        String baseHost = System.getProperty("server.host");
        System.out.println("baseHost" + baseHost);
        if (baseHost == null) {
            baseHost = "https://jsonplaceholder.typicode.com";
        }
        RestAssured.baseURI = baseHost;
    }
}
