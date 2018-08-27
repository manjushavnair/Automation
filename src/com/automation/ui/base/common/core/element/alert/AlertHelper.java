package com.automation.ui.base.common.core.element.alert;


import com.automation.ui.base.common.core.element.IHTMLComponent;
import com.automation.ui.base.common.core.element.textbox.TextBoxHelper;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper implements IHTMLComponent {

    private static Logger logger = Logger.getLogger(TextBoxHelper.class);
    private WebDriver driver;

    private BasePageObject basePageObject;

    public AlertHelper(WebDriver driver, BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject = basePageObject;
    }

    public Alert getAlert() {

        return driver.switchTo().alert();
    }

    public void acceptAlert() {

        getAlert().accept();
    }

    public void dismissAlert() {

        getAlert().dismiss();
    }

    public String getAlertText() {
        String text = getAlert().getText();

        return text;
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();

            return true;
        } catch (NoAlertPresentException e) {
            // Ignore

            return false;
        }
    }

    public void acceptAlertIfPresent() {
        if (!isAlertPresent())
            return;
        acceptAlert();

    }

    public void dismissAlertIfPresent() {

        if (!isAlertPresent())
            return;
        dismissAlert();

    }

    public void acceptPrompt(String text) {

        if (!isAlertPresent())
            return;

        Alert alert = getAlert();
        alert.sendKeys(text);
        alert.accept();

    }
}
