package com.automation.ui.base.common.core.element.button;


import com.automation.ui.base.common.core.element.IHTMLComponent;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ButtonHelper implements IHTMLComponent {

    private static Logger logger = Logger.getLogger(ButtonHelper.class);
    private WebDriver driver;
    private BasePageObject basePageObject;


    public ButtonHelper(WebDriver driver, BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject = basePageObject;
    }

    public void click(By locator) {
        //click(driver.findElement(locator));
    }

    public void click(WebElement element) {
        element.click();
    }
}
