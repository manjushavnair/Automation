package com.automation.ui.base.common.core.element.checkbox;

import com.automation.ui.base.common.core.element.IHTMLComponent;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxOrRadioButton implements IHTMLComponent {

    private static Logger logger = Logger.getLogger(CheckBoxOrRadioButton.class);
    private WebDriver driver;
    private BasePageObject basePageObject;


    public CheckBoxOrRadioButton(WebDriver driver, BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject = basePageObject;
        logger.debug("CheckBoxOrRadioButtonHelper : " + this.driver.hashCode());
    }

    public void selectCheckBox(By locator) {

        //  selectCheckBox(driver.findElement(locator));
    }

    public void unSelectCheckBox(By locator) {

        //unSelectCheckBox(driver.findElement(locator));
    }

    public boolean isIselected(By locator) {

        return true;
        //isIselected(driver.findElement(locator));
    }

    public boolean isIselected(WebElement element) {
        boolean flag = element.isSelected();

        return flag;
    }

    public void selectCheckBox(WebElement element) {
        if (!isIselected(element))
            element.click();

    }

    public void unSelectCheckBox(WebElement element) {
        if (isIselected(element))
            element.click();

    }


}