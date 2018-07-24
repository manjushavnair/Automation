package com.automation.ui.connected.pageobjectsfactory;

import com.automation.ui.connected.common.constants.TestCONSTANTS;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AlertManagerPage extends SiteBasePageObject {

    private static Logger logger = Logger.getLogger(AlertManagerPage.class);
    //@FindBy(xpath = "//span[contains(@class,'name alerts alerts-click firepath-matching-node')]")
    @FindBy(xpath = "//span[@title='Alert Manager']")

    WebElement alertmanager;
    /**
     * 1. Search for the precreated alerts
     *
     * @throws InterruptedException
     */
    @FindBy(css = "input[class^='global-search-input']")
    //@FindBy(xpath = "//input[@class='global-search-input form-control ng-valid ng-dirty ng-pristine ng-touched']")
            WebElement searchAlert;

    // 1.Navigate toinvokeAlertManager page from home
    /**
     * 2. Click on New Alert
     *
     * @throws InterruptedException
     */

    @FindBy(xpath = "//span[@class='new-alert ng-binding']")
    WebElement newAlert;

    public AlertManagerPage() {

    }


    public void invokeAlertManager() {
        logger.info("Entering into invokeAlertManager");
        WebDriverWait wait = new WebDriverWait(driver,
                TestCONSTANTS.WAITTIME20SEC);
        wait.until(ExpectedConditions.textToBePresentInElement(alertmanager,
                "Alert Manager"));

        logger.info("Going to Click invokeAlertManager page");

        alertmanager.click();

        try {
            Thread.sleep(TestCONSTANTS.WAITTIME1000MILLISEC);
        } catch (Exception e) {
            Assert.fail("Unable to navigate to invokeAlertManager page");
            e.printStackTrace();
        }
        logger.info("In invokeAlertManager Page View");
    }


    public void newAlert() throws InterruptedException {

        logger.info("Going to click new Alert");
        Thread.sleep(TestCONSTANTS.WAITTIME10000MILLISEC);
        driver.manage()
                .timeouts()
                .implicitlyWait(TestCONSTANTS.WAITTIME10SEC,
                        TimeUnit.SECONDS);

        Actions builder = new Actions(driver);
        builder.moveToElement(newAlert).click();
        builder.perform();
        logger.info("New Alert window opened");
    }

}