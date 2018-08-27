package com.automation.ui.connected.testcases.dashboardviewtest;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview.UAConnectionDetails;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


public class UAConnectionDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(UAConnectionDetailsTest.class);


    public UAConnectionDetailsTest() {

        super();
        logger.info(" UAConnectionDetailsTest");
        connectiondetail_page = new UAConnectionDetails();
    }


    @Test(enabled = true, priority = 4, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDropdownTest() throws Throwable {


        logger.info("Entering dcConnectionDropdownTest");
        connectiondetail_page.dcDropdown();
        logger.info("Exiting dcConnectionDropdownTest");
        Thread.sleep(10000);
    }

    @Test(enabled = true, priority = 5, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionPauseTest() throws Throwable {


        logger.info("Entering dcConnectionPauseTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionDropdownTest");
        logger.info("Entering dcConnectionPauseTest");
        connectiondetail_page.dcPause();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionPauseTest");
    }


    @Test(enabled = true, priority = 6, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionEditTest() throws Throwable {


        logger.info("Entering dcConnectionEditTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionDropdownTest");
        logger.info("Entering dcConnectionEditTest");
        connectiondetail_page.dcEdit();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionEditTest");
    }
}
