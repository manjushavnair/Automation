package com.automation.ui.connected.pageobjectsfactory.pageobject.home;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.base.common.core.element.JavascriptActions;
import com.automation.ui.connected.common.prpreader.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataCONSTANTS;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.AddCloudDataConnection;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginCONSTANTS;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(HomePage.class);

    @FindBy(css = "primary")
    private WebElement primarysButton;


    @FindBy(css = "a.logouts")
    private WebElement logoutLink;

    @FindBy(xpath = HomeConstants.LOGOUTBUTTON)
    @CacheLookup
    private WebElement logout;

    @FindBy(xpath = HomeConstants.MENUBUTTON)
    @CacheLookup
    private WebElement menubutton;


   // @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON)
   // private WebElement addButton;

     @FindBy(css = HomeConstants.ADDCONNECTIONSBUTTON)
     private WebElement addButton;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG)
    private WebElement msg;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG_EXTRA)
    private WebElement msgExtra;



    //for GITHUB testing
    @FindBy(css =HomeConstants.NEWREPOCREATION)
    private WebElement addRepo;

    /*public com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage open() {
        return open(Configuration.getSiteName());
    }

    public com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage open(String siteName) {
        getUrl(UrlBuilder.createUrlBuilderForSite(siteName).getUrl());
        waitForPageLoad();

        return this;
    }

    public com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage openAndWaitForGlobalShortcuts() {
        open();
        waitFor.until((Function<WebDriver, Boolean>) arg0 -> driver
                .executeScript(
                        "return typeof window.wgGlobalShortcutsLoaded !== 'undefined' && window.wgGlobalShortcutsLoaded")
                .equals(true));

        return this;
    }*/

    public HomePage() {
        super();
    }


    public void home() throws InterruptedException {

    }

    public HomePage open() {

        logger.info("open the URL"+getCurrentUrl());
       if (Configuration.getEnvType().equals(EnvType.DEV)) {

            logger.info(getCurrentUrl());
              getUrl("http://localhost:9000");
             // getUrl(getCurrentUrl());
        } else {
            logger.info(getCurrentUrl());
          // getUrl("http://localhost:9000");
            //  getUrl(getCurrentUrl() + " ");
            getUrl(getCurrentUrl());
        }
        //waitForPageLoad();




        return this;
    }


    //Go to LoginPage
    public void goToLogoutPage() {

         try {
            logger.info("Logging out of the URL ");
            Reporter.log("Logging out of the URL");
            // wait.forElementVisible(logout,BASEConstants.WAITTIME10000MILLISEC);

            logout.click();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail( AssertDataReader.readProperty().getValue("OPCUA_LOGOUT_LOGOUTMSG"));
            Reporter.log("Logged out of the URL successfully");

        }


    }

    public void addRepo( )
    {

      wait.forElementClickable(addRepo);
        addRepo.click();
        }

    public AddCloudDataConnection addConnection( ) {
        try {
            logger.info("Entering  addConnection: ");
            Reporter.log("Entering  addConnection:");
            logger.info("click  ");
          //  addButton.click();
          // wait.forElementClickable(addButton,BASEConstants.WAITTIME10000MILLISEC);
          //  waitAndClick(addButton);

           wait.forElementVisible(addButton,30);

          jsActions.click(addButton);

          Thread.sleep(30000);

       //   addButton.click();

            logger.info("clicked ");
       Thread.sleep(5000);

            logger.info("Exiting  addConnection");
            Reporter.log("Exiting  addConnection");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Add failed");
            Reporter.log("Add failed");


        }
        return new AddCloudDataConnection();

    }


    public void addConnection_accessdenied( ) {

            logger.info("Entering  addConnection_accessdenied: ");
            Reporter.log("Entering  addConnection_accessdenied:");
            try {
                Thread.sleep(10000);
            }catch(Exception e)
            {
                e.printStackTrace();
            }


        logger.info("accessdenied "+msg.getText());


        Assert.assertEquals( msg.getText(),
                AssertDataReader.readProperty().getValue("OPCUA_ACCESSDENIED"));

            logger.info("Exiting  addConnection_accessdenied");
            Reporter.log("Exiting  addConnection_accessdenied");


    }

}