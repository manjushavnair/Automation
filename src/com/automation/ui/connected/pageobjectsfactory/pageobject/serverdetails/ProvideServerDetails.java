package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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




    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_SERVERTYPE)
    private WebElement connServerType;


    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_SERVERNAME)
    private WebElement connServerName;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_PORT)
    private WebElement connServerPort;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_SERVER_AUTHUSERNAME)
    private WebElement connServerAuthUserNAme;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_SERVER_AUTHPASSWORD)
    private WebElement connServerAuthPassword;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.CONNECTION_TO_SERVER)
    private WebElement connToServerLink;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.OPCUA_SERVER_URL)
    private WebElement opcuaServerURL;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.MIN_KEY_SIZE)
    private WebElement minKeySize;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.SECURITY_ENABLE_FLAG)
    private WebElement securityKeyFlag;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.SECURITY_CERT_ACCEPT)
    private WebElement securityKeyAccept;

    @FindBy(xpath = ProvideServerDetailsCONSTANTS.SECURITY_DOMAIN_CHECK)
    private WebElement securityDomainCheck;



    public ProvideServerDetails open() {
        // getUrl(  );
        //  waitForPageLoad();
        //driver.navigate().refresh();


        return new ProvideServerDetails();
    }

    /*Method to validate Connection cancel in Provide Server Details page*/
    public void provideServerDetailsCancel() {


        logger.info("Entering  provideServerDetailsCancel:" + cancelButton.getText() + ":: " + cancelButton.getTagName());
        Reporter.log("Entering  provideServerDetailsCancel:");

        try {

            waitAndClick(cancelButton);

            logger.info("Exiting  provideServerDetailsCancel");
            Reporter.log("Exiting  provideServerDetailsCancel");
        } catch (Exception e) {
            e.printStackTrace();


        }


    }

    /*Method to validate Next Connection in Provide Server Details page*/
    public void provideServerDetailsNext() {

        Reporter.log("Entering  provideServerDetailsNext:");

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


    public void clearCustomerName() {

        Reporter.log("Entering  clearCustomerName:");
        clearFieldInput(customerNameField);


    }

    /* Method to validate customer customer name error message*/
    public String customerNameErMsg() {

        wait.forElementVisible(customerNameErMsg);

        return customerNameErMsg.getText();

    }

    /*Method to clear Site name entry from Site name field*/
    public void clearSiteName() {

        Reporter.log("Entering  clearSiteName:");
        clearFieldInput(siteNameField);


    }

    /* Method to validate Site name error message*/
    public String siteNameErMsg() {

        wait.forElementVisible(siteNameErMsg);

        return siteNameErMsg.getText();

    }

    /* Method to enter Customer Name under Collector Details*/

    public void provideServerDetailsAddCustName() {


        Reporter.log("Entering  provideServerDetailsAddCustName:");

        try {

            customerNameField.click();
            fillInputAfterClear(customerNameField, "Connected Assets Demo");
            Reporter.log("Exiting provideServerDetailsAddCustName");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /* Method to enter site  Name under Collector Details*/

    public void provideServerDetailsAddSiteName() {


        Reporter.log("Entering  provideServerDetailsAddSiteName:");

        try {

            siteNameField.click();
            fillInputAfterClear(siteNameField, "Bracknell");
            Reporter.log("Exiting provideServerDetailsAddSiteName");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


     /* Method to enter site  Name under Collector Details*/

    public void provideServerDetailsConnDetails() {


        Reporter.log("Entering  provideServerDetailsConnDetails:");

        try {

            connServerName.click();
            fillInputAfterClear(connServerName, "Bracknell");
            Reporter.log("Exiting provideServerDetailsConnDetails");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
