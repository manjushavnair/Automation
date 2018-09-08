package com.automation.ui.connected.pageobjectsfactory.pageobject.dashboardview;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class UAConnectionDetailsPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(UAConnectionDetailsPage.class);

    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDROPDOWN)

    private WebElement dcDropdownButton;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONPAUSE)

    private WebElement dcPause;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONEDIT)

    private WebElement dcEdit;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONREMOVE)

    private WebElement dcRemove;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDETAILS)

    private WebElement dcDetails;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDETAILS_EDIT)

    private WebElement dcDetailsEdit;


    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDETAILS_PREF)
    private WebElement dcDetailsEditPref;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDETAILS_SERVER)
    private WebElement dcDetailsEditServer;
    @FindBy(xpath = UAConnectionDetailsCONSTANTS.DATACONNECTIONDETAILS_TAGS)
    private WebElement dcDetailsEditTags;


    public UAConnectionDetailsPage open() {


            logger.debug("open the URL" + getCurrentUrl());

            getUrl(getCurrentUrl());

            return this;
    }

    public void dcDropdown() {
        try {
            logger.debug("Click on DataConnection drop down");
            waitAndClick(dcDropdownButton);
           // wait.forElementVisible(dcDropdownButton, BASEConstants.WAITTIME1000MILLISEC);
           //dcDropdownButton.click();
         } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dcPause() {
        try {

            waitAndClick(dcPause);
           // logger.debug("Click on DataConnection Pause");
            //wait.forElementVisible(dcPause, BASEConstants.WAITTIME1000MILLISEC);
            //scrollAndClick(dcPause, 0);


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcEdit() {
        try {
            logger.debug("Click on DataConnection Edit 1");
          //  wait.forElementVisible(dcEdit, BASEConstants.WAITTIME10000MILLISEC);
            scrollAndClick(dcEdit, 1);
            logger.debug("Click on DataConnection Edit 2 ");
            waitAndClick(dcEdit);


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcRemove() {
        try {
            logger.debug("Click on DataConnection Remove");
          //  wait.forElementVisible(dcRemove, BASEConstants.WAITTIME10000MILLISEC);
            scrollAndClick(dcRemove, 2);
            waitAndClick(dcRemove);



        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcDetails() {
        try {
            logger.debug("Click on DataConnection Details");
          //  wait.forElementVisible(dcDetails, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetails);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcDetails_Edit() {
        try {
            logger.debug("Click on DataConnection Details");
           // wait.forElementVisible(dcDetailsEdit, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetailsEdit);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcDetails_Edit_Tag() {
        try {
            logger.debug("Click on dcDetails_Edit_Tag");
           // wait.forElementVisible(dcDetailsEditTags, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetailsEditTags);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcDetails_Edit_Preferances() {
        try {
            logger.debug("Click on dcDetails_Edit_Preferancess");
           // wait.forElementVisible(dcDetailsEditPref, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetailsEditPref);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void dcDetails_Edit_Server() {
        try {
            logger.debug("Click on dcDetails_Edit_Server");
           // wait.forElementVisible(dcDetailsEditServer, BASEConstants.WAITTIME10000MILLISEC);
            waitAndClick(dcDetailsEditServer);

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

}
