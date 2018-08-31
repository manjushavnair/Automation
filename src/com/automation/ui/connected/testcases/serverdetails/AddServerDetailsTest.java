package com.automation.ui.connected.testcases.serverdetails;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.assertion.Assertion;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.common.dataprovider.SiteDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.AddServerDetails;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter.FilterDetails;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import com.automation.ui.connected.testcases.base.ServerType;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.*;


public class AddServerDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(AddServerDetailsTest.class);


    public AddServerDetailsTest() {

        super();
        logger.info(" AddServerDetailsTest");

        filter = new FilterDetails();
        serverdetail_page = new AddServerDetails();
        home_page = new HomePage();

    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 10, groups = {"validcase"}, description = "FilterDetails")
    public void provideServerDetailsCancelTest() throws Throwable {


        Reporter.log("Entering provideServerDetailsCancel ");

        home_page.addConnection();
        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        //    Assertion.assertEquals(subhead.getSubheadTitle(),  "Editing template: InfoboxBuilderChangeTemplateNameBySubhead"
        serverdetail_page.provideServerDetailsCancel();
        Reporter.log("Entering provideServerDetailsCancel ");

        logger.info("Exiting provideServerDetailsCancel  ");
    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 11, groups = {"validcase"}, description = "FilterDetails ")
    public void provideServerDetailsNextTest() throws Throwable {

        logger.info("Entering provideServerDetailsNext  ");
        Reporter.log("Entering provideServerDetailsNext ");
        home_page.addConnection();

        Assertion.assertTrue(serverdetail_page.isButtonVisible());
        serverdetail_page.provideServerDetailsNext();
        logger.info("Entering provideServerDetailsNext  " + serverdetail_page.getConnMessage());
        Assertion.assertEquals(serverdetail_page.getConnMessage(),
                "Connection name is required");

        Reporter.log("Entering provideServerDetailsNext ");
        logger.info("Exiting provideServerDetailsNext  ");
    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 12, groups = {"validcase"}, description = "FilterDetails ")
    public void clearCustomerNameTest() throws Throwable {

        home_page.addConnection();
        Reporter.log("Entering clearCustomerName ");
        serverdetail_page.clearCustomerName();
        serverdetail_page.provideServerDetailsNext();
        Assert.assertEquals(serverdetail_page.customerNameErMsg(),
                AssertDataReader.assertreader.getValue("CUSTOMERNAMEVALIDATIONMESSAGE"));

    }
    /***
     *
     * @throws Throwable
     * Test : Add Connection-&gt; locate customername field-&gt; clear the field-&gt; Add right name-&gt;Click next
     * */
    @Test(enabled = true, priority = 13, groups = {"validcase"}, description = "FilterDetails")
    public void addCustomerNameTest() throws Throwable {

        home_page.addConnection();

        serverdetail_page.provideServerDetailsAddCustName("Connected Assets Demo");
        serverdetail_page.provideServerDetailsNext();

        Thread.sleep(2000);

    }

    /***
     *Test : Add Connection-&gt;locate customername field-&gt; clear the field-&gt; Add right name-&gt;Click next
     * @throws Throwable
     */


    @Test(enabled = true, priority = 14, groups = {"validcase"}, description = "FilterDetails")
    public void clearSiteNameTest() throws Throwable {

        home_page.addConnection();

        serverdetail_page.clearSiteName();
        serverdetail_page.provideServerDetailsNext();


        Assert.assertEquals(serverdetail_page.siteNameErMsg(),
                AssertDataReader.assertreader.getValue("SITENAMEVALIDATIONMESSAGE"));

        //  Thread.sleep(5000);

    }


    /**
     * Test : Add Connection-&gt; locate customername field-&gt; clear the field-&gt; Add right name-&gt;Click next
     * */
    @Test(enabled = true, priority = 15, groups = {"validcase"}, description = "FilterDetails")
    public void provideConnectionType() throws Throwable {
        home_page.addConnection();
        serverdetail_page.provideServerConnectionType(ServerType.SDX_COLLECTOR);
        Thread.sleep(1000);

    }

       /** */
    @Test(enabled = true, priority = 16, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
             ,dataProvider = "getServerDetailsforOPCUA", description = "FilterDetails")
    public void provideAllServerDetails(Map<String, Object> connInfo) throws Throwable {
        home_page.addConnection();

        serverdetail_page.provideAllServerDetails(connInfo);



    }



}
