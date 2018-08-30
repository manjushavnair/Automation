package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);


    public HomePageTest() {

        super();
    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 6, groups = {"validcase"}, description = "home page ")

    public void launchLogin() throws Throwable {

        logger.info("Entering launchLogin");
        Reporter.log("Entering launchLogin");
        login_page = new LoginPage();

        login_page.open();


        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String userName = eu.getCellData(1, 1);
        String passWord = eu.getCellData(1, 2);

        login_page.enterUser(userName);
        login_page.enterPassword(passWord);
        home_page = login_page.login();


        // home_page.waitForPageReload();


        logger.info("Exiting launchLogin and going to  homepage" + urlBuilder.getUrl());


    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 7, groups = {"validcase"}, description = "launchHomePage ")
    public void launchHomePage() throws Throwable {
        home_page = new HomePage();
        // Thread.sleep(20000);


    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 8, groups = {"validcase"}, description = "home page ")

    public void addConnection() throws Throwable {
        logger.info("Entering addConnection");
        Reporter.log("Entering addConnection");
        home_page.addConnection();

        logger.info("Exiting addConnection and going to  add ");
    }
    /***
     *
     * @throws Throwable
     */


    @Test(enabled = false, priority = 9, groups = {"validcase"}, description = "home page ")

    public void launch_logout() throws Throwable {

        logger.info("Logging out from launchHomePage_logout");
        Reporter.log("Logging out from launchHomePage_logout");

        home_page.goToLogoutPage();
    }


}
