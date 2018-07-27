package com.automation.ui.connected.pageobjectsfactory.pageobject;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.elements.*;

import com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.automation.ui.connected.pageobjectsfactory.pageobject.notifications.NotificationsDropdown;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SiteBasePageObject extends BasePageObject {


    private static final String LOGGED_IN_USER_SELECTOR_OASIS =
            ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in img, "
                    + ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in svg";
    private static final String WDS_FOOTER_HEADER_CLASS = "wds-global-footer__header";
    private static final By MERCURY_SKIN = By.cssSelector("#ember-container");
    private static final By MERCURY_NAV_ICON = By.cssSelector(".site-head .site-head-icon-nav");
    private static final String LOGGED_IN_USER_SELECTOR_MERCURY =
            ".wikia-nav__avatar img[alt*=%userName%]";
    private static final By BANNER_NOTIFICATION_CONTAINER = By.cssSelector(".banner-notifications-placeholder,.smart-banner");
    private static final By BANNER_NOTIFICATION = By.cssSelector(".banner-notifications-placeholder div div");
    private static final By RECIRCULATION_PREFOOTER = By.cssSelector(".recirculation-prefooter");
    private static final By RECIRCULATION_PREFOOTER_FULFILLED = By.cssSelector(".recirculation-prefooter.has-items");
    @Getter(lazy = true)
    private final GlobalNavigation globalNavigation = new GlobalNavigation();

    @Getter(lazy = true)
    private final NotificationsDropdown notificationsDropdown = new NotificationsDropdown();

    @Getter(lazy = true)
    private final TopBar topBar = new TopBar();
    @FindBy(css = "body")
    protected WebElement body;
    @FindBy(css = ".page-header__title")
    protected WebElement articleTitle;
    @FindBy(css = "#ca-edit")
    protected WebElement editButton;
    @FindBy(css = "ul#pagehistory > li:first-child .comment")
    protected WebElement cssEditSummary;
    @FindBy(css = "ul#pagehistory > li:first-child .minoredit")
    protected WebElement cssMinorEdit;
    @FindBy(css = "#ca-watch")
    protected WebElement followButton;
    @FindBy(css = ".page-header__contribution-buttons .wds-button-group .wds-dropdown")
    protected WebElement articleEditDropdown;
    @FindBy(css = "#ca-delete")
    protected WebElement deleteDropdown;
    @FindBy(css = "#ca-protect")
    protected WebElement protectDropdown;
    @FindBy(css = "#ca-move")
    protected WebElement renameDropdown;
    @FindBy(css = "#ca-ve-edit")
    protected WebElement veEditButton;
    @FindBy(css = "body.ve")
    protected WebElement veMode;
    @FindBy(css = ".editsection a")
    protected List<WebElement> sectionEditButtons;
    @FindBy(css = "a.new[href$='redlink=1']")
    protected List<WebElement> redLinks;
    @FindBy(css = "body:not(.rte_source):not(.ve):not(.rte_wysiwyg)")
    protected WebElement srcOnlyMode;
    @FindBy(css = ".oo-ui-widget-enabled.ve-ui-wikiaFocusWidget")
    protected WebElement focusMode;
    @FindBy(css = ".ve-init-mw-viewPageTarget-toolbar")
    protected WebElement veToolMenu;
    @FindBy(css = "h3[id='headerWikis']")
    protected WebElement headerWhereIsMyExtensionPage;
    @FindBy(css = "#globalNavigation,.site-head.no-shadow,.wds-global-navigation")
    protected WebElement navigationBar;
    @FindBy(css = "#globalNavigation")
    protected WebElement newGlobalNavigation;
    protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");
    protected By parentBy = By.xpath("./..");
    protected String modalWrapper = "#WikiaConfirm";
    @FindBy(css = ".banner-notifications-placeholder,.smart-banner")
    private WebElement bannerNotificationContainer;
    @FindBys(@FindBy(css = ".wds-banner-notification"))
    private List<WebElement> notificationElements;
    @FindBy(css = "#WikiaArticle a[href*='Special:UserLogin']")
    private WebElement specialUserLoginLink;
    @FindBy(css = ".wds-dropdown__toggle .wds-avatar")
    private WebElement globalNavigationAvatar;
    @FindBy(className = WDS_FOOTER_HEADER_CLASS)
    private WebElement footer;
    @FindBy(css = ".wds-global-navigation")
    private WebElement globalNavigationBar;
    @FindBy(id = "recirculation-rail")
    private WebElement recirculationRightRailModule;


    private static final int TIMEOUT_PAGE_REGISTRATION = 3000;
    private static Logger logger = Logger.getLogger(SiteBasePageObject.class);

    public SiteBasePageObject() {
        super();
    }





    public String getWikiUrl() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.substring(0, currentURL.lastIndexOf("/wiki/"));
    }



    private void logMercuryUserId() {
        Object scriptOut = driver.executeScript("return window.M && window.M.prop('userId')");

        if (scriptOut != null) {
            Log.info("Mercury userID: " + scriptOut.toString());
        }
    }
    /**
     * Logout by clicking on "Sign out" option in global navigation
     */
    public void logOut() {
        try {
            getGlobalNavigation().clickSignOut();
        } catch (TimeoutException e) {
            Log.log("logOut", "page loads for more than 30 seconds", true);
        }
    }

    public boolean isBannerNotificationContainerPresent() {
        return isElementOnPage(BANNER_NOTIFICATION_CONTAINER);
    }

    public int getBannerNotificationsHeight() {
        return bannerNotificationContainer.getSize().getHeight();
    }

    public int getNavigationBarOffsetFromTop() {
        return Integer.parseInt(navigationBar.getAttribute("offsetTop")) + navigationBar.getSize().height;
    }

    public UserProfilePage openProfilePage(String userName, String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.USER_PROFILE.replace("%userName%", userName));
        return new UserProfilePage();
    }

    public String loginAs(User user) {
        return loginAs(user.getUserName(), user.getPassword(), urlBuilder.getUrl());
    }

    public String loginAs(String userName, String password, String wikiURL) {
        String token = Helios.getAccessToken(userName);

        driver.manage().addCookie(new Cookie("access_token", token,
                String.format(".%s", Configuration.getEnvType().getSiteDomain()), null, null));

        if (driver.getCurrentUrl().contains("Logout")) {
            driver.get(wikiURL);
        } else {
            refreshPageAddingCacheBuster();
        }

        this.verifyUserLoggedIn(userName);
        Log.info("loginCookie",
                "user was logged in by by helios using access token: " + token);
        logMercuryUserId();

        return token;
    }

    public void verifyUserLoggedIn(final String userName) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            if (driver.findElements(By.cssSelector("#PreviewFrame")).size() > 0) {
                driver.switchTo().frame("PreviewFrame");
            }
            // open nav if on mercury, required to see login data
            if (driver.findElements(MERCURY_SKIN).size() > 0) {
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
                wait.forElementVisible(By.cssSelector(
                        LOGGED_IN_USER_SELECTOR_MERCURY.replace("%userName%", userName.replace(" ", "_"))));
                // close nav on mercury
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
            } else {
                WebElement avatar = wait.forElementPresent(By.cssSelector(LOGGED_IN_USER_SELECTOR_OASIS));
                String loggedInUserName = avatar.getAttribute("alt");
                if (!loggedInUserName.equals(userName) && !loggedInUserName.equals(userName + " avatar")) {
                    throw new IllegalArgumentException(
                            "Invalid user, expected " + userName + ", but found: " + loggedInUserName);
                }
            }
        } finally {
            restoreDefaultImplicitWait();
            driver.switchTo().defaultContent();
        }
        Log.log("verifyUserLoggedIn", "user " + userName + " logged in", true);
    }

    public void verifyUserLoggedIn(User user) {
        this.verifyUserLoggedIn(user.getUserName());
    }


    public String getRightNavText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleTitle);
        return articleTitle.getText();
    }

    public String getFooterText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleTitle);
        return articleTitle.getText();
    }

    public String getLeftNavText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleTitle);
        return articleTitle.getText();
    }


    public String getHeaderText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleTitle);
        return articleTitle.getText();
    }

    public void verifyHeader(String fileName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleTitle);
        Assertion.assertStringContains(articleTitle.getText(), fileName);
    }



    public void verifyGlobalNavigation() {
        //NEEDTOCHECK
        wait.forElementVisibleW(globalNavigationBar);
        Log.log("verifyGlobalNavigation", "Verified global navigation", true);
    }

    public void verifyAvatarVisible() {
        //NEEDTOCHECK

        wait.forElementVisibleW(globalNavigationAvatar);
        Log.log("verifyAvatarVisible", "desired avatar is visible on navbar", true);
    }

    public void scrollToFooter() {
        //NEEDTOCHECK
        wait.forElementVisibleW(footer);
        //NEEDTOCHECK
        jsActions.scrollIntoView(footer);

        Log.log("scrollToFooter", "Scroll to the footer of the page", true);
    }

    protected Boolean isNewGlobalNavPresent() {
        return isElementOnPage(newGlobalNavigation);
    }

    public static class AssertionMessages {

        public static final String INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS =
                "Number of action confirming notifications is invalid";
        public static final String BANNER_NOTIFICATION_NOT_VISIBLE = "Banner notification message is not visible";

        private AssertionMessages() {
            throw new IllegalAccessError("Utility class");
        }
    }


     /*
    public HistoryPagePageObject openFileHistoryPage(String articlePage, String wikiURL) {
        getUrl(urlBuilder.appendQueryStringToURL(
                wikiURL + URLsContent.SITE_DIR + URLsContent.FILE_NAMESPACE + articlePage,
                URLsContent.ACTION_HISTORY));
        Log.log("openFileHistoryPage", "history page opened", true);
        return new HistoryPagePageObject();
    }

    public AttachedRegisterPage openSpecialUserSignUpPage(String wikiURL) {
        getUrl(wikiURL + URLsContent.USER_SIGNUP);
        Log.log("openSpecialUserSignUpPage", "Special:UserSignup page opened", true);
        return new AttachedRegisterPage();
    }

    public PreferencesPageObject openSpecialPreferencesPage(String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_PREFERENCES);
        Log.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
        return new PreferencesPageObject();
    }

    public AttachedSignInPage openSpecialUserLogin(String wikiURL) {
        getUrl(wikiURL + URLsContent.USER_LOGIN);
        Log.log("openSpecialUserLogin", "Special:UserLogin page opened", true);
        return new AttachedSignInPage();
    }



    public CreateNewSitePageObjectStep1 openSpecialCreateNewWikiPage(String wikiURL) {
        getUrl(wikiURL + URLsContent.SPECIAL_CREATE_NEW_WIKI);
        return new CreateNewSitePageObjectStep1();
    }

    protected void openArticleEditDropdown() {

        new Actions(driver).moveToElement(articleEditDropdown).perform();
    }

    public void openSpecialWatchListPage(String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_WATCHLIST);
    }



    public DeletePageObject deletePage() {
        String url =
                urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_DELETE);
        getUrl(url);
        Log.log("deletePage", "delete page opened", true);
        return new DeletePageObject(driver);
    }

    public List<Notification> getNotifications() {
        wait.forElementVisible(BANNER_NOTIFICATION);
        List<Notification> notificationList = new ArrayList<>();
        for (WebElement notificationElement : notificationElements) {
            Notification notification = new Notification(driver, notificationElement);
            notificationList.add(notification);
        }
        return notificationList;
    }

    public List<Notification> getNotifications(NotificationType notificationType) {
        List<Notification> notificationList = getNotifications();
        return notificationList.stream().filter(n -> n.getType().toLowerCase().contains(notificationType.getClassName()))
                .collect(Collectors.toList());
    }

    public boolean isNotificationPresent(NotificationType type, String message) {
        return getNotifications(type).stream().anyMatch(n -> n.getMessage().contains(message));
    }

    public void clickLoginOnSpecialPage() {
        //NEEDTOCHECK
        wait.forElementVisibleW(specialUserLoginLink);
        Log.log("Element found", "Link to login special page present", true);
        scrollAndClick(specialUserLoginLink);
    }




    public void verifyPageUnfollowed() {
        wait.forTextInElement(followButton, "Follow");
        Log.log("verifyPageUnfollowed", "page is not followed", true);
    }

    public void follow() {

        //NEEDTOCHECK
        wait.forElementVisibleW(followButton);
        jsActions.click(followButton);
        wait.forTextInElement(followButton, "Following");
        Log.log("followArticle", "page is followed", true, driver);
    }

    public WatchPageObject unfollowCurrentUrl() {
        driver.get(
                urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_UNFOLLOW));
        return new WatchPageObject();
    }

    public DeletePageObject deleteUsingDropdown() {
        this.openArticleEditDropdown();
        wait.forElementClickable(deleteDropdown);
        scrollAndClick(deleteDropdown);
        return new DeletePageObject(driver);
    }
    public String getNameForArticle() {
        return PageContent.ARTICLE_NAME_PREFIX + TimeZoneUtil.getTimeStamp();
    }

    protected String getPseudoElementValue(WebElement element, String pseudoElement, String cssValue) {
        return driver
                .executeScript("return getComputedStyle(arguments[0], arguments[1])[arguments[2]];",
                        element, pseudoElement, cssValue)
                .toString();
    }

    public void addVideoViaAjax(String videoURL) {
        String request =
                "$.ajax('" + getWikiUrl() + "wikia.php?controller=Videos&method=addVideo&format=json', {"
                        + "data: {url: '" + videoURL + "'}," + "type: 'POST' } );";
        jsActions.execute(request);
    }

    public void verifyVEPublishComplete() {
        waitForElementNotVisibleByElement(veMode);
        waitForElementNotVisibleByElement(focusMode);
        waitForElementNotVisibleByElement(veToolMenu);
        Log.log("verifyVEPublishComplete", "Publish is done", true, driver);
    }

    public SiteHistoryPageObject openArticleHistoryPage() {
        getUrl(urlBuilder.appendQueryStringToURL(getCurrentUrl(), URLsContent.ACTION_HISTORY));
        return new SiteHistoryPageObject();
    }

    public String getArticleName() {
        return (String) jsActions.execute(WikiaGlobalVariables.WG_PAGE_NAME);
    }

    public void verifyArticleNameInWgPageName(String targetText) {
        Assertion.assertStringContains(targetText, getArticleName());
        Log.log("verifyArticleNameInWgPageName",
                "The wgPageName variable contains article name" + targetText, true);
    }

    public void verifyNumberOfTop1kWikisInRange(Range expectedRange) {
        //NEEDTOCHECK
        wait.forElementVisibleW(headerWhereIsMyExtensionPage);
        Log.log("verifyNumberOfTop1kWikisInRange", "Verification of top 1k wikis", true, driver);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(headerWhereIsMyExtensionPage.getText());
        m.find();
        Assertion.assertTrue(
                expectedRange.contains(Integer.parseInt(m.group())),
                String.format("Number of Top 1k Wikis between %s and %s", expectedRange.getMinimum(), expectedRange.getMaximum())
        );
    }


    public void verifyRevisionMarkedAsMinor() {
        if (isElementOnPage(cssMinorEdit)) {
            Log.log("cssEditSummary", "minor edit is marked in first revision", true);
        } else {
            throw new NoSuchElementException("Minor Edit is not present on the page");
        }
    }


    public void scrollToRecirculationPrefooter() {
        wait.forElementPresent(RECIRCULATION_PREFOOTER);
        //NEEDTOCHECK
        jsActions.scrollIntoViewE(RECIRCULATION_PREFOOTER);
        wait.forElementPresent(RECIRCULATION_PREFOOTER_FULFILLED);

        Log.log("scrollToRecirculationPrefooter", "Scroll to the recirculation prefooter", true);
    }
 */
