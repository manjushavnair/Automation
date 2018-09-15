package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.manage;

import com.automation.ui.base.common.core.assertion.Assertion;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.testcases.base.ServerType;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;
import java.util.*;

public class AddServerDetailsPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddServerDetailsPage.class);

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

    @FindBy(xpath = AddServerDetailsCONSTANTS.ADDCONNECTIONNAME)
    private WebElement connName;



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

    @FindBy(xpath = AddServerDetailsCONSTANTS.QUERY)
    private WebElement tagQuery;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_NO)

    private WebElement securityNo;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_YES)
    private WebElement securityYes;

    @FindBy(xpath = AddServerDetailsCONSTANTS.SECURITY_CONNECTION_WARNING)

    private WebElement connWarning;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_STATUS)
    private WebElement connStatus;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_NEXT)

    private WebElement connNext;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_MAKEITPRIVATE)

    private WebElement connPrivate;

    @FindBy(xpath = AddServerDetailsCONSTANTS.CONNECTION_MAKEITPRIVATE_CLOSE)

    private WebElement connPrivateClose;



    public AddServerDetailsPage open() {
        // getUrl(  );
        //  waitForPageLoad();
        //driver.navigate().refresh();


        return new AddServerDetailsPage();
    }

    /*Method to validate Connection cancel in Provide Server Details page*/
    public void provideServerDetailsCancel() {


        logger.debug("Entering  provideServerDetailsCancel:" + cancelButton.getText() + ":: " + cancelButton.getTagName());
        Reporter.log("Entering  provideServerDetailsCancel:");

        try {

            waitAndClick(cancelButton);

            logger.debug("Exiting  provideServerDetailsCancel");
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
     //   wait.forElementVisible(nextButtonErrorMSG);

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

    public void provideServerDetailsAddCustName(String custName) {


        Reporter.log("Entering  provideServerDetailsAddCustName:");

        try {

            scrollTo(customerNameField);
            customerNameField.click();
            fillInputAfterClear(customerNameField, custName);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*public void provideServerSecurityCheckDisable( ) {


        Reporter.log("Entering  provideServerSecurityCheck:");
        logger.debug("Entering  provideServerSecurityCheck:");

        try {
          //  scrollTo(securityKeyFlag);
           // wait.forElementClickable(securityKeyFlag);
            boolean bValue = false;

            // This statement will return True, in case of first Radio button is selected

            bValue = securityKeyFlag.isEnabled();
            logger.debug("Entering  provideServerSecurityCheck:" +bValue);
            logger.debug("Entering  provideServerSecurityCheck:" +securityKeyFlag.isDisplayed());
            logger.debug("Entering  provideServerSecurityCheck:" +securityKeyFlag.getText());
            List <WebElement> ls=securityKeyFlag.findElements(By.name("es2"));
            int iSize = ls.size();
            logger.debug("Entering  provideServerSecurityCheck:size " +iSize);

            // Start the loop from first Check Box to last Check Boxe
            for(int i=0; i < iSize ; i++ ){
                // Store the Check Box name to the string variable, using 'Value' attribute
                String sValue = ls.get(i).getAttribute("value");
                logger.debug("Entering  provideServerSecurityCheck: sValue : " +sValue);


                // Select the Check Box it the value of the Check Box is same what you are looking for
                if (sValue.equalsIgnoreCase("Yes")){
                    ls.get(i).click();
                    // This will take the execution out of for loop
                    break;
                }
            }
            if(bValue)
                securityKeyFlag.click();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
*/






    /* Method to enter site  Name under Collector Details*/

    public void provideServerDetailsAddSiteName(String siteName) {


        Reporter.log("Entering  provideServerDetailsAddSiteName:");

        try {

            scrollTo(siteNameField);
            siteNameField.click();
            fillInputAfterClear(siteNameField, siteName);
            Reporter.log("Exiting provideServerDetailsAddSiteName");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void provideServerDetailsConnName(String connValue) {


        Reporter.log("Entering  provideServerDetailsConnName:");

        try {
            scrollTo(connName);
            connName.click();
            fillInputAfterClear(connName, connValue);
            Reporter.log("Exiting provideServerDetailsConnName");

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
            scrollTo(connServerType);

            connServerType.click();

            Select select = new Select(connServerType);
            select.selectByVisibleText(serverType.getServerType());
            builder.moveToElement(connServerType).click(connServerType);
            builder.perform();
        } catch (NoSuchElementException e) {
            logger.debug("provideServerConnectionType");
        } catch (Exception e) {
            logger.debug("provideServerConnectionType");
        }
    }



    public void provideServerDetailsClientServerName(String connServerValue) {


        Reporter.log("Entering  provideServerDetailsServerName:");

        try {

            scrollTo(connServerName);
            connServerName.click();
            fillInputAfterClear(connServerName, connServerValue);
            Reporter.log("Exiting provideServerDetailsServerName");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void chooseOPCUAServerType() {


        Reporter.log("Entering  chooseOPCUAServerType:");

        try {


            provideServerConnectionType (ServerType.OPCUA_UA_SERVER);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void chooseServerType() {


        Reporter.log("Entering  chooseServerType:");

        try {

           provideServerConnectionType(ServerType.SDX_COLLECTOR);


            provideServerConnectionType (ServerType.ODBC_COLLECTOR);

            provideServerConnectionType (ServerType.PHD_SERVER);

            provideServerConnectionType (ServerType.OPCUA_UA_SERVER);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void securityYes() {


        logger.debug("Entering  securityYes");

        try {

         securityYes.click();

            logger.debug("Exiting  securityYes");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void securityNo() {


        Reporter.log("Entering  securityNo:");

        try {

            securityNo.click();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void provideConnectAlertClose( ) {


        Reporter.log("Entering  provideConnectAlertClose:");

        try {
            scrollTo(connPrivateClose);
            waitAndClick(connPrivateClose);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void provideConnectAlertMakePrivate( ) {


        Reporter.log("Entering  provideConnectAlertMakePrivate:");

        try {
            scrollTo(connPrivate);
            waitAndClick(connPrivate);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void provideServerDetailsTagQueryName(String query) {


        Reporter.log("Entering  provideServerDetailsTagQueryName:");

        try {
            scrollTo(tagQuery);
            tagQuery.click();
            fillInputAfterClear(tagQuery, query);
            Reporter.log("Exiting provideServerDetailsTagQueryName");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }









    public void provideServerPort(String port) {


        Reporter.log("Entering  provideServerPort:");

        try {
            scrollTo(connServerPort);
            connServerPort.click();
            fillInputAfterClear(connServerPort, port);
            Reporter.log("Exiting provideServerPort");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

       public void provideServerURL(String url) {


        Reporter.log("Entering  provideServerPassword:");

        try {
            scrollTo(opcuaServerURL);
            opcuaServerURL.click();
            fillInputAfterClear(opcuaServerURL, url);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void provideConnectToServer( ) {


        Reporter.log("Entering  provideConnectToServer:");

        try {
            scrollTo(connToServerLink);
            waitAndClick(connToServerLink);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void provideConnectToNext( ) {


        Reporter.log("Entering  provideConnectToNext:");

        try {
            scrollTo(connNext);
            waitAndClick(connNext);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void provideServerUserName(String userName) {


        Reporter.log("Entering  provideServerUserName:");

        try {
            scrollTo(connServerAuthUserNAme);
            connServerAuthUserNAme.click();
            fillInputAfterClear(connServerAuthUserNAme, userName);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void provideServerPassword(String pass) {


        Reporter.log("Entering  provideServerPassword:");

        try {
            scrollTo(connServerAuthPassword);
            connServerAuthPassword.click();
            fillInputAfterClear(connServerAuthPassword, pass);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void  provideAllServerDetails(Map<String, Object> connInfo) {


        Reporter.log("Entering  provideAllServerDetails:");

        try {

           // String slotName = connInfo.get("custName").toString();

            provideServerDetailsAddCustName("Connected Assets Demo");

            provideServerDetailsAddSiteName ("Bracknell");

            provideServerDetailsConnName ("OPCUAUITEST");

            chooseOPCUAServerType();

            provideServerDetailsClientServerName("localhost");

            provideServerPort("9021");

            provideServerURL("opc.tcp://AS2CCHAPIOPCUA.hscperth.hsc.honeywell.com.au:53530/OPCUA/SimulationServer");


            securityNo();

            provideConnectAlertClose();

            provideServerUserName("Opcconfiguser");

            provideServerPassword("Password.123");

            provideConnectToServer();

           if (Assertion.assertStringContains(connStatus.getText(), "Authentication successful"))
            {
                provideConnectToNext();

            }




        } catch (Exception e) {
            e.printStackTrace();
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
