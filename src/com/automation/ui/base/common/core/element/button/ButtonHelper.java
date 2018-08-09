package com.automation.ui.base.common.core.element.button;


import com.automation.ui.base.common.core.element.textbox.TextBoxHelper;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.ui.base.common.core.element.*;


public class ButtonHelper implements IWebComponent {

    private WebDriver driver;

    private static Logger logger = Logger.getLogger(ButtonHelper.class);
    private BasePageObject basePageObject;


    public ButtonHelper(WebDriver driver,BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject=basePageObject;
     }

    public void click(By locator) {
        //click(driver.findElement(locator));
     }

    public void click(WebElement element){
        element.click();
     }
}
