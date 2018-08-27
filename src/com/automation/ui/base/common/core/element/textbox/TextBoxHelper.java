package com.automation.ui.base.common.core.element.textbox;


import com.automation.ui.base.common.core.element.IHTMLComponent;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TextBoxHelper implements IHTMLComponent {

    private static Logger logger = Logger.getLogger(TextBoxHelper.class);
    private WebDriver driver;
    private BasePageObject basePageObject;

    public TextBoxHelper(WebDriver driver, BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject = basePageObject;

    }

    public void sendKeys(By locator, String value) {

        basePageObject.getElement(locator).sendKeys(value);
    }

    public void clear(By locator) {
        basePageObject.getElement(locator).clear();
    }

    public String getText(By locator) {

        return basePageObject.getElement(locator).getText();
    }

    public void fillInputAfterClear(By locator, String value) {
        WebElement element = basePageObject.getElement(locator);
        element.clear();
        element.sendKeys(value);
        //wait.forElementVisible(input).sendKeys(value);

    }

    public void fillInputAfterClear(WebElement element, String value) {

        element.clear();
        element.sendKeys(value);
    }


}
