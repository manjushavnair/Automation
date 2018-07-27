package com.automation.ui.connected.testcases.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//comment the above line and uncomment below line to use Chrome
public class SampleCheckOfSelenium {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
        //System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
        //  WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();

        String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        // get the actual value of the title
        actualTitle = driver.getTitle();


        String tagName = "";


        tagName = driver.findElement(By.id("name-and-slogan")).getTagName();
        System.out.println(tagName);

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */

        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        //close Fire fox
        driver.close();

    }

}