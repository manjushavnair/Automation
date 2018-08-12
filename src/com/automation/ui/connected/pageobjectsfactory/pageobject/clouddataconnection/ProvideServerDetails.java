package com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection;

import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class ProvideServerDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(ProvideServerDetails.class);

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.ADDCONNECTIONSCANELBUTTON)
    private WebElement cancelButton;


    @FindBy(xpath = ProvideServerDetailsCONSTANTS.ADDCONNECTIONSNEXTBUTTON)
    private WebElement nextButton;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.ADDCONNECTIONNAMEERROR)
    private WebElement nextButtonErrorMSG;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CUSOTMERNAME)
    private WebElement customerNameField;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CUSTOMERNAME_ERMSG)
    private WebElement customerNameErMsg;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.SITENAME)
    private WebElement siteNameField;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.SITENAME_ERMSG)
    private WebElement siteNameErMsg;

    public ProvideServerDetails open( )
            {
      // getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();


        return new ProvideServerDetails();
    }

/*Method to validate Connection cancel in Provide Server Details page*/
    public void provideServerDetailsCancel()
    {


        logger.info("Entering  provideServerDetailsCancel:"+cancelButton.getText() +":: "+ cancelButton.getTagName() );
        Reporter.log("Entering  provideServerDetailsCancel:" );

        try {

            waitAndClick(cancelButton);

            logger.info("Exiting  provideServerDetailsCancel");
            Reporter.log("Exiting  provideServerDetailsCancel");
        } catch (Exception e) {
            e.printStackTrace();



        }


    }

    /*Method to validate Next Connection in Provide Server Details page*/
    public void provideServerDetailsNext()
    {

        Reporter.log("Entering  provideServerDetailsNext:" );

        try {

            waitAndClick(nextButton);

            Reporter.log("Exiting  provideServerDetailsNext");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getConnMessage() {
        wait.forElementVisible(nextButtonErrorMSG);

        return nextButtonErrorMSG.getText();
    }

    public boolean isButtonVisible() {
        wait.forElementVisible(cancelButton);

        return cancelButton.isDisplayed();
    }


    public void clearCustomerName()
    {

           Reporter.log("Entering  clearCustomerName:" );
           clearFieldInput(customerNameField);


    }

    /* Method to validate customer customer name error message*/
    public String customerNameErMsg() {

        wait.forElementVisible(customerNameErMsg);

        return customerNameErMsg.getText();

    }

/*Method to clear Site name entry from Site name field*/
    public void clearSiteName()
    {

        Reporter.log("Entering  clearSiteName:" );
        clearFieldInput(siteNameField);


    }
    /* Method to validate Site name error message*/
    public String siteNameErMsg() {

        wait.forElementVisible(siteNameErMsg);

        return siteNameErMsg.getText();

    }

    /* Method to enter Customer Name under Collector Details*/

    public void provideServerDetailsAddCustName()
    {


           Reporter.log("Entering  provideServerDetailsAddCustName:" );

        try {

            customerNameField.click();
            fillInputAfterClear(customerNameField,"Sivaprasad Inc");
            Reporter.log("Exiting provideServerDetailsAddCustName");

            } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
