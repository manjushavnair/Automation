package com.automation.ui.connected.pageobjectsfactory.pageobject.base;

import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class SiteBasePageObject extends BasePageObject {


    private static final String LOGGED_IN_USER_SELECTOR_OASIS =
            ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in img, "
                    + ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in svg";

    private static final By MERCURY_SKIN = By.cssSelector("#ember-container");
    private static final By MERCURY_NAV_ICON = By.cssSelector(".site-head .site-head-icon-nav");





    @FindBy(css = "#globalNavigation,.site-head.no-shadow,.wds-global-navigation")
    protected WebElement navigationBar;
    @FindBy(css = "#globalNavigation")
    protected WebElement newGlobalNavigation;
     protected By parentBy = By.xpath("./..");
     @FindBy(css = ".banner-notifications-placeholder,.smart-banner")
    private WebElement bannerNotificationContainer;


      @FindBy(css = ".wds-dropdown__toggle .wds-avatar")
    private WebElement globalNavigationAvatar;
    @FindBy(css = ".wds-global-navigation")
    private WebElement globalNavigationBar;



    private static Logger logger = Logger.getLogger(SiteBasePageObject.class);

    public SiteBasePageObject() {
        super();
    }




       //NEED TO CHANGE the context based on site context
     public String getContextUrl() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.substring(0, currentURL.lastIndexOf("/login"));
    }


    public String getUrl() {
        return driver.getCurrentUrl();
    }

    private void logUserId() {
        Object scriptOut = driver.executeScript("return window.M && window.M.prop('userId')");

        if (scriptOut != null) {
            Log.info("  userID: " + scriptOut.toString());
        }
    }
    /**
     * Logout by clicking on "Sign out" option in global navigation
     */
    public void logOut() {
        try {
           //  getGlobalNavigation().clickSignOut();
        } catch (TimeoutException e) {
            Log.log("logOut", "page loads for more than 30 seconds", true);
        }
    }


    public int getBannerNotificationsHeight() {
        return bannerNotificationContainer.getSize().getHeight();
    }

    public int getNavigationBarOffsetFromTop() {
        return Integer.parseInt(navigationBar.getAttribute("offsetTop")) + navigationBar.getSize().height;
    }


    public String loginAs(String userName, String password, String siteURL) {
        String token = Helios.getAccessToken(userName);

        driver.manage().addCookie(new Cookie("access_token", token,
                String.format(".%s", Configuration.getEnvType().getSiteDomain()), null, null));

        logger.info("user was logged in by helios using cookietoken: " + String.format(".%s", Configuration.getEnvType().getSiteDomain()));

        if (driver.getCurrentUrl().contains("Logout")) {
            driver.get(siteURL);
        } else {
            refreshPageAddingCacheBuster();
        }

        logger.info("user was logged in by helios using userName: " + userName);


        this.verifyUserLoggedIn(userName);
        Log.info("loginCookie",
                "user was logged in by helios using access token: " + token);
        logger.info("user was logged in by helios using access token: " + token);
        logUserId();

        return token;
    }

    public void verifyUserLoggedIn(final String userName) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            if (driver.findElements(By.cssSelector("#PreviewFrame")).size() > 0) {
                driver.switchTo().frame("PreviewFrame");
            }
            // open nav , required to see login data
            if (driver.findElements(MERCURY_SKIN).size() > 0) {
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
           //     wait.forElementVisible(By.cssSelector(
             //           LOGGED_IN_USER_SELECTOR_MERCURY.replace("%userName%", userName.replace(" ", "_"))));
                // close nav
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
            } else {
                WebElement logo = wait.forElementPresent(By.cssSelector(LOGGED_IN_USER_SELECTOR_OASIS));
                String loggedInUserName = logo.getAttribute("alt");
                if (!loggedInUserName.equals(userName) && !loggedInUserName.equals(userName + " logo")) {
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





    public void verifyGlobalNavigation() {
        //NEEDTOCHECK
        wait.forElementVisible(globalNavigationBar);
        Log.log("verifyGlobalNavigation", "Verified global navigation", true);
    }

    public void verifyAvatarVisible() {
        //NEEDTOCHECK

        wait.forElementVisible(globalNavigationAvatar);
        Log.log("verifyAvatarVisible", "desired logo is visible on navbar", true);
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
    public HistoryPagePageObject openFileHistoryPage(String articlePage, String siteURL) {
        getUrl(urlBuilder.appendQueryStringToURL(
                siteURL + URLsContent.SITE_DIR + URLsContent.FILE_NAMESPACE + articlePage,
                URLsContent.ACTION_HISTORY));
        Log.log("openFileHistoryPage", "history page opened", true);
        return new HistoryPagePageObject();
    }

    public AttachedRegisterPage openSpecialUserSignUpPage(String siteURL) {
        getUrl(siteURL + URLsContent.USER_SIGNUP);
        Log.log("openSpecialUserSignUpPage", "Special:UserSignup page opened", true);
        return new AttachedRegisterPage();
    }

    public PreferencesPageObject openSpecialPreferencesPage(String siteURL) {
        getUrl(siteURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_PREFERENCES);
        Log.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
        return new PreferencesPageObject();
    }

    public AttachedSignInPage openSpecialUserLogin(String siteURL) {
        getUrl(siteURL + URLsContent.USER_LOGIN);
        Log.log("openSpecialUserLogin", "Special:UserLogin page opened", true);
        return new AttachedSignInPage();
    }





    protected void openArticleEditDropdown() {

        new Actions(driver).moveToElement(articleEditDropdown).perform();
    }

    public void openSpecialWatchListPage(String siteURL) {
        getUrl(siteURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_WATCHLIST);
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





    public void verifyPageUnfollowed() {
        wait.forTextInElement(followButton, "Follow");
        Log.log("verifyPageUnfollowed", "page is not followed", true);
    }

    public void follow() {

        //NEEDTOCHECK
        wait.forElementVisible(followButton);
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
    wait.forElementVisible(sectionEditButton);
    sectionEditButton.click();
    Log.log("openCKModeWithSectionEditButton",
                          "RTE edit button clicked at section: " + section, true, driver);
    return new VisualEditModePageObject();
  }


    public VisualEditorPageObject openNewArticleEditModeVisual(String siteURL) {
    getUrl(urlBuilder.appendQueryStringToURL(siteURL + URLsContent.SITE_DIR + getNameForArticle(),
                                             URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject();
  }
  public VisualEditModePageObject navigateToArticleEditPage() {
    getUrl(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject navigateToArticleEditPage(String siteURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(siteURL + URLsContent.SITE_DIR + article,
        URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject navigateToUniqueArticleEditPage() {
    String title = String.format("%s%s", PageContent.ARTICLE_NAME_PREFIX, LocalDateTime.now());
    return navigateToArticleEditPage(getContextUrl(), title);
  }

  public SourceEditModePageObject navigateToArticleEditPageSrc(String siteURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(siteURL + URLsContent.SITE_DIR + article,
        URLsContent.ACTION_EDIT));
    return new SourceEditModePageObject();
  }

  public VisualEditModePageObject goToArticleDefaultContentEditPage(String siteURL,
      String article) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder
                                                 .appendQueryStringToURL(
                                                     siteURL + URLsContent.SITE_DIR + article,
                                                     URLsContent.ACTION_EDIT),
                                             URLsContent.USE_DEFAULT_FORMAT));
    return new VisualEditModePageObject();
  }


  public VisualEditorPageObject openVEOnArticle(String siteURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(siteURL + URLsContent.SITE_DIR + article,
                                             URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject();
  }
*/
/*
  public SpecialVideosPageObject openSpecialVideoPage(String siteURL) {

    getUrl(siteURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_VIDEOS);
    return new SpecialVideosPageObject(driver);
  }

  public SpecialVideosPageObject openSpecialVideoPage() {
    return openSpecialVideoPage(getContextUrl());
  }

  public SpecialVideosPageObject openSpecialVideoPageMostRecent(String siteURL) {
    getUrl(siteURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_VIDEOS + URLsContent.MOST_RECENT);
    return new SpecialVideosPageObject(driver);
  }

  public SpecialNewFilesPage openSpecialNewFiles(String siteURL) {
    getUrl(siteURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_NEW_FILES);
    return new SpecialNewFilesPage();
  }


  public SpecialNewFilesPage openSpecialNewFiles() {
    return openSpecialNewFiles(getContextUrl() + URLsContent.SITE_DIR + URLsContent.SPECIAL_NEW_FILES);
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
    wait.forElementVisible(sectionEditButton);
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
