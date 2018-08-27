package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.tags;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview.UAConnectionDetailsCONSTANTS;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(TagDetails.class);

    @FindBy(xpath = TagDetailsCONSTANTS.Tag)
    private WebElement dcDropdownButton;




    public TagDetails open() {


        return new TagDetails();
    }



    public void dcDetails() {
        try {
            logger.info("Click on DataConnection Details");
            wait.forElementVisible(dcDetails, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetails);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }


}
