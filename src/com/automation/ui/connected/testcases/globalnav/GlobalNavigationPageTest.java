package com.automation.ui.connected.testcases.globalnav;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav.*;

public class GlobalNavigationPageTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(GlobalNavigationPageTest.class);


    public GlobalNavigationPageTest() {

        super();
        logger.debug(" GlobalNavigationPageTest");
        global_nav  = new GlobalNavigationPage();
     }

    /***
     *
     * @throws Throwable
     */



    @Test(enabled = true, priority = 0, groups = {"validcase"}
            , description = "GlobalNavigationPageTest")
    public void cloudDataButton() throws Throwable {
        global_nav.cloudDataButton();



    }

    @Test(enabled = true, priority = 1, groups = {"validcase"}
             , description = "GlobalNavigationPageTest")
    public void logout() throws Throwable {
         global_nav.clickSignOut();



    }



}
