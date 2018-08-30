package com.automation.ui.connected.testcases.login;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.automation.ui.base.common.core.assertion.Assertion;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//webdriver.firefox.marionette
        //  System.setProperty("webdriver.gecko.driver","D:\\Project\\Automation\\target\\classes\\FireFoxDriver\\firefoxdriver_win32\\geckodriver.exe");
      //  System.setProperty("webdriver.ie.driver", "D:\\Project\\Automation\\target\\classes\\IEDriver\\IEDriverServer.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\Project\\Automation\\target\\classes\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:9000/cloudData_steps.html");
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor) driver;




     //  WebElement one = driver.findElement(By.xpath("//input[@id='a42']"));

      WebElement yes = driver.findElement(By.xpath("//label[@for='es1']//span"));
        WebElement no = driver.findElement(By.xpath("//label[@for='es2']//span"));

        WebElement three = driver.findElement(By.xpath("//h1[contains(text(),'Connection is not secure')]"));
        WebElement four = driver.findElement(By.xpath("//label[@class='success']"));





        System.out.println("Successfully opened the website  "+ no.getText() + " :" +no.getTagName());
      // System.out.println("Successfully opened the website  "+ one.getText() + " : " +one.getTagName());

       /* one.isEnabled();
        System.out.println( one.isSelected());
        Thread.sleep(10000);
        System.out.println("two"  );


System.out.println(two.isSelected());
*/
        Thread.sleep(15000);
        no.click();

        System.out.println(no.isSelected());
        System.out.println(three.getText());
        System.out.println(four.getText());

        if (Assertion.assertStringContains(four.getText(), "Authentication successful")) {
            System.out.println("sleeping");

            Thread.sleep(15000);
        }



        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        //Declare and set the start time
        long start_time = System.currentTimeMillis();

        //Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        System.out.println("Successfully called js ");
        //Get the difference (currentTime - startTime)  of times.
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));

        Thread.sleep(5000);
        driver.close();
        driver.quit();


    }

}
