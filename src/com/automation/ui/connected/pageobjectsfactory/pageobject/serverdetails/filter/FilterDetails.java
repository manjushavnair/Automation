package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class FilterDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(FilterDetails.class);
   @FindBy(xpath = FilterDetailsCONSTANTS.ALLTAG)
    private WebElement allTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.LISTOFTAG)
    private WebElement listTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.LISTPARTIALTAG)
    private WebElement partTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.FILTER_NEXT)
    private WebElement next;

    @FindBy(xpath = FilterDetailsCONSTANTS.CONNECTION_BACK)
    private WebElement back;




    public FilterDetails open() {

        return new FilterDetails();
    }

    /*Method to validate Next Connection in Provide Server Details page*/
    public void next() {

        Reporter.log("Entering  next:");

        try {

            waitAndClick(next);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void allTag() {


        logger.info("Entering  allTag");

        try {

         allTag.click();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
