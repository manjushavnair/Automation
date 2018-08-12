package com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.*;
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


    public ProvideServerDetails open( )
            {
      // getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();


        return new ProvideServerDetails();
    }


    public void addConnectionCancel()
    {


        logger.info("Entering  addConnectionCancel:"+cancelButton.getText() +":: "+ cancelButton.getTagName() );
        Reporter.log("Entering  addConnectionCancel:" );

        try {

            waitAndClick(cancelButton);

            logger.info("Exiting  addConnectionCancel");
            Reporter.log("Exiting  addConnectionCancel");
        } catch (Exception e) {
            e.printStackTrace();



        }


    }


    public void addConnectionNext()
    {


        logger.info("Entering  addConnectionNext:"+nextButton.getText() +":: "+ nextButton.getTagName() );
        Reporter.log("Entering  addConnectionNext:" );

        try {

            waitAndClick(nextButton);

            //

            //nextButtonErrorMSG


            logger.info("Exiting  addConnectionNext");
            Reporter.log("Exiting  addConnectionNext");
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






}
