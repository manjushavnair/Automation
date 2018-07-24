package com.automation.ui.connected.pageobjectsfactory.pageobject.login;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage extends BasePageObject {


    private static Logger logger = Logger.getLogger(LoginPage.class);
    // Instead of above commented method we can call @FindBy

    @FindBy(xpath = LoginCONSTANTS.USERID)
    private WebElement username;

    @FindBy(xpath = LoginCONSTANTS.PASSWORD)
    private WebElement password;

    @FindBy(xpath = LoginCONSTANTS.SUBMIT)
    private WebElement login_button;



    @FindBy(xpath = LoginCONSTANTS.SAVELOGINANDSUBMIT)
    private WebElement savepasswordbutton;

    @FindBy(xpath =  LoginCONSTANTS.SAVELOGINCHECK)
    private WebElement choosetosave;

    public LoginPage() {
        super();
        logger.info("default constructor called");
        Reporter.log("default constructor called");
    }

    public LoginPage open() {
        if (Configuration.getEnvType().equals(EnvType.DEV)) {
            logger.info(getCurrentUrl());
            // getUrl("http://www.clayhut.co.in");
            //  getUrl(getCurrentUrl() + "?action=history");
            getUrl(getCurrentUrl());
        } else {
            logger.info(getCurrentUrl());
            //  getUrl(getCurrentUrl() + "?action=history");
            getUrl(getCurrentUrl());
        }

        return this;
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

    public LoginPage enterUser(String userName) {
        try {
            logger.info("Entering  login: ");
            Reporter.log("Entering  login:");
            logger.info("click  ");


            username.click();

            username.clear();

            username.sendKeys(userName);



             login_button.click();

            logger.info("Exiting  login");
            Reporter.log("Exiting  login");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Login failed");
            Reporter.log("Login failed");

        }
        return this;

    }

    public LoginPage enterPassword(String upassword) {
        try {
            logger.info("Entering  password: ");
            Reporter.log("Entering  password:");

            wait.forElementVisibleW(password);

            logger.info("wait  password: ");
            password.click();
            password.clear();



            password.sendKeys(upassword);

            logger.info("Exiting  password");
            Reporter.log("Exiting  password");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("password failed");
            Reporter.log("password failed");

        }
        return this;

    }


    public HomePage login() {
        try {
            logger.info("Entering  login: ");
            Reporter.log("Entering  login:");



            login_button.click();
            logger.info("Exiting  login");
            Reporter.log("Exiting  login");


           wait.forElementVisibleW(savepasswordbutton);


           savepasswordbutton.click();


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Login failed");
            Reporter.log("Login failed");

        }
        return new HomePage();

    }

}
