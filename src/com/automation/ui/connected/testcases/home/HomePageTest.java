
package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.utils.ExcelUtil;
import com.automation.ui.connected.common.prpreader.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataConnection;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);

    private  LoginPage login_page=null;
    private  HomePage  home_page=null;

    public HomePageTest() {

        super();

    }


    @Test(enabled = true,priority = 1,groups = {  "validcase"}, description = "home page ")

    public void launchLogin() throws Throwable {

        logger.info("Entering launchLogin");
        Reporter.log("Entering launchLogin");
          login_page = new LoginPage();

        login_page.open();
        // Call the method

        String userName = "";
        String passWord = "";
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            ExcelUtil eu = getExcelUtil();
            userName = eu.getCellData(1, 1);
            passWord = eu.getCellData(1, 2);

            logger.info("Entering  " +methodname);
        } catch (Exception e) {
            Assert.fail( AssertDataReader.readProperty().getValue("EXCEL_LOGINMSG"));
            e.printStackTrace();
        }
        login_page.enterUser(userName);
        login_page.enterPassword(passWord);
         home_page= login_page.login();
      // home_page.waitForPageReload();
         home_page.waitForPageLoad();
        // home_page.open();



        logger.info("Exiting launchLogin and going to  homepage"+urlBuilder.getUrl());



    }
     @Test(enabled = true,priority = 2,groups = {  "validcase"}, description = "launchHomePage ")
    public void launchHomePage()   {
         logger.info(" launchHomePage and going to  homepage"+urlBuilder.getUrl() );
         login_page.wait.forElementClickable(By.cssSelector("primary"));
         logger.info(" home_page.getCurrentUrl() "+ home_page.getCurrentUrl());

home_page.addConnection();

            //home_page.addRepo();
     }




    @Test(enabled = false,priority = 3,groups = {  "validcase"}, description = "Add Connection ")
    public void addConnection() throws Throwable {

        logger.info("Entering launchHomePage");
        Reporter.log("Entering launchHomePage");
        // add_conn_page.open();

        home_page.addConnection();
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
