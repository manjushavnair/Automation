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


    @Test(enabled = true, priority =4, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDropdownTest() throws Throwable {


        logger.info("Entering UAConnectionDetails");
       connectiondetail_page.dcDropdown();
       logger.info("Exiting provideServerDetailsCancel");
    }
}
