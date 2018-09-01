package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
public class FilterDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(FilterDetails.class);
   @FindBy(xpath = FilterDetailsCONSTANTS.ALLTAG)
    private WebElement allTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.LISTOFTAG)
    private WebElement listTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.ASSETTREETAG)
    private WebElement assetTreeTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.FILTER_NEXT)
    private WebElement next;

    @FindBy(xpath = FilterDetailsCONSTANTS.CONNECTION_BACK)
    private WebElement back;

    @FindBy(xpath = FilterDetailsCONSTANTS.FILTEREDITTAG)
    private WebElement filterEditTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.FILTEREXPORTCONFIG)
    private WebElement filterExportTag;

    @FindBy(xpath = FilterDetailsCONSTANTS.FILTERIMPORTONFIG)
    private WebElement filterImportTag;



    public FilterDetails open() {

        return new FilterDetails();
    }

    public void filterExportTag() {
        Reporter.log("Entering  filterExportTag:");
        try {
            wait.forElementVisible(filterExportTag );
            logger.info("Entering  filterExportTag:");
             waitAndClick(filterExportTag);;
            Select select = new Select(filterExportTag);
            select.selectByVisibleText("Export Tags");
            builder.moveToElement(filterExportTag).click(filterExportTag);
            builder.perform();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterImportTag() {
        Reporter.log("Entering  filterImportTag:");
        try {
            waitAndClick(filterImportTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filterEditTag() {
       logger.info("Entering  filterEditTag:");
       try {
          wait.forElementVisible(filterEditTag );
           logger.info("Entering  filterEditTag:");
           waitAndClick(filterEditTag);
           logger.info("Exiting  filterEditTag:");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void next() {

        Reporter.log("Entering  next:");

        try {

            waitAndClick(next);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void listFilterTag() {


        logger.info("Entering  listFilterTag");

        try {

            listTag.click();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void assetTreeTag() {


        logger.info("Entering  assetTreeTag");

        try {

            assetTreeTag.click();




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
