package com.automation.ui.connected.testcases.dashboardviewtest;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview.UAConnectionDetailsPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


public class UAConnectionDetailsPageTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(UAConnectionDetailsPageTest.class);

    UAConnectionDetailsPage connectiondetail_page=null;

    public UAConnectionDetailsPageTest() {

        super();
        logger.debug(" UAConnectionDetailsPageTest");
        connectiondetail_page = new UAConnectionDetailsPage();
    }




    /***
     *
     * @throws Throwable
     */
    @Test(enabled = true, priority = 0, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionDropdownTest() throws Throwable {


        logger.debug("Entering dcConnectionDropdownTest");
        connectiondetail_page.dcDropdown();
        logger.debug("Exiting dcConnectionDropdownTest");
       // //Thread.sleep(5000);
    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 1, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionPauseTest() throws Throwable {



       // //Thread.sleep(5000);
        logger.debug("Entering dcConnectionPauseTest");
        connectiondetail_page.dcDropdown();

        logger.debug("Exiting dcConnectionDropdownTest");
        logger.debug("Entering dcConnectionPauseTest");
        connectiondetail_page.dcPause();
        ////Thread.sleep(5000);
        logger.debug("Exiting dcConnectionPauseTest");
    }

    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 2, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionEditTest() throws Throwable {
        //Thread.sleep(5000);

        logger.debug("Entering dcConnectionDropdownTest 2");
        connectiondetail_page.dcDropdown();
        //Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDropdownTest 2");
        logger.debug("Entering dcConnectionEditTest");
        connectiondetail_page.dcEdit();
        //Thread.sleep(5000);
        logger.debug("Exiting dcConnectionEditTest");
    }


    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 3, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionRemoveTest() throws Throwable {

Thread.sleep(5000);
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

    @Test(enabled = true, priority = 4, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionDetailsTest() throws Throwable {


        logger.debug("Entering dcConnectionDetailsTest");
        connectiondetail_page.dcDetails();
        //Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDetailsTest");

    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 5, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionDetailsEditTest() throws Throwable {


        logger.debug("Entering dcConnectionDetailsEditTest");
        connectiondetail_page.dcDetails();
        //Thread.sleep(5000);
        connectiondetail_page.dcDetails_Edit();
        //Thread.sleep(5000);
        logger.debug("Exiting dcConnectionDetailsEditTest");

    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 6, groups = {"validcase"}, description = "UAConnectionDetailsPage")
    public void dcConnectionDetailsEdit_Tabs() throws Throwable {


        logger.debug("Entering dcConnectionDetailsEdit_Tabs");
        connectiondetail_page.dcDetails();
        connectiondetail_page.dcDetails_Edit();
        //Thread.sleep(3000);
        connectiondetail_page.dcDetails_Edit_Tag();
        //Thread.sleep(3000);
       ;
        connectiondetail_page.dcDetails_Edit_Preferances();
        //Thread.sleep(3000);

        connectiondetail_page.dcDetails_Edit_Server();
        //Thread.sleep(3000);
        logger.debug("Exiting dcConnectionDetailsEdit_Tabs");

    }


}
