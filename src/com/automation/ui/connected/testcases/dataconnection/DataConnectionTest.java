/**
 *
 */
package com.automation.ui.connected.testcases.dataconnection;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.testcases.login.LoginTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.ui.base.common.constants.*;
public class DataConnectionTest extends LoginTest {

    private static Logger logger = Logger.getLogger(DataConnectionTest.class);
    private DataConnectionTest dataconnection_page;

    public DataConnectionTest() {

        super();
        System.out.println("DataConnectionTest called");
        dataconnection_page = PageFactory.initElements(
                driver, DataConnectionTest.class);

    }

    /*
     * Test Case 1
     * 1.Log into  UIAutomation
     * 2.Navigate to Alert Manager view
     * 3.Click on Alert Manager
     * 4. Search for an alert from existing ones
     * 5. Ensure the alert has been selected and displayed
     * */
    @Test(priority = 2)
    public void clickAlertManager() {

        try {

            logger.info("Entering into clickAlertManager ");

            Thread.sleep(BASEConstants.WAITTIME100000MILLISEC);

            dataconnection_page.clickAlertManager();

            logger.info("Exiting from clickAlertManager");

        } catch (Exception e) {
            Assert.fail("Unable to click the  AlertManager Links");
            e.printStackTrace();

        }

    }


}
