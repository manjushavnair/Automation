package com.automation.ui.connected.testcases.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//webdriver.firefox.marionette
        //  System.setProperty("webdriver.gecko.driver","D:\\Project\\Automation\\target\\classes\\test\\FireFoxDriver\\firefoxdriver_win32\\geckodriver.exe");
           System.setProperty("webdriver.ie.driver","D:\\Project\\Automation\\target\\classes\\test\\IEDriver\\IEDriverServer.exe");

        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://www.facebook.com");

        System.out.println("Successfully opened the website www.facebook.com");

        Thread.sleep(5000);
        driver.quit();
    }

}
