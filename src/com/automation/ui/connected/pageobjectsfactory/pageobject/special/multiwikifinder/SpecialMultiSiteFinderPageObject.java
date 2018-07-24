package com.automation.ui.connected.pageobjectsfactory.pageobject.special.multiwikifinder;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.common.dataprovider.SearchDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SpecialMultiSiteFinderPageObject extends SiteBasePageObject {

    private static final String LIST_OF_LINKS_SELECTOR = ".special > li > a";
    private static final String
            LINKS_LIMIT =
            "a[href*='/wiki/Special:Multiwikifinder?limit=%limit%']";

    @FindBy(css = "#mw-content-text input[type=submit]")
    private WebElement findButton;
    @FindBy(css = "input[name=target]")
    private WebElement enterPagenameField;
    @FindBy(css = ".mw-nextlink")
    private WebElement nextResultsButton;
    @FindBy(css = ".mw-prevlink")
    private WebElement previousResultsButton;
    @FindBys(@FindBy(css = ".special > li > a"))
    private List<WebElement> listOfLinks;

    public SpecialMultiSiteFinderPageObject(WebDriver driver) {
        super();
    }

    public void findPageName(String pagename) {
        //NEEDTOCHECK
        wait.forElementVisibleW(enterPagenameField);
        enterPagenameField.sendKeys(pagename);
        //NEEDTOCHECK
        wait.forElementVisibleW(findButton);
        findButton.click();
    }

    public void verifyEmptyPagename() {
        wait.forElementNotPresent(By.cssSelector(".mw-spcontent > p"));
        Log.log(
                "verifyEmptyPageName",
                "Empty pagename is not founded",
                true, driver
        );
    }

    public void compareResultsCount(int limit) {
        if (limit == 0) {
            wait.forElementNotPresent(By.cssSelector(LIST_OF_LINKS_SELECTOR));
            Log.log(
                    "verifyNoPagenameFounded",
                    "Not existing pagename is not founded",
                    true, driver
            );
        } else {
            //NEEDTOCHECK
            wait.forElementVisibleW(listOfLinks.get(0));
            Assertion.assertTrue(listOfLinks.size() <= limit);
        }
    }

    public void verifyPagination() {
        String firstLinkOnFirstPage = listOfLinks.get(0).getAttribute("href");
        String lastLinkOnFirstPage = listOfLinks.get(listOfLinks.size() - 1).getAttribute("href");
        nextResultsButton.click();
        waitForPageLoad();
        String firstLinkOnSecondPage = listOfLinks.get(0).getAttribute("href");
        String lastLinkOnSecondPage = listOfLinks.get(listOfLinks.size() - 1).getAttribute("href");
        Assertion.assertNotEquals(firstLinkOnSecondPage, firstLinkOnFirstPage);
        Assertion.assertNotEquals(lastLinkOnSecondPage, lastLinkOnFirstPage);
        previousResultsButton.click();
        waitForPageLoad();
        String firstLinkAfterBack = listOfLinks.get(0).getAttribute("href");
        String lastLinkAfterBack = listOfLinks.get(listOfLinks.size() - 1).getAttribute("href");
        Assertion.assertEquals(firstLinkAfterBack, firstLinkOnFirstPage);
        Assertion.assertEquals(lastLinkAfterBack, lastLinkOnFirstPage);
    }

    public void verifyAllLinksHavePagenameInPath(String pageName) {
        for (int i = 0; i < listOfLinks.size(); i++) {
            Assertion.assertTrue(listOfLinks.get(i).getAttribute("href").endsWith("/" + pageName));
        }
    }

    public void checkAllLimits() {
        for (int limit : SearchDataProvider.getSearchLimits()) {
            String limitCssSelector = LINKS_LIMIT.replace("%limit%", Integer.toString(limit));
            WebElement limitButton = driver.findElement(By.cssSelector(limitCssSelector));
            limitButton.click();
            compareResultsCount(limit);
        }
    }

}
