package com.automation.ui.base.pageobjectsfactory.pageobject;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.contentpatterns.XSSContent;
import com.automation.ui.base.common.core.assertion.Assertion;
import com.automation.ui.base.common.core.CommonExpectedConditions;
import com.automation.ui.base.common.core.EmailUtils;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.element.JavascriptActions;
import com.automation.ui.base.common.core.element.Wait;
import com.automation.ui.base.common.core.element.alert.AlertHelper;
import com.automation.ui.base.common.core.element.button.ButtonHelper;
import com.automation.ui.base.common.core.element.checkbox.CheckBoxOrRadioButton;
import com.automation.ui.base.common.core.element.link.LinkHelper;
import com.automation.ui.base.common.core.element.textbox.TextBoxHelper;
import com.automation.ui.base.common.core.purge.PurgeMethod;
import com.automation.ui.base.common.core.url.Page;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.driverprovider.DriverProvider;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.common.utils.TimeZoneUtil;
import com.google.common.base.Function;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class BasePageObject {



    private static Logger logger = Logger.getLogger(BasePageObject.class);
    public final Wait wait;
    public WebDriverWait waitFor;
    public Actions builder;
    protected UIWebDriver driver = DriverProvider.getActiveDriver();
    protected int timeOut = 15;
    protected UrlBuilder urlBuilder = UrlBuilder.createUrlBuilder();
    protected JavascriptActions jsActions;

    protected LinkHelper linkHelper;
    protected TextBoxHelper textHelper;
    protected CheckBoxOrRadioButton crHelper;
    protected ButtonHelper btnHelper;
    protected AlertHelper alertHelper;

    public BasePageObject() {

        this.waitFor = new WebDriverWait(driver, timeOut);
        this.builder = new Actions(driver);
        this.wait = new Wait(driver);
        this.jsActions = new JavascriptActions(driver);

        linkHelper = new LinkHelper(driver, this);
        textHelper = new TextBoxHelper(driver, this);
        crHelper = new CheckBoxOrRadioButton(driver, this);
        btnHelper = new ButtonHelper(driver, this);
        alertHelper = new AlertHelper(driver, this);

        PageFactory.initElements(driver, this);

    }

    private static String getEmailChangeConfirmationLink(String email, String password) {
        String mailSubject = "Confirm your email address change ";
        String url = EmailUtils.getActivationLinkFromEmailContent(
                EmailUtils.getFirstEmailContent(email, password, mailSubject));
        Log.log("getActivationLinkFromMail",
                "activation link is visible in email content: " + url, true);
        return url;
    }

    public static String getPasswordResetLink(String email, String password) {
        String passwordResetEmail =
                EmailUtils.getFirstEmailContent(email, password, "Reset your  password");
        String resetLink = EmailUtils.getPasswordResetLinkFromEmailContent(passwordResetEmail);
        Log.log("Password reset link", "Password reset link received: " + resetLink,
                true);

        return resetLink;
    }

    public static String getEmailConfirmationLink(String email, String password) {
        String emailConfirmationMessage = EmailUtils.getFirstEmailContent(email, password,
                "Confirm your email and get started !");
        String confirmationLink =
                EmailUtils.getConfirmationLinkFromEmailContent(emailConfirmationMessage);
        Log.log("Email confirmation link",
                "Email confirmation link received: " + confirmationLink, true);

        return confirmationLink;
    }


    protected abstract String loginAs(String userName, String password, String siteURL);

    public String loginAs(User user) {

        return loginAs(user.getUserName(), user.getPassword(), urlBuilder.getUrl());
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void logoutFromAnywhere() {
        driver.get(URLsContent.USER_SIGNOUT);
    }

    /**
     * Refresh page, busting the cache( by adding cb=currentTimestamp )
     */
    public void refreshPageAddingCacheBuster() {
        driver.get(getUrlWithCacheBuster(driver.getCurrentUrl(), "cb=" + DateTime.now().getMillis() + "&AbTest.DISCUSSIONS_LIGHTWEIGHT_CONTRIBUTION_MENU=OLD_1"));
    }

    public String getUrlWithCacheBuster(String url, String queryString) {
        return urlBuilder.appendQueryStringToURL(url, queryString);
        //Temporary change until the outcome of experiment in IRIS-5829


    }

    //"script[src*='/scripts/beacon.js']";
    // wait for comscore to load

    public void waitForPageLoad() {
         wait.forElementPresent(By.className("logo"));
     }

    public BasePageObject waitForPageReload() {
        waitSafely(
                () -> wait.forElementVisible(By.className("logo"), Duration.ofSeconds(3)));

        waitSafely(() -> wait.forElementNotVisible(By.className("logo")),
                "Loading overlay still visible, page not loaded in expected time");

        logger.info("Loading overlay still visible, page not loaded in expected time");
        return this;
    }

    /**
     * Simple method for checking if element is on page or not. Changing the implicitWait value allows
     * us no need for waiting 30 seconds
     */
    public boolean isElementOnPage(By by) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return driver.findElements(by).size() > 0;
        } finally {
            restoreDefaultImplicitWait();
        }
    }


    protected Dimension getWindowSize() {
        return driver.manage().window().getSize();
    }


    /**
     * Simple method for checking if element is on page or not. Changing the implicitWait value allows
     * us no need for waiting 30 seconds
     */
    protected boolean isElementOnPage(WebElement element) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        boolean isElementOnPage = true;
        try {
            // Get location on WebElement is rising exception when element is not present
            element.getLocation();
        } catch (WebDriverException ex) {
            isElementOnPage = false;
        } finally {
            restoreDefaultImplicitWait();
        }
        return isElementOnPage;
    }

    /**
     * WebElement.isEnabled() method signature says that it returns true for anything except disabled
     * input fields. In order to check if non-input elements are disabled, "disabled" attribute value
     * must be checked and compared to "true" value
     *
     * @param element WebElement on the page
     * @return true if value of "disabled" attribute is different than "true"
     */
    protected boolean isElementEnabled(WebElement element) {
        return !"true".equals(element.getAttribute("disabled"));
    }

    /**
     * Method to check if WebElement is displayed on the page
     *
     * @return true if element is displayed, otherwise return false
     */

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    /**
     * Method to check if WebElement is displayed on the page
     *
     * @return true if element is displayed, otherwise return false
     */

    protected boolean isElementDisplayed(WebElement element, int timeout) {
        try {
            wait.forElementVisible(element, timeout);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementVisible(String element) {
        try {
            wait.forElementVisible(By.cssSelector(element));

        } catch (TimeoutException | ElementNotVisibleException ex) {
            logger.info("Web element " + element + " not visible");
            return false;
        }
        return true;
    }

    /**
     * Make sure element is ready to be clicked and click on it The separation of this method has
     * particular reason. It allows global modification of such click usages. This way it is very easy
     * to control what criteria have to be met in order to click on element
     *
     * @param element to be clicked on
     */
    protected void waitAndClick(WebElement element) {
        //logger.info("waitAndClick 1");
        wait.forElementClickable(element).click();
    }

    /**
     * Simple method for getting number of element on page. Changing the implicitWait value allows us
     * no need for waiting 30 seconds
     */
    protected int getNumOfElementOnPage(By cssSelectorBy) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        int numElementOnPage;
        try {
            numElementOnPage = driver.findElements(cssSelectorBy).size();
        } catch (WebDriverException ex) {
            numElementOnPage = 0;
        } finally {
            restoreDefaultImplicitWait();
        }
        return numElementOnPage;
    }

    protected void waitSafely(Runnable o) {
        waitSafely(o, "");
    }

    protected void waitSafely(Runnable o, String message) {
        try {
            o.run();
        } catch (TimeoutException e) {
            Log.log("Timed out waiting", String.format("%s\n%s", message, e), true);
        }
    }

    protected boolean isElementInContext(String cssSelector, WebElement element) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        boolean isElementInElement = true;
        try {
            if (element.findElements(By.cssSelector(cssSelector)).size() < 1) {
                isElementInElement = false;
            }
        } catch (WebDriverException ex) {
            isElementInElement = false;
        } finally {
            restoreDefaultImplicitWait();
        }
        return isElementInElement;
    }

    public void scrollTo(WebElement element) {
        jsActions.scrollElementIntoViewPort(element);
        wait.forElementClickable(element, 5);
    }

    protected void scrollAndClick(WebElement element) {
        jsActions.scrollElementIntoViewPort(element);
        wait.forElementClickable(element, 5);
        element.click();
    }

    protected void scrollAndClick(List<WebElement> elements, int index) {
        jsActions.scrollElementIntoViewPort(elements.get(index));
        wait.forElementClickable(elements, index, 5);
        elements.get(index).click();
    }

    protected void scrollAndClick(WebElement element, int offset) {
        jsActions.scrollToElement(element, offset);
        element.click();
    }

    public boolean isStringInURL(String givenString) {
        String currentURL = driver.getCurrentUrl();
        if (currentURL.toLowerCase().contains(givenString.toLowerCase())) {
            Log.log("isStringInURL",
                    String.format("Current url: %s contains given string: %s", currentURL, givenString),
                    true);
            return true;
        } else {
            Log.log("isStringInURL", String.format(
                    "Current url: %s does not contain given string: %s", currentURL, givenString), false);
            return false;
        }
    }

    /*

    public void waitForElement(WebElement element,int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250,TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}

    */

    protected By getElementLocator(Object obj, String element) throws SecurityException, NoSuchFieldException {
        Class childClass = obj.getClass();
        By locator = null;
        try {
            locator = getFindByAnno(childClass.
                    getDeclaredField(element).
                    getAnnotation(FindBy.class));
        } catch (SecurityException | NoSuchFieldException e) {
            logger.equals(e);
            throw e;
        }
        logger.debug(locator);
        return locator;
    }


    private By getFindByAnno(FindBy anno) {
        logger.info(anno);
        switch (anno.how()) {

            case CLASS_NAME:
                return new By.ByClassName(anno.using());
            case CSS:
                return new By.ByCssSelector(anno.using());
            case ID:
                return new By.ById(anno.using());
            case TAG_NAME:
                return new By.ByTagName(anno.using());
            case LINK_TEXT:
                return new By.ByLinkText(anno.using());
            case NAME:
                return new By.ByName(anno.using());
            case PARTIAL_LINK_TEXT:
                return new By.ByPartialLinkText(anno.using());
            case XPATH:
                return new By.ByXPath(anno.using());
            default:
                throw new IllegalArgumentException("Locator not Found : " + anno.how() + " : " + anno.using());
        }
    }

    public void verifyUrlContains(final String givenString, int timeOut) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl()
                    .toLowerCase().contains(givenString.toLowerCase()));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public void verifyURL(String givenURL) {
        Assertion.assertEquals(driver.getCurrentUrl(), givenURL);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void getUrl(String url) {


        logger.info("getUrl" + url);
        getUrl(url, false);
    }

    public void getUrl(String url, boolean makeScreenshot) {
        driver.get(url);
        if (makeScreenshot) {
            Log.log("Take screenshot",
                    String.format("Screenshot After Navigation to: %s", url), true, driver);
        }
    }

    public void getUrl(Page page) {
        getUrl(page.getUrl());
    }

    public void getUrl(Page page, String queryString) {
        getUrl(urlBuilder.appendQueryStringToURL(page.getUrl(), queryString));
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            Log.log("refreshPage", "page refreshed", true);
        } catch (TimeoutException e) {
            Log.log("refreshPage", "page loaded for more than 30 seconds after click",
                    true);
        }
    }

    public void waitForWindow(String windowName, String comment) {
        Object[] windows = driver.getWindowHandles().toArray();
        int delay = 500;
        int sumDelay = 500;
        while (windows.length == 1) {
            try {
                Thread.sleep(delay);
                windows = driver.getWindowHandles().toArray();
                sumDelay += 500;
            } catch (InterruptedException e) {
                Log.log(windowName, e, false);
            }
            if (sumDelay > 5000) {
                Log.log(windowName, comment, false);
                break;
            }
        }
    }

    protected void hover(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    protected void moveAway(WebElement element) {
        new Actions(driver).moveToElement(element, -200, 0).perform();
    }

    protected Boolean scrollToSelector(String selector) {
        if (isElementOnPage(By.cssSelector(selector))) {
            try {
                driver.executeScript(
                        "var x = $(arguments[0]);" + "window.scroll(0,x.position()['top']+x.height()+100);"
                                + "$(window).trigger('scroll');",
                        selector);
            } catch (WebDriverException e) {
                if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
                    Log.log("JSError", "JQuery is not defined", false);
                }
            }
            return true;
        } else {
            Log.log("SelectorNotFound", "Selector " + selector + " not found on page",
                    true);
            return false;
        }
    }

    // You can get access to hidden elements by changing class
    public void unhideElementByClassChange(String elementName, String classWithoutHidden,
                                           int... optionalIndex) {
        int numElem = optionalIndex.length == 0 ? 0 : optionalIndex[0];
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementsByName('" + elementName + "')[" + numElem
                + "].setAttribute('class', '" + classWithoutHidden + "');");
    }

    public void waitForElementNotVisibleByElement(WebElement element) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            waitFor.until(CommonExpectedConditions.invisibilityOfElementLocated(element));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public void waitForElementNotVisibleByElement(WebElement element, long timeout) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, timeout)
                    .until(CommonExpectedConditions.invisibilityOfElementLocated(element));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public void waitForValueToBePresentInElementsAttributeByCss(String selector, String attribute,
                                                                String value) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            waitFor.until(CommonExpectedConditions
                    .valueToBePresentInElementsAttribute(By.cssSelector(selector), attribute, value));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public void waitForValueToBePresentInElementsCssByCss(String selector, String cssProperty,
                                                          String expectedValue) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            waitFor.until(CommonExpectedConditions.cssValuePresentForElement(By.cssSelector(selector),
                    cssProperty, expectedValue));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public void waitForValueToBePresentInElementsAttributeByElement(WebElement element,
                                                                    String attribute, String value) {
        waitFor.until(
                CommonExpectedConditions.valueToBePresentInElementsAttribute(element, attribute, value));
    }

    public void waitForStringInURL(String givenString) {
        waitFor.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
        Log.log("waitForStringInURL", "verify that url contains " + givenString, true);
    }

    public String getRandomDigits(int length) {
        String timeStamp = TimeZoneUtil.getTimeStamp();
        int timeStampLenght = timeStamp.length();
        int timeStampCut = timeStampLenght - length;
        return timeStamp.substring(timeStampCut);
    }

    public void openSitePage() {
        getUrl(getSiteUrl() + URLsContent.NOEXTERNALS);
        Log.log("SitePageOpened", " page is opened", true);
    }

    public String getSiteUrl() {
        return UrlBuilder.createUrlBuilder().getUrl();
    }


    public String getSiteUrlWithPath() {
        return UrlBuilder.createUrlBuilder().getUrlForPath(URLsContent.SITE_CONTEXT);
    }

    public void fillInputAfterClear(WebElement input, String value) {

        input.clear();

        wait.forElementVisible(input).sendKeys(value);

    }

    public void clearFieldInput(WebElement input) {

        input.clear();


    }

    public void fillInput(WebElement input, String value) {
        wait.forElementVisible(input).sendKeys(value);
    }

    /**
     * Wait for new window present
     */
    public void waitForNewWindow() {
        waitFor.until(CommonExpectedConditions.newWindowPresent());
    }

    public void goToCurrentUrlWithSuffix(String additionToUrl) {
        driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), additionToUrl));
        Log.log("appendToUrl", additionToUrl + " has been appended to url", true);
    }

    public void goToCurrentUrlWithAppendedMultipleQueryStrings(String[] queryStrings) {
        String currentUrl = getCurrentUrl();
        for (String queryString : queryStrings) {
            currentUrl = urlBuilder.appendQueryStringToURL(currentUrl, queryString);
        }
        driver.get(currentUrl);
        Log.log("appendQueryToUrl", queryStrings + " have been appended to url", true);
    }

    public void pressDownArrow(WebElement element) {
        driver.executeScript(
                "var e = jQuery.Event(\"keydown\"); " + "e.which=40; $(arguments[0]).trigger(e);", element);
    }

    public void setDisplayStyle(String selector, String style) {
        driver.executeScript("document.querySelector(arguments[0]).style.display = arguments[1]",
                selector, style);
    }

    private void purge(String url) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest method = new PurgeMethod(url);
        try {
            int status = client.execute(method).getStatusLine().getStatusCode();
            if (status != HttpStatus.SC_OK && status != HttpStatus.SC_NOT_FOUND) {
                throw new Exception("HTTP PURGE failed for: " + url + "(" + status + ")");
            }
            Log.log("purge", url, true);
            return;
        } finally {
            client.close();
        }
    }

    /**
     * return status code of given URL
     */
    public int getURLStatus(String url) {
        try {
            purge(url);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.disconnect();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) "
                            + "Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            int status = connection.getResponseCode();
            connection.disconnect();
            return status;
        } catch (Exception e) {
            throw new WebDriverException(e);
        }
    }

    /**
     * check if current HTTP status of given URL is the same as expected
     */
    public void verifyURLStatus(int desiredStatus, String url) {
        int waitTime = 500;
        int statusCode = 0;
        boolean status = false;
        while (!status) {
            try {
                statusCode = getURLStatus(url);
                if (statusCode == desiredStatus) {
                    status = true;
                } else {
                    Thread.sleep(500);
                    waitTime += 500;
                }
                if (waitTime > 20000) {
                    break;
                }
            } catch (InterruptedException e) {
                throw new WebDriverException(e);
            }
        }
        Assertion.assertEquals(statusCode, desiredStatus);
        Log.log("verifyURLStatus", url + " has status " + statusCode, true);
    }

    protected void changeImplicitWait(int value, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(value, timeUnit == null ? TimeUnit.SECONDS : timeUnit);
    }

    protected void setShortImplicitWait() {
        changeImplicitWait(3, TimeUnit.SECONDS);
    }

    protected void restoreDefaultImplicitWait() {
        changeImplicitWait(timeOut, TimeUnit.SECONDS);
    }

    public void verifyUrlInNewWindow(String url) {
        waitForWindow("", "");
        Object[] windows = driver.getWindowHandles().toArray();
        driver.switchTo().window(windows[1].toString());
        waitForStringInURL(url);
        driver.close();
        driver.switchTo().window(windows[0].toString());
        Log.log("verifyUrlInNewWindow", "url in new window verified", true);
    }

    public void verifyElementMoved(Point source, WebElement element) {
        Point target = element.getLocation();
        if (source.x == target.x && source.y == target.y) {
            Assertion.fail("Element did not move. Old coordinate (" + source.x + "," + source.y + ") "
                    + "New coordinate (" + target.x + "," + target.y + ")");
        }
        Log.log("verifyElementMoved", "Element did move. From (" + source.x + ","
                + source.y + ") to (" + target.x + "," + target.y + ")", true, driver);
    }

    public void verifyElementResized(Dimension source, WebElement element) {
        Dimension target = element.getSize();
        int sourceWidth = source.width;
        int sourceHeight = source.height;
        int targetWidth = target.width;
        int targetHeight = target.height;

        if (sourceWidth == targetWidth && sourceHeight == targetHeight) {
            Assertion.fail("Element did not resize. Old dimension (" + sourceWidth + "," + sourceHeight
                    + ") " + "New dimension (" + targetWidth + "," + targetHeight + ")");
        }
        Log.log("verifyElementMoved", "Element did resize. From (" + sourceWidth + ","
                + sourceHeight + ") to (" + targetWidth + "," + targetHeight + ")", true, driver);
    }

    public String switchToNewBrowserTab() {
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        return driver.getCurrentUrl();
    }

    public Set<String> getWindowHandlens() {

        return driver.getWindowHandles();
    }


    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());
        driver.switchTo().window(windowsId.get(0));

    }

    public void switchToParentWithChildClose() {
        switchToParentWindow();

        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());

        for (int i = 1; i < windowsId.size(); i++) {

            driver.switchTo().window(windowsId.get(i));
            driver.close();
        }

        switchToParentWindow();
    }

    public void switchToWindow(int index) {

        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandlens());

        if (index < 0 || index > windowsId.size())
            throw new IllegalArgumentException("Invalid Index : " + index);

        driver.switchTo().window(windowsId.get(index));
        logger.info(index);
    }

    private int getTabsCount() {
        return driver.getWindowHandles().size();
    }

    private String getNewTab(String parentTab) {
        Optional<String> newTab = driver.getWindowHandles().stream()
                .filter(handleName -> !handleName.equals(parentTab)).findFirst();
        return newTab.orElseThrow(() -> new NotFoundException("New tab not found!"));
    }

    private String switchToNewTab(String parentTab) {
        String newTab = getNewTab(parentTab);
        driver.switchTo().window(newTab);
        return newTab;
    }

    private String getTabWithTitle(String title) {
        return getTabWithCondition(nameToTitle -> nameToTitle.getValue().startsWith(title));
    }

    private String getOtherTab(String title) {
        return getTabWithCondition(nameToTitle -> !nameToTitle.getValue().startsWith(title));
    }

    private String getTabWithCondition(
            java.util.function.Predicate<? super Pair<String, String>> condition) {
        Optional<String> newTab = driver.getWindowHandles().stream()
                .map(handleName -> Pair.of(handleName, driver.switchTo().window(handleName).getTitle()))
                .peek(handleTitle -> Log.log("Found window",
                        String.format("Window with title %s", handleTitle), true))
                .filter(condition).map(Pair::getKey).findFirst();
        return newTab
                .orElseThrow(() -> new NotFoundException("Tab that satisfies the condition doesn't exist"));
    }

    public WebDriver switchToWindowWithTitle(String title) {
        Log.log("Switching windows",
                String.format("Switching to window with title: %s", title), true);
        return driver.switchTo().window(getTabWithTitle(title));
    }

    public WebDriver switchAwayFromWindowWithTitle(String title) {
        Log.log("Switching windows",
                String.format("Switching away from window with title: %s", title), true);
        return driver.switchTo().window(getOtherTab(title));
    }

    public WebDriver switchToMainWindow() {
        return driver.switchTo().defaultContent();
    }




    private List<String> getTabUrls() {
        String currentTab = driver.getWindowHandle();
        List<String> result = new ArrayList<>();
        for (String windowHandler : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandler);
            result.add(driver.getCurrentUrl());
        }

        driver.switchTo().window(currentTab);
        return result;
    }

    public boolean tabContainsUrl(String url) {
        return getTabUrls().contains(url);
    }

    /**
     * Check for element is present based on locator
     * If the element is present return the web element otherwise null
     *
     * @param locator
     * @return WebElement or null
     */

    public WebElement getElement(By locator) {

        WebElement webElement = null;

        //NEED TO CHECK THIS IMPL
        if (isElementOnPage(locator)) {
            webElement = driver.findElement(locator);
            return webElement;
        }


        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            logger.error(re);
            throw re;
        }

    }

    /**
     * Check for element is present based on locator
     * If the element is present return the web element otherwise null
     *
     * @param locator
     * @return WebElement or null
     */

    public WebElement getElementWithNull(By locator) {

        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            // Ignore
        }
        return null;
    }

    public WebElement getElementByCssSelector(String elementName) {
        WebElement element = driver.findElement(By.cssSelector(elementName));

        return element;
    }


    public int getElementBottomPositionByCssSelector(String elementName) {
        WebElement element = driver.findElement(By.cssSelector(elementName));

        return element.getLocation().getY() + element.getSize().getHeight();
    }

    public int getElementTopPositionByCssSelector(String elementName) {
        WebElement element = driver.findElement(By.cssSelector(elementName));

        return element.getLocation().getY();
    }

    public void enterEmailChangeLink(String email, String password) {
        getUrl(getEmailChangeConfirmationLink(email, password));
    }

    public boolean isVisible(WebElement element) {
        boolean result;
        try {

            wait.forElementVisible(element);
            result = true;
        } catch (TimeoutException e) {
            Log.info("Element: " + element.toString() + " not found.", e);
            result = false;
        }
        return result;
    }

    public void openSitePage(String siteURL) {
        getUrl(siteURL);
        Log.log("openSitePage", "Site page is opened", true);
    }


    public enum PositionsVideo {
        LEFT, CENTER, RIGHT
    }
}
