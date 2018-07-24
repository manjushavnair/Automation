/**
 *
 */
package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.utils.ExcelUtil;
import com.automation.ui.connected.pageobjectsfactory.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataConnection;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
//import com.uiautomation.ui.listener.LoginListener;

//@Listeners(com.uiautomation.ui.listener.LoginListener.class)

public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);

    public HomePageTest() {

        super();

    }


    @Test(enabled = true,priority = 6,groups = {  "validcase"}, description = "home page ")
    public void launchHomePage() throws Throwable {

        logger.info("Entering launchHomePage");
        Reporter.log("Entering launchHomePage");
        LoginPage login_page = new LoginPage();
        HomePage  home_page = new HomePage();
        login_page.open();
        // Call the method

        String userName = "";
        String password = "";
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            ExcelUtil eu = getExcelUtil();
            userName = eu.getCellData(1, 1);
            password = eu.getCellData(1, 2);

            logger.info("Entering launchHomePage");
        } catch (Exception e) {
            Assert.fail("Unable to login");
            e.printStackTrace();
        }
       login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();

        logger.info("Exiting launchHomePage and going to  homepage"+urlBuilder.getUrl());
        //Thread.sleep(120000);
         home_page.waitForPageReload();



        //driver.navigate().forward();



    }


    @Test(enabled = true,priority = 8,groups = {  "validcase"}, description = "Add Connection ")
    public void addConnection() throws Throwable {

        logger.info("Entering launchHomePage");
        Reporter.log("Entering launchHomePage");
        LoginPage login_page = new LoginPage();

        login_page.open();
        // Call the method

        String userName = "";
        String password = "";
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            ExcelUtil eu = getExcelUtil();
            userName = eu.getCellData(1, 1);
            password = eu.getCellData(1, 2);

            logger.info("Entering launchHomePage");
        } catch (Exception e) {
            Assert.fail("Unable to login");
            e.printStackTrace();
        }
        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();

        HomePage  home_page = new HomePage();

        logger.info("Exiting launchHomePage and going to  homepage"+urlBuilder.getUrl());
        //Thread.sleep(120000);
        home_page.waitForPageReload();
        logger.info("Entering addConnection");
        Reporter.log("Entering addConnection");
         AddCloudDataConnection add_conn_page = new AddCloudDataConnection();

        add_conn_page.open();

        add_conn_page.addConnection();

        logger.info("Exiting addConnection and going to  add"+add_conn_page.getUrl());
        //Thread.sleep(120000);
        add_conn_page.waitForPageReload();

    }



/*
    @AfterClass
    protected void tearDownAfterTestClass() {
        // close connections, close browser as needed
        logger.info("Keeping browser open tearDownAfterTestClass  ::Saaslogin");

    }

    @AfterSuite
    protected void tearDownAfterTestSuite() {
        logger.info("Keeping browser open   tearDownAfterTestSuite ::Saaslogin ");

    }
    */


}
