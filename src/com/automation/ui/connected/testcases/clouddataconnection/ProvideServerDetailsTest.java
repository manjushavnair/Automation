package com.automation.ui.connected.testcases.clouddataconnection;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.*;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ProvideServerDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(ProvideServerDetailsTest.class);


    public ProvideServerDetailsTest() {

        super();
        logger.info(" ProvideServerDetailsTest");

    }


    @Test(enabled = true, priority = 1,groups = {"validcase"}, description = "home page ")
    public void addConnectionCancel() throws Throwable {

        serverdetail_page=new ProvideServerDetails();

        logger.info("Entering addConnectionCancel " );
        Reporter.log("Entering addConnectionCancel ");
        home_page =new HomePage();
        home_page.addConnection();
        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        //    Assertion.assertEquals(subhead.getSubheadTitle(),  "Editing template: InfoboxBuilderChangeTemplateNameBySubhead"
        serverdetail_page.addConnectionCancel();
        Reporter.log("Entering addConnectionCancel ");
        //  home_page.waitForPageLoad();
        logger.info("Exiting addConnectionCancel  ");
    }

    @Test(enabled = true, priority = 2,groups = {"validcase"}, description = "home page ")
    public void addConnectionNext() throws Throwable {

        logger.info("Entering addConnectionNext  " );
        Reporter.log("Entering addConnectionNext ");
        home_page.addConnection();

        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        serverdetail_page.addConnectionNext();
        logger.info("Entering addConnectionNext  " +serverdetail_page.getConnMessage());
        Assertion.assertEquals(serverdetail_page.getConnMessage(),
                "Connection name is required");

        Reporter.log("Entering addConnectionNext ");
        logger.info("Exiting addConnectionNext  ");
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
