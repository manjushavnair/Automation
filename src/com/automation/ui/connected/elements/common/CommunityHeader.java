package com.automation.ui.connected.elements.common;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.UserProfilePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.special.SpecialAdminDashboardPageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.special.SpecialSiteActivityPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CommunityHeader extends BasePageObject {

    @FindBy(css = ".wds-community-header__wordmark img")
    private WebElement wordmark;

    @FindBy(css = ".wds-community-header__sitename a")
    private WebElement wikiName;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"add-new-page\"]")
    private WebElement addNewPageButton;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"wiki-activity\"]")
    private WebElement wikiActivityButton;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"admin-dashboard\"]")
    private WebElement adminDashboardButton;

    @FindBy(css = ".wds-community-header .wds-tabs__tab #wds-icons-explore-tiny, .wds-community-header .wds-tabs__tab use[*|href=\"#wds-icons-explore-tiny\"]")
    private WebElement exploreTab;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-activity\"], .wds-dropdown a[data-tracking-label=\"explore-activity\"]")
    private WebElement exploreWikiActivityLink;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-random\"], .wds-dropdown a[data-tracking-label=\"explore-random\"]")
    private WebElement exploreRandomLink;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-community\"], .wds-dropdown a[data-tracking-label=\"explore-community\"]")
    private WebElement exploreCommunityLink;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-videos\"], .wds-dropdown a[data-tracking-label=\"explore-videos\"]")
    private WebElement exploreVideosLink;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-images\"], .wds-dropdown a[data-tracking-label=\"explore-images\"]")
    private WebElement exploreImagesLink;

    @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-forum\"]")
    private WebElement exploreForumLink;

    @FindBy(css = ".wds-community-header a[data-tracking=\"discuss\"], .wds-community-header a[data-tracking=\"forum\"]")
    private WebElement discussLink;

    @FindBy(css = ".wds-community-header .wds-avatar-stack__avatar a")
    private List<WebElement> avatars;

    @FindBy(css = ".wds-community-header")
    private WebElement communityHeader;

    @FindBy(css = ".wds-community-header__wiki-buttons .wds-dropdown")
    private WebElement moreToolsDropdown;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-add-new-image\"]")
    private WebElement moreToolsAddImageLink;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-add-new-video\"]")
    private WebElement moreToolsAddVideoLink;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-recent-changes\"]")
    private WebElement moreToolsRecentChanges;

    @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-all-shortcuts\"]")
    private WebElement moreToolsAllShortcuts;

    public boolean isVisible() {
        return this.isElementDisplayed(communityHeader);
    }


    public CreateArticleModalComponentObject clickAddNewPage() {
        wait.forElementClickable(addNewPageButton).click();

        Log.info("clicked Add New Page button");

        return new CreateArticleModalComponentObject(this.driver);
    }

    public SpecialSiteActivityPageObject clickWikiActivity() {
        wait.forElementClickable(wikiActivityButton).click();

        Log.info("clicked Wiki Activity Button");

        return new SpecialSiteActivityPageObject();
    }

    public SpecialAdminDashboardPageObject clickAdminDashboard() {
        wait.forElementClickable(adminDashboardButton).click();

        Log.info("clicked admin dashboard Button");

        return new SpecialAdminDashboardPageObject();
    }

    public UserProfilePage clickUserAvatar(int index) {
        wait.forElementClickable(avatars.get(index)).click();

        Log.info("clicked user avatar");

        return new UserProfilePage();
    }

    public String getUserNameFromAvatar(int index) {
        return avatars.get(index).getAttribute("title");
    }

    public CommunityHeader openExploreMenu() {
        new Actions(driver).moveToElement(exploreTab).perform();

        Log.info("explore dropdown opened");

        return this;
    }

    public SpecialSiteActivityPageObject clickExploreWikiActivityLink() {
        wait.forElementClickable(exploreWikiActivityLink);
        scrollAndClick(exploreWikiActivityLink);

        Log.info("explore -> wikiActivity link clicked");

        return new SpecialSiteActivityPageObject();
    }


    public void clickExploreCommunityLink() {
        wait.forElementClickable(exploreCommunityLink);
        scrollAndClick(exploreCommunityLink);

        Log.info("explore -> community link clicked");
    }

    public void clickExploreVideosLink() {
        wait.forElementClickable(exploreVideosLink);
        scrollAndClick(exploreVideosLink);

        Log.info("explore -> videos link clicked");
    }

    public void clickExploreImagesLink() {
        wait.forElementClickable(exploreImagesLink);
        scrollAndClick(exploreImagesLink);

        Log.info("explore -> images link clicked");
    }

    public void clickDiscussLink() {
        wait.forElementClickable(discussLink);
        scrollAndClick(discussLink);

        Log.info("discuss link clicked");
    }

    public void clickExploreForumLink() {
        wait.forElementClickable(exploreForumLink);
        scrollAndClick(exploreForumLink);

        Log.info("explore->forum link clicked");
    }

    public CommunityHeader openMoreToolsDropdown() {
        new Actions(driver).moveToElement(moreToolsDropdown).perform();

        Log.info("more tools dropdown opened");

        return this;
    }

    public void clickMoreAddImageLink() {
        wait.forElementClickable(moreToolsAddImageLink).click();

        Log.info("more -> Add image link clicked");
    }

    public AddMediaModalComponentObject clickMoreAddVideoLink() {
        wait.forElementClickable(moreToolsAddVideoLink).click();

        Log.info("more -> Add video link clicked");

        return new AddMediaModalComponentObject();
    }

    public void clickMoreRecentChanges() {
        wait.forElementClickable(moreToolsRecentChanges).click();

        Log.info("more -> Recent Changes link clicked");
    }

    public ActionExplorerModal clickMoreAllShortcuts() {
        wait.forElementClickable(moreToolsAllShortcuts).click();

        Log.info("more -> All shortcuts link clicked");

        return new ActionExplorerModal();
    }

    public boolean isDiscussLinkDisplayed() {
        return this.isElementDisplayed(discussLink);
    }

    public boolean isExploreForumLinkDisplayed() {
        return this.isElementDisplayed(exploreForumLink);
    }
}
