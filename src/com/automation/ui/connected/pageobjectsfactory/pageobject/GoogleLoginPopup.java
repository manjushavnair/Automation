package com.automation.ui.connected.pageobjectsfactory.pageobject;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleLoginPopup extends BasePageObject {

    @FindBy(css = "#Email")
    private WebElement usernameInput;

    @FindBy(css = "#Passwd")
    private WebElement passwordInput;

    @FindBy(css = "#signIn")
    private WebElement signInButton;

    @FindBy(css = "#next")
    private WebElement nextButton;

    public GoogleLoginPopup(WebDriver driver) {
        super();
    }

    public void SignInToGoogle(String userName, String password) {
        //NEEDTOCHECK
        wait.forElementVisibleW(usernameInput);

        usernameInput.sendKeys(userName);
        nextButton.click();
        //NEEDTOCHECK
        wait.forElementVisibleW(passwordInput);
        passwordInput.sendKeys(password);
        //NEEDTOCHECK
        wait.forElementVisibleW(signInButton);
        signInButton.click();
    }
}
