package com.automation.ui.test;

import com.automation.ui.driver.DriverType;
import com.automation.base.common.utils.TestDataPro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FactoryPatternTest {

    DriverManager driverManager;
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {

        driverManager = DriverManagerFactory.startBrowser(DriverType.CHROME.name());
    }

    @BeforeMethod
    public void beforeMethod() {

        driver = driverManager.getDriver();
    }

    @AfterMethod
    public void afterMethod() {

        driverManager.quitDriver();
    }


    @Test
    public void launchGoogleTest() {
        driver.get("https://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(dataProviderClass=TestDataPro.class,dataProvider="getdata")
    public void testGoogleWithSearch(String searchData) {
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys(searchData);
    }

    @Test
    public void launchYahooTest() {
        driver.get("https://www.yahoo.com");
        Assert.assertEquals("Yahoo", driver.getTitle());
    }

}