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
        logger.debug(" UAConnectionDetailsTest");
        connectiondetail_page = new UAConnectionDetails();
    }

    /***
     *
     * @throws Throwable
     */
    @Test(enabled = true, priority = 20, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDropdownTest() throws Throwable {


        logger.debug("Entering dcConnectionDropdownTest");
        connectiondetail_page.dcDropdown();
        logger.debug("Exiting dcConnectionDropdownTest");
        Thread.sleep(10000);
    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 21, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionPauseTest() throws Throwable {


        logger.debug("Entering dcConnectionPauseTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDropdownTest");
        logger.debug("Entering dcConnectionPauseTest");
        connectiondetail_page.dcPause();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionPauseTest");
    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 22, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionEditTest() throws Throwable {


        logger.debug("Entering dcConnectionEditTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDropdownTest");
        logger.debug("Entering dcConnectionEditTest");
        connectiondetail_page.dcEdit();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionEditTest");
    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 23, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionRemoveTest() throws Throwable {


        logger.debug("Entering dcConnectionDropdownTest");
        connectiondetail_page.dcDropdown();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDropdownTest");
        logger.debug("Entering dcConnectionRemoveTest");
        connectiondetail_page.dcRemove();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionRemoveTest");
    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 24, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsTest() throws Throwable {


        logger.debug("Entering dcConnectionDetailsTest");
        connectiondetail_page.dcDetails();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDetailsTest");

    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 25, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEditTest() throws Throwable {


        logger.debug("Entering dcConnectionDetailsEditTest");
        connectiondetail_page.dcDetails();
        Thread.sleep(5000);
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDetailsEditTest");

    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 26, groups = {"validcase"}, description = "UAConnectionDetails")
    public void dcConnectionDetailsEdit_Tabs() throws Throwable {


        logger.debug("Entering dcConnectionDetailsEdit_Tabs");
        connectiondetail_page.dcDetails();
        connectiondetail_page.dcDetails_Edit();
        Thread.sleep(3000);
        connectiondetail_page.dcDetails_Edit_Tag();
        Thread.sleep(3000);
       ;
        connectiondetail_page.dcDetails_Edit_Preferances();
        Thread.sleep(3000);

        connectiondetail_page.dcDetails_Edit_Server();
        Thread.sleep(3000);
        logger.debug("Exiting dcConnectionDetailsEdit_Tabs");

    }


}
