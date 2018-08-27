package com.automation.ui.githubtesting.pageobjectfactory.pageobject.login;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.githubtesting.common.prpreader.AssertDataReader;
import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.githubtesting.pageobjectfactory.pageobject.home.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(xpath = LoginCONSTANTS.USERID)
    @CacheLookup
    private WebElement username;

    @FindBy(xpath = LoginCONSTANTS.PASSWORD)
    @CacheLookup
    private WebElement password;

    @FindBy(xpath = LoginCONSTANTS.SUBMIT)
    @CacheLookup
    private WebElement login_button;


    public LoginPage() {
        super();
        logger.info("default constructor called");
        Reporter.log("default constructor called");
    }

    public LoginPage open() {

        logger.info("getCurrentUrl()" + getCurrentUrl());
        logger.info("getSiteUrl()" + getSiteUrl());
        logger.info("getUrl()" + getUrl());
        logger.info("getSiteUrlWithPath()" + getSiteUrlWithPath());


        if (Configuration.getEnvType().equals(EnvType.DEV)) {
            logger.info(getCurrentUrl());
            // getUrl("http://www.site.com");
            // getUrl(getCurrentUrl() + "?action=history");
            getUrl(getCurrentUrl());
        } else {
            logger.info(getSiteUrlWithPath());
            //  getUrl(getCurrentUrl() + "?action=something");
            //   getUrl(new Page(getSiteUrl(),"/login"));
            getUrl(getCurrentUrl() + "login");
            //for context added url like github.com/login
            //  getUrl(getSiteUrlWithPath());

        }
        return this;
    }


    public LoginPage enterUser(String userName) {
        try {
            logger.info("Entering enterUser  login: ");
            Reporter.log("Entering  enterUser login:");
            fillInputAfterClear(username, userName);

            logger.info("Exiting enterUser login");
            Reporter.log("Exiting  enterUser login");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exiting enterUser login" + AssertDataReader.assertreader.getValue("OPCUA_LOGIN_LOGINMSG"));
            Assert.fail(AssertDataReader.assertreader.getValue("OPCUA_LOGIN_LOGINMSG"));
            Reporter.log("Login failed");

        }
        return this;

    }

    public LoginPage enterPassword(String upassword) {
        try {
            logger.info("Entering  enterPassword: ");
            Reporter.log("Entering  enterPassword:");

            fillInputAfterClear(password, upassword);
            logger.info("Exiting  enterPassword");
            Reporter.log("Exiting  enterPassword");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(AssertDataReader.assertreader.getValue("OPCUA_PASSWORD"));
            Reporter.log("password failed");

        }
        return this;

    }


    public HomePage login() {
        try {
            logger.info("Entering  login: ");
            Reporter.log("Entering  login:");


            waitAndClick(login_button);

            logger.info("Exiting  login");
            Reporter.log("Exiting  login");
            // wait.forElementVisible(savepasswordbutton);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(AssertDataReader.assertreader.getValue("OPCUA_LOGIN_LOGINMSG"));
            Reporter.log("Login failed");

        }
        return new HomePage();

    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getLogin_button() {
        return login_button;
    }

    public void setLogin_button(WebElement login_button) {
        this.login_button = login_button;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;

    }


}
