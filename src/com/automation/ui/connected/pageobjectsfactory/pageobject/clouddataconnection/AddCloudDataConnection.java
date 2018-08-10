package com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.*;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomeConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class AddCloudDataConnection extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddCloudDataConnection.class);




    public AddCloudDataConnection open( )
            {
      // getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();


        return new AddCloudDataConnection();
    }







}
