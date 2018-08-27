package com.automation.ui.base.common.core.element.grid;

import com.automation.ui.base.common.core.element.IHTMLComponent;
import com.automation.ui.base.common.core.element.textbox.TextBoxHelper;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;


public class GridHelper implements IHTMLComponent {

    private static Logger logger = Logger.getLogger(TextBoxHelper.class);
    private WebDriver driver;

    private BasePageObject basePageObject;

    public GridHelper(WebDriver driver, BasePageObject basePageObject) {
        this.driver = driver;
        this.basePageObject = basePageObject;
    }


    //NEED TO CHECK THIS IMPL


    protected String getHeaderXpath(String tableIdoRxPath) {

        return basePageObject.isElementOnPage(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//thead" : tableIdoRxPath + "//thead";
    }

    protected String getTableBodyXpath(String tableIdoRxPath) {

        return basePageObject.isElementOnPage(By.id(tableIdoRxPath)) ? "//table[@id='" + tableIdoRxPath + "']//tbody" : tableIdoRxPath + "//tbody";
    }

    protected WebElement getGridElement(String tableIdoRxPath, int row, int column) {

        WebElement element;

        if ((element = basePageObject.getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]//input"))) != null) {

            return element;
        } else if ((element = basePageObject.getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/a"))) != null) {

            return element;
        } else if ((element = basePageObject.getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]/button"))) != null) {

            return element;
        } else if ((element = basePageObject.getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null) {

            return element;
        }
        return null;
    }

    protected int searchInGrid(String description, String tableIdoRxPath, int row, final int column) {

        WebElement element;

        while ((element = basePageObject.getElementWithNull(By.xpath(getTableBodyXpath(tableIdoRxPath) + "//tr[" + row + "]//td[" + column + "]"))) != null) {

            if (!element.getText().isEmpty()) {

                if (element.getText().trim().contains(description))
                    return row;
            }
            row++;
        }

        throw new IllegalArgumentException("No matching description found : " + description);
    }

    public List<String> getGridHeading(String tableIdoRxPath) {
        List<String> header = new LinkedList<String>();

        int j = 1;
        WebElement element;

        while ((element = (basePageObject.getElementWithNull(By.xpath(getHeaderXpath(tableIdoRxPath) + "//tr[1]//th[" + j + "]")))) != null) {
            if (!element.getText().isEmpty()) {
                header.add(element.getText().trim());

            }
            j++;
        }
        return header;
    }

    public String getGridColumnText(String tableIdoRxPath, int row, int column) {

        WebElement element = getGridElement(tableIdoRxPath, row, column);
        return element == null ? "" : element.getText().trim();
    }

    public void typeInGrid(String tableIdoRxPath, int row, int column, String value) {

        WebElement element = getGridElement(tableIdoRxPath, row, column);
        element.clear();
        element.sendKeys(value);
    }

    public void typeInGrid(String description, String tableIdoRxPath, int row, int column, String value) {

        int index = searchInGrid(description, tableIdoRxPath, row, column);
        typeInGrid(tableIdoRxPath, index, 3, value);
    }

}
