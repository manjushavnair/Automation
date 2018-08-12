package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataConnection;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import com.automation.ui.base.common.core.element.*;
import com.automation.ui.base.common.core.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;



public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);


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


        logger.info("Exiting launchLogin and going to  homepage" + urlBuilder.getUrl());


    }

    @Test(enabled = true, priority = 2, groups = {"validcase"},description = "launchHomePage ")
    public void launchHomePage() throws Throwable {
       // logger.info(" launchHomePage and going to  homepage" + urlBuilder.getUrl());
        home_page=new HomePage();
        //home_page.wait.forElementClickable(By.cssSelector(".primary"));
        Thread.sleep(20000);
       // logger.info(" home_page.getCurrentUrl() " + home_page.getCurrentUrl());
       // home_page.waitForPageLoad();


    }

    @Test(enabled = true, priority = 3, groups = {"validcase"}, description = "home page ")

    public void addConnection() throws Throwable {
        logger.info("Entering addConnection");
        Reporter.log("Entering addConnection");
         home_page.addConnection();
        logger.info("Entering addConnection data_page" +data_page);
      //  home_page.waitForPageLoad();
        logger.info("Exiting addConnection and going to  add "  );
    }




    @Test(enabled = true, priority = 4,groups = {"validcase"}, description = "home page ")
    public void addConnectionCancel() throws Throwable {

        logger.info("Entering addConnectionCancel " );
        Reporter.log("Entering addConnectionCancel ");
        home_page.addConnection();
         Assertion.assertTrue(home_page.isButtonVisible());
    //    Assertion.assertEquals(subhead.getSubheadTitle(),  "Editing template: InfoboxBuilderChangeTemplateNameBySubhead"
          home_page.addConnectionCancel();
        Reporter.log("Entering addConnectionCancel ");
      //  home_page.waitForPageLoad();
        logger.info("Exiting addConnectionCancel  ");
    }

    @Test(enabled = true, priority = 5,groups = {"validcase"}, description = "home page ")
    public void addConnectionNext() throws Throwable {

        logger.info("Entering addConnectionNext  " );
        Reporter.log("Entering addConnectionNext ");
        home_page.addConnection();

        Assertion.assertTrue(home_page.isButtonVisible());
        home_page.addConnectionNext();
        logger.info("Entering addConnectionNext  " +home_page.getConnMessage());
        Assertion.assertEquals(home_page.getConnMessage(),
                "Connection name is required");

        Reporter.log("Entering addConnectionNext ");
        logger.info("Exiting addConnectionNext  ");
    }


    @Test(enabled = false,priority = 4,groups = {  "validcase"}, description = "home page ")

    public void launch_logout() throws Throwable {

        logger.info("Logging out from launchHomePage_logout");
        Reporter.log("Logging out from launchHomePage_logout");

        home_page.goToLogoutPage();
    }


    private void verifyElementsVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertTrue(home_page.isElementVisible(
                element), element + " is not visible"));
    }

    private void verifyElementsNotVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertFalse(home_page.isElementVisible(
                element), element + " is visible"));
    }


}
