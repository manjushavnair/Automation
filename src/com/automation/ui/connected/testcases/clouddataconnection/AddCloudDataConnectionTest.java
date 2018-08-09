package com.automation.ui.connected.testcases.clouddataconnection;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataConnection;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddCloudDataConnectionTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(AddCloudDataConnectionTest.class);


    public AddCloudDataConnectionTest() {
        super();

    }




    @Test(enabled = true, priority = 4,groups = {"validcase"}, description = "add conn page ")

    public void addConnecetion_Cancel() throws Throwable {
        logger.info("Entering addConnecetion_Cancel ");
        Reporter.log("Entering addConnecetion_Cancel ");
        data_page= new AddCloudDataConnection();
        data_page.addConnecetion_Cancel();
        data_page.waitForPageLoad();
        logger.info("Exiting addConnecetion_Cancel and going to  add" + data_page.getUrl());
    }




}
