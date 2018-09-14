package com.automation.ui.githubtesting.testcases.login;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Test {


    public static void main(String[] args) throws InterruptedException {
//webdriver.firefox.marionette
        //  System.setProperty("webdriver.gecko.driver","D:\\Project\\Automation\\target\\classes\\FireFoxDriver\\firefoxdriver_win32\\geckodriver.exe");
        System.setProperty("webdriver.ie.driver", "D:\\Project\\Automation\\target\\classes\\IEDriver\\IEDriverServer.exe");


        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://demo.guru99.com/V4/");
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor) driver;


        System.out.println("Successfully opened the website ");

        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        //Declare and set the start time
        long start_time = System.currentTimeMillis();

        //Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        System.out.println("Successfully called js ");
        //Get the difference (currentTime - startTime)  of times.
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));

//        Thread.sleep(5000);
        driver.quit();
    }

}
