package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class FilterDetailsPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(FilterDetailsPage.class);
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
    @FindBy(xpath = FilterDetailsCONSTANTS.FILTER_NEWTAGFILTER)
    private WebElement filterNewTag;



    public FilterDetailsPage open() {

        return new FilterDetailsPage();
    }

    public void filterExportTag() {
        Reporter.log("Entering  filterExportTag:");
        try {
            wait.forElementVisible(filterExportTag);
            waitAndClick(filterExportTag);
            ;


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
        logger.debug("Entering  filterEditTag:");
        try {
            wait.forElementVisible(filterEditTag);
            waitAndClick(filterEditTag);

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
        logger.debug("Entering  listFilterTag");
         try {
             listTag.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    public void assetTreeTag() {
         logger.debug("Entering  assetTreeTag");
       try {
            assetTreeTag.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void allTag() {
        logger.debug("Entering  allTag");
        try {
            allTag.click();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
