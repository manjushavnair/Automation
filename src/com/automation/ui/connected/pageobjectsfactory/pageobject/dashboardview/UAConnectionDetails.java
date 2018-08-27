package com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomeConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class UAConnectionDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(UAConnectionDetails.class);

    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDROPDOWN)
    private WebElement dcDropdownButton;

    /*@FindBy(xpath = UAConnectionDetailsCONSTANTS.ADDCONNECTIONSBUTTON_TOP)
    private WebElement addButtonTop;*/

    public UAConnectionDetails open() {


        return new UAConnectionDetails();
    }

    public void dcDropdown() {
        try {
            logger.info("Click on DataConnection drop down");
            wait.forElementVisible(dcDropdownButton, BASEConstants.WAITTIME10000MILLISEC);

            dcDropdownButton.click();

        } catch (Exception e) {
            e.printStackTrace();
           ;

        }
    }

    public void getServerDetailsCancel() {





    }



}
