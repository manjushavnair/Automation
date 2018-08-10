package com.automation.ui.base.common.core.element.dropdown;



import java.util.LinkedList;
import java.util.List;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.automation.ui.base.common.core.element.*;

public class DropDownHelper implements IHTMLComponent {


    private static Logger logger = Logger.getLogger(DropDownHelper.class);
    private WebDriver driver;

    private BasePageObject basePageObject;

    public DropDownHelper(WebDriver driver,BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject=basePageObject;
    }

    public void SelectUsingVisibleValue(By locator,String visibleValue) {
        SelectUsingVisibleValue(basePageObject.getElement(locator),visibleValue);
    }

    public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleValue);
     }

    public void SelectUsingValue(By locator,String value) {
        Select select = new Select(basePageObject.getElement(locator));
        select.selectByValue(value);
     }

    public void SelectUsingIndex(By locator,int index) {
        Select select = new Select(basePageObject.getElement(locator));
        select.selectByIndex(index);
     }

    public String getSelectedValue(By locator) {
         return getSelectedValue(basePageObject.getElement(locator));
    }

    public String getSelectedValue(WebElement element) {
        String value = new Select(element).getFirstSelectedOption().getText();
         return value;
    }


    public List<String> getAllDropDownValues(By locator) {
        Select select = new Select(basePageObject.getElement(locator));
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();

        for (WebElement element : elementList) {
             valueList.add(element.getText());
        }
        return valueList;
    }
}
