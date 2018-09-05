package com.automation.ui.connected.testcases.globalnav;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav.*;

public class GlobalNavigationTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(GlobalNavigationTest.class);


    public GlobalNavigationTest() {

        super();
        logger.debug(" GlobalNavigationTest");
        global_nav  = new GlobalNavigation();
     }

    /***
     *
     * @throws Throwable
     */


    @Test(enabled = true, priority = 0, groups = {"validcase"}
             , description = "GlobalNavigationTest")
    public void logout() throws Throwable {
       global_nav.clickSignOut();

    }



}
