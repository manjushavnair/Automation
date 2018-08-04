package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);

    private LoginPage login_page = null;
    private HomePage home_page = null;


    public HomePageTest() {

        super();

    }


    @Test(enabled = true, priority = 1, groups = {"validcase"}, description = "home page ")

    public void launchLogin() throws Throwable {

        logger.info("Entering launchLogin");
        Reporter.log("Entering launchLogin");
        login_page = new LoginPage();

        login_page.open();
        // Call the method


        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String userName = eu.getCellData(1, 1);
        String passWord = eu.getCellData(1, 2);

        login_page.enterUser(userName);
        login_page.enterPassword(passWord);
        home_page = login_page.login();
        // home_page.waitForPageReload();
        // home_page.open();


        logger.info("Exiting launchLogin and going to  homepage" + urlBuilder.getUrl());


    }

    @Test(enabled = true, priority = 2, groups = {"validcase"}, description = "launchHomePage ")
    public void launchHomePage() throws Throwable {
        logger.info(" launchHomePage and going to  homepage" + urlBuilder.getUrl());
        login_page.wait.forElementClickable(By.cssSelector(".primary"));
        logger.info(" home_page.getCurrentUrl() " + home_page.getCurrentUrl());
        home_page.waitForPageLoad();

        Thread.sleep(30000);

        //  home_page.addConnection();

        //home_page.addRepo();
    }

    /*

    @Test(enabled = true,priority = 3,groups = {  "validcase"}, description = "Add Connection ")
        public void addConnection_accessdenied() throws Throwable {
        logger.info("Entering addConnection_accessdenied Threed.sleep");

        home_page.addConnection_accessdenied();
        Thread.sleep(30000);

    }
    */




    @Test(enabled = true,priority = 4,groups = {  "validcase"}, description = "Add Connection ")
    public void addConnection() throws Throwable {

        logger.info("Entering launchHomePage");
        Reporter.log("Entering launchHomePage");
        // add_conn_page.open();

        home_page.addConnection();
        home_page.waitForPageLoad();

        logger.info("Exiting addConnection and going to  add"+home_page.getUrl());

    }

/*
    @Test(enabled = false,priority = 4,groups = {  "validcase"}, description = "home page ")

    public void launchHomePage_logout() throws Throwable {

        logger.info("Logging out from launchHomePage_logout");
        Reporter.log("Logging out from launchHomePage_logout");

        home_page.goToLogoutPage();
    }

*/
}
