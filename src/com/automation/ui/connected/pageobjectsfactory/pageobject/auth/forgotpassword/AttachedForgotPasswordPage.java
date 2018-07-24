package com.automation.ui.connected.pageobjectsfactory.pageobject.auth.forgotpassword;


import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.FormPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.FormError;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedForgotPasswordPage extends BasePageObject implements ForgotPasswordPage {

    private static final String PASS_REQUESTED_SUCCESS = "Thanks! Please check your email.";
    @FindBy(id = "forgotPasswordSubmit")
    private WebElement requestLinkButton;
    @FindBy(id = "loginUsername")
    private WebElement usernameField;
    private AuthPageContext authContext;

    public AttachedForgotPasswordPage() {
        authContext = new AuthPageContext();
    }

    public void submit() {
        //NEEDTOCHECK
        wait.forElementVisibleW(requestLinkButton).click();
    }

    @Override
    public FormPage open() {
        driver.get(urlBuilder.getUrl() + URLsContent.USER_FORGOT_PASSWORD);
        return this;
    }

    @Override
    public boolean isDisplayed() {
        return authContext.isHeaderDisplayed();
    }

    @Override
    public boolean submitButtonNotClickable() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(requestLinkButton).isEnabled();
    }

    public void requestLinkForUsername(String username) {
        fillInput(usernameField, username);
        submit();
    }

    @Override
    public String getError() {
        return new FormError().getError();
    }

    protected boolean isConfirmationDisplayed() {
        return authContext.confirmationDisplayed(PASS_REQUESTED_SUCCESS);
    }

}
