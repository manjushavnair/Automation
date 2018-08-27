package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.testcases.base.ServerType;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class AddServerDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddServerDetails.class);

    @FindBy(xpath = AddServerDetailsCONSTANTS.ADDCONNECTIONSCANELBUTTON)
    private WebElement cancelButton;

    @FindBy(xpath = AddServerDetailsCONSTANTS.ADDCONNECTIONSNEXTBUTTON)
    private WebElement nextButton;

    @FindBy(xpath = AddServerDetailsCONSTANTS.ADDCONNECTIONNAMEERROR)
    private WebElement nextButtonErrorMSG;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CUSOTMERNAME)
    private WebElement customerNameField;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CUSTOMERNAME_ERMSG)
    private WebElement customerNameErMsg;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SITENAME)
    private WebElement siteNameField;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SITENAME_ERMSG)
    private WebElement siteNameErMsg;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_SERVERTYPE)
    private WebElement connServerType;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_SERVERNAME)
    private WebElement connServerName;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_PORT)
    private WebElement connServerPort;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_SERVER_AUTHUSERNAME)
    private WebElement connServerAuthUserNAme;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_SERVER_AUTHPASSWORD)
    private WebElement connServerAuthPassword;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_TO_SERVER)
    private WebElement connToServerLink;

    @FindBy(xpath = AddServerDetailsCONSTANTS.OPCUA_SERVER_URL)
    private WebElement opcuaServerURL;

    @FindBy(xpath = AddServerDetailsCONSTANTS.MIN_KEY_SIZE)
    private WebElement minKeySize;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_ENABLE_FLAG)
    private WebElement securityKeyFlag;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_CERT_ACCEPT)
    private WebElement securityKeyAccept;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_DOMAIN_CHECK)
    private WebElement securityDomainCheck;


    public AddServerDetails open() {
        // getUrl(  );
        //  waitForPageLoad();
        //driver.navigate().refresh();


        return new AddServerDetails();
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

    /* Method to enter site  Name under Collector Details*/

    public void provideServerConnectionType(ServerType serverType) {


        Reporter.log("Entering  provideServerConnectionType:");

        try {

            //  WebElement selectedMedia = connServerType.get(2);

            wait.forElementClickable(connServerType);

            connServerType.click();

            Select select = new Select(connServerType);
            select.selectByVisibleText(serverType.getServerType());
            builder.moveToElement(connServerType).click(connServerType);
            builder.perform();
        } catch (NoSuchElementException e) {
            logger.info("provideServerConnectionType");
        } catch (Exception e) {
            logger.info("provideServerConnectionType");
        }
    }

    public void selectServerType(ServerType position) {
        wait.forElementVisible(connServerType);//.get(1));
        Select positionDropdown = new Select(connServerType);//.get(1));
        switch (position) {
            case OPCUA_SERVER:
                positionDropdown.selectByVisibleText(position.getServerType());
                break;
            case ODBC_COLLECTOR:
                positionDropdown.selectByVisibleText(position.getServerType());
                break;
            case OPCUA_UA_SERVER:
                positionDropdown.selectByVisibleText(position.getServerType());
                break;
            case PHD_SERVER:
                positionDropdown.selectByVisibleText(position.getServerType());
                break;
            case SDX_COLLECTOR:
                positionDropdown.selectByVisibleText(position.getServerType());
                break;
            default:
                throw new NoSuchElementException("Non-existing ServerType selected");
        }
    }


}
