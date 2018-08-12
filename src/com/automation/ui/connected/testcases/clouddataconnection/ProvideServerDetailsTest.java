package com.automation.ui.connected.testcases.clouddataconnection;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.*;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ProvideServerDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(ProvideServerDetailsTest.class);


    public ProvideServerDetailsTest() {

        super();
        logger.info(" ProvideServerDetailsTest");
        serverdetail_page=new ProvideServerDetails();
        home_page =new HomePage();

    }


    @Test(enabled = true, priority = 4,groups = {"validcase"}, description = "ProvideServerDetails")
    public void addConnectionCancelTest() throws Throwable {



        logger.info("Entering provideServerDetailsCancel " );
        Reporter.log("Entering provideServerDetailsCancel ");

        home_page.addConnection();
        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        //    Assertion.assertEquals(subhead.getSubheadTitle(),  "Editing template: InfoboxBuilderChangeTemplateNameBySubhead"
        serverdetail_page.provideServerDetailsCancel();
        Reporter.log("Entering provideServerDetailsCancel ");
        //  home_page.waitForPageLoad();
        logger.info("Exiting provideServerDetailsCancel  ");
    }

    @Test(enabled = true, priority = 3,groups = {"validcase"}, description = "home page ")
    public void addConnectionNextTest() throws Throwable {

        logger.info("Entering provideServerDetailsNext  " );
        Reporter.log("Entering provideServerDetailsNext ");
        home_page.addConnection();

        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        serverdetail_page.provideServerDetailsNext();
        logger.info("Entering provideServerDetailsNext  " +serverdetail_page.getConnMessage());
        Assertion.assertEquals(serverdetail_page.getConnMessage(),
                "Connection name is required");

        Reporter.log("Entering provideServerDetailsNext ");
        logger.info("Exiting provideServerDetailsNext  ");
    }

    private void verifyElementsVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertTrue(home_page.isElementVisible(
                element), element + " is not visible"));
    }

    private void verifyElementsNotVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertFalse(home_page.isElementVisible(
                element), element + " is visible"));
    }

    @Test(enabled = true, priority =1 ,groups = {"validcase"}, description = "ProvideServerDetails ")
    public void clearCustomerNameTest() throws Throwable {

        home_page.addConnection();
       Reporter.log("Entering clearCustomerName ");
        serverdetail_page.clearCustomerName();
        serverdetail_page.provideServerDetailsNext();


        Assert.assertEquals(serverdetail_page.customerNameErMsg(),
                AssertDataReader.assertreader.getValue("CUSTOMERNAMEVALIDATIONMESSAGE"));

        Thread.sleep(5000);

    }

/* test to validate site name ( clear site name and check the right error message)*/
@Test(enabled = true, priority =2 ,groups = {"validcase"}, description = "ProvideServerDetails ")
public void clearSiteNameTest() throws Throwable {

    home_page.addConnection();
    Reporter.log("Entering clearSiteName ");
    serverdetail_page.clearSiteName();
    serverdetail_page.provideServerDetailsNext();


    Assert.assertEquals(serverdetail_page.siteNameErMsg(),
            AssertDataReader.assertreader.getValue("SITENAMEVALIDATIONMESSAGE"));

    Thread.sleep(5000);

}







}
