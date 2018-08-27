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

    @Test(enabled = true, priority = 7, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionRemoveTest() throws Throwable {


        logger.info("Entering dcConnectionDropdownTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionDropdownTest");
        logger.info("Entering dcConnectionRemoveTest");
        connectiondetail_page.dcRemove();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionRemoveTest");
    }

    @Test(enabled = true, priority = 8, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsTest() throws Throwable {


        logger.info("Entering dcConnectionDetailsTest");
        connectiondetail_page.dcDetails();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionDetailsTest");

    }

    @Test(enabled = true, priority = 9, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEditTest() throws Throwable {


        logger.info("Entering dcConnectionDetailsEditTest");
        connectiondetail_page.dcDetails();
        Thread.sleep(5000);
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(5000);
        logger.info("Exiting dcConnectionDetailsEditTest");

    }

    @Test(enabled = true, priority = 10, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEditTags() throws Throwable {


        logger.info("Entering dcConnectionDetailsEditTags");
        connectiondetail_page.dcDetails();
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(3000);
        connectiondetail_page.dcDetails_Edit_Tag();
        Thread.sleep(3000);
        logger.info("Exiting dcConnectionDetailsEditTags");

    }

    @Test(enabled = true, priority = 11, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEditPref() throws Throwable {


        logger.info("Entering dcConnectionDetailsEditPref");
        connectiondetail_page.dcDetails();
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(3000);
        connectiondetail_page.dcDetails_Edit_Preferances();
        Thread.sleep(3000);
        logger.info("Exiting dcConnectionDetailsEditPref");

    }

    @Test(enabled = true, priority = 12, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEditServer() throws Throwable {


        logger.info("Entering dcConnectionDetailsEditServer");
        connectiondetail_page.dcDetails();
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(3000);
        connectiondetail_page.dcDetails_Edit_Server();
        Thread.sleep(3000);
        logger.info("Exiting dcConnectionDetailsEditServer");

    }
}
