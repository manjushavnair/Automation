package com.automation.ui.connected.testcases.home;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class HomePageTest extends ConnectedBaseTest {

    private static Logger logger = Logger
            .getLogger(HomePageTest.class);


    public HomePageTest() {

        super();
    }


    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 0, groups = {"validcase"}, description = "home page ")
    public void launchHomePage() throws Throwable {
        global_nav=new GlobalNavigationPage();
        home_page = new HomePage();
        // Thread.sleep(20000);


    }
    /***
     *
     * @throws Throwable
     */

    @Test(enabled = true, priority = 1, groups = {"validcase"}, description = "home page ")

    public void addConnection() throws Throwable {
        logger.debug("Entering addConnection");
        Reporter.log("Entering addConnection");
        home_page.addConnection( );

        logger.debug("Exiting addConnection and going to  add ");
    }



}