/*
  public VisualEditModePageObject openCKModeWithMainEditButton() {
    this.openArticleEditDropdown();
    editButton.click();
    Log.log("openCKModeWithMainEditButton", "CK main edit button clicked", true,
        driver);
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject openCKModeWithMainEditButtonDropdown() {
    this.openArticleEditDropdown();
    editButton.click();
    Log.log("openCKModeWithMainEditButton", "CK main edit button clicked", true,
                          driver);
    return new VisualEditModePageObject();
  }

  public VisualEditorPageObject openVEModeWithMainEditButton() {
    wait.forElementClickable(veEditButton);
    veEditButton.click();
    Log.log("openVEModeWithMainEditButton", "VE main edit button clicked", true,
        driver);
    return new VisualEditorPageObject();
  }

  public VisualEditorPageObject openVEModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    wait.forElementClickable(sectionEditButton);
    sectionEditButton.click();
    Log.log("openVEModeWithSectionEditButton",
        "VE edit button clicked at section: " + section, true, driver);
    return new VisualEditorPageObject();
  }

  protected VisualEditModePageObject openCKModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    //NEEDTOCHECK
    wait.forElementVisibleW(sectionEditButton);
    sectionEditButton.click();
    Log.log("openCKModeWithSectionEditButton",
                          "RTE edit button clicked at section: " + section, true, driver);
    return new VisualEditModePageObject();
  }


    public VisualEditorPageObject openNewArticleEditModeVisual(String wikiURL) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SITE_DIR + getNameForArticle(),
                                             URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject();
  }
  public VisualEditModePageObject navigateToArticleEditPage() {
    getUrl(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject navigateToArticleEditPage(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SITE_DIR + article,
        URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject navigateToUniqueArticleEditPage() {
    String title = String.format("%s%s", PageContent.ARTICLE_NAME_PREFIX, LocalDateTime.now());
    return navigateToArticleEditPage(getWikiUrl(), title);
  }

  public SourceEditModePageObject navigateToArticleEditPageSrc(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SITE_DIR + article,
        URLsContent.ACTION_EDIT));
    return new SourceEditModePageObject();
  }

  public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL,
      String article) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder
                                                 .appendQueryStringToURL(
                                                     wikiURL + URLsContent.SITE_DIR + article,
                                                     URLsContent.ACTION_EDIT),
                                             URLsContent.USE_DEFAULT_FORMAT));
    return new VisualEditModePageObject();
  }


  public VisualEditorPageObject openVEOnArticle(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SITE_DIR + article,
                                             URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject();
  }
*/
/*
  public SpecialVideosPageObject openSpecialVideoPage(String wikiURL) {

    getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_VIDEOS);
    return new SpecialVideosPageObject(driver);
  }

  public SpecialVideosPageObject openSpecialVideoPage() {
    return openSpecialVideoPage(getWikiUrl());
  }

  public SpecialVideosPageObject openSpecialVideoPageMostRecent(String wikiURL) {
    getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_VIDEOS + URLsContent.MOST_RECENT);
    return new SpecialVideosPageObject(driver);
  }

  public SpecialNewFilesPage openSpecialNewFiles(String wikiURL) {
    getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_NEW_FILES);
    return new SpecialNewFilesPage();
  }


  public SpecialNewFilesPage openSpecialNewFiles() {
    return openSpecialNewFiles(getWikiUrl() + URLsContent.SITE_DIR + URLsContent.SPECIAL_NEW_FILES);
  }
  public SourceEditModePageObject openCurrectArticleSourceMode() {
    String queryStrings[] = {URLsContent.ACTION_EDIT, URLsContent.SOURCE_MODE};
    goToCurrentUrlWithAppendedMultipleQueryStrings(queryStrings);
    return new SourceEditModePageObject();
  }

  public SourceEditModePageObject openSrcModeWithMainEditButton() {
    wait.forElementClickable(editButton);
    editButton.click();
    Log.log("openSrcModeWithMainEditButton", "Src main edit button clicked", true,
        driver);
    return new SourceEditModePageObject();
  }



  protected SourceEditModePageObject openSrcModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    //NEEDTOCHECK
    wait.forElementVisibleW(sectionEditButton);
    sectionEditButton.click();
    Log.log("openSrcModeWithSectionEditButton",
            "Src edit button clicked at section: " + section, true, driver);
    return new SourceEditModePageObject();
  }

  protected SourceEditModePageObject openSrcModeWithMainEditButtonDropdown() {
    this.openArticleEditDropdown();
    editButton.click();
    Log.log("openSrcModeWithMainEditButton", "Src main edit button clicked", true,
                          driver);
    return new SourceEditModePageObject();
  }
*/


}
