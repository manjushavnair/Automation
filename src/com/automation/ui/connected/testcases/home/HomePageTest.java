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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



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
        HomePage  home_page= login_page.login();

        logger.info("Exiting launchHomePage and going to  homepage"+urlBuilder.getUrl());

         home_page.waitForPageReload();



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
        HomePage  home_page =login_page.login();


        logger.info("Exiting launchHomePage and going to  homepage"+urlBuilder.getUrl());
        home_page.waitForPageReload();
        logger.info("Entering addConnection");
        Reporter.log("Entering addConnection");
        AddCloudDataConnection add_conn_page = new AddCloudDataConnection();

        add_conn_page.open();
        add_conn_page.addConnection();
        logger.info("Exiting addConnection and going to  add"+add_conn_page.getUrl());
         add_conn_page.waitForPageReload();

    }


}