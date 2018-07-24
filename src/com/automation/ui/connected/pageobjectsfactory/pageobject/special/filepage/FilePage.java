package com.automation.ui.connected.pageobjectsfactory.pageobject.special.filepage;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.actions.DeletePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class FilePage extends SiteBasePageObject {

    public static final int ABOUT_TAB = 0;
    public static final int HISTORY_TAB = 1;
    public static final int METADATA_TAB = 2;

    @FindBys(@FindBy(css = "ul.tabs li a"))
    private List<WebElement> tabList;
    @FindBy(css = ".fullImageLink")
    private WebElement fileEmbedded;
    @FindBys(@FindBy(css = ".tabs li"))
    private List<WebElement> tabs;
    @FindBy(css = "div#mw-imagepage-nofile")
    private WebElement noFileTextBox;
    @FindBy(css = "li#mw-imagepage-reupload-link a")
    private WebElement reuploadLink;
    @FindBy(css = "#wpWikiaVideoAddUrl")
    private WebElement uploadFileURL;
    @FindBy(css = "div.submits input")
    private WebElement addButton;
    @FindBys(@FindBy(css = "table.filehistory tr td:nth-child(1)>a"))
    private List<WebElement> historyDeleteLinks;
    @FindBy(css = ".boilerplate b")
    private WebElement imgLicensePlate;
    @FindBy(css = ".tabBody.selected")
    private WebElement tabBody;

    public FilePage open(String fileName, boolean noRedirect) {
        String url =
                urlBuilder.getUrl() + URLsContent.SITE_DIR + URLsContent.FILE_NAMESPACE + fileName;
        if (noRedirect) {
            url = urlBuilder.appendQueryStringToURL(url, "redirect=no");
        }
        getUrl(url);

        return this;
    }

    public FilePage open(String fileName) {
        return open(fileName, false);
    }

    public void clickTab(int tab) {
        WebElement currentTab = tabList.get(tab);
        //NEEDTOCHECK
        wait.forElementVisibleW(currentTab);
        scrollAndClick(currentTab);
        Log.log("clickTab", tab + " selected", true);
    }

    public void selectHistoryTab() {
        clickTab(HISTORY_TAB);
    }

    public void verifySelectedTab(String tabName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(tabBody);
        Assertion.assertEquals(tabBody.getAttribute("data-tab-body"), tabName);
        Log.log("verified selected tab", tabName + " selected", true);
    }

    public void refreshAndVerifyTabs(int tab) {

        String tabName;

        if (tab == ABOUT_TAB) {
            tabName = "about";
        } else if (tab == HISTORY_TAB) {
            tabName = "history";
        } else {
            tabName = "metadata";
        }

        clickTab(tab);
        verifySelectedTab(tabName);
        refreshPage();
        verifySelectedTab(tabName);
    }

    public void verifyEmbeddedVideoIsPresent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(fileEmbedded);
        Log.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible",
                true);
    }

    public boolean isNoFileTextBoxVisible() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(noFileTextBox);
            Log.log("isNoFileTextBoxVisible", "No-file textbox is visible ", true);
            return true;
        } catch (TimeoutException e) {
            Log.log("isNoFileTextBoxVisible", e, false);
            return false;
        }
    }

    public void verifyEmptyFilePage() {
        Assertion.assertEquals(isNoFileTextBoxVisible(), true);
        Log.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible",
                true);
    }

    public String getImageUrl() {
        return fileEmbedded.findElement(By.cssSelector("a")).getAttribute("href");
    }

    public String getImageThumbnailUrl() {
        return fileEmbedded.findElement(By.cssSelector("img")).getAttribute("src");
    }

    public void verifyTabsExistVideo() {
        String[] expectedTabs = {"about", "history", "metadata"};
        Assertion.assertEquals(expectedTabs.length, tabs.size());
        verifyTabsExist(expectedTabs);
    }

    public void verifyTabsExistImage() {
        String[] expectedTabs = {"about", "history"};
        int numberOfTabs = tabs.size();
        int expectedNumberOfTabs = expectedTabs.length;
        Assertion.assertTrue(numberOfTabs >= expectedNumberOfTabs,
                String.format("Number of tabs (%s) is not greater or equal to %s,", numberOfTabs, expectedNumberOfTabs));
        verifyTabsExist(expectedTabs);
    }

    public void verifyTabsExist(String[] expectedTabs) {
        for (int i = 0; i < expectedTabs.length; i++) {
            String tab = tabs.get(i).getAttribute("data-tab");
            Assertion.assertEquals(tab, expectedTabs[i]);
        }
    }

    public void replaceVideo(String url) {
        //NEEDTOCHECK
        wait.forElementVisibleW(reuploadLink);
        scrollAndClick(reuploadLink);

        uploadFileURL.sendKeys(url);
        Log.log("replaceVideo", url + " typed into url field", true);
        //NEEDTOCHECK
        wait.forElementVisibleW(addButton);
        scrollAndClick(addButton);
        Log.log("replaceVideo", "add url button clicked", true, driver);
    }

    public DeletePageObject deleteVersion(int num) {
        scrollAndClick(historyDeleteLinks.get(num - 1));

        Log.log("deletePage", "delete page opened", true);

        return new DeletePageObject(driver);
    }
}
