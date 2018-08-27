package com.automation.ui.connected.testcases.dashboardviewTest;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview.UAConnectionDetails;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.AddServerDetails;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import com.automation.ui.connected.testcases.base.ServerType;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
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
