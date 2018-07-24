/**
 *
 */
package com.automation.ui.connected.pageobjectsfactory.componentobject.editcategory;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCategoryComponentObject extends BasePageObject {

    @FindBy(css = ".WikiaForm .categoryName > input")
    private WebElement categoryNameField;
    @FindBy(css = "#categorySelectEditModal button.primary")
    private WebElement saveButton;

    public EditCategoryComponentObject(WebDriver driver) {
        super();
    }

  /*public ArticlePageObject editCategoryName(String newCategoryName) {
    //NEEDTOCHECK
    wait.forElementVisibleW(categoryNameField);
    categoryNameField.clear();
    categoryNameField.sendKeys(newCategoryName);
    saveButton.click();
    wait.forElementNotVisible(categoryNameField);
    Log.log("editCategoryName", "category name changed to " + newCategoryName, true);
    return new ArticlePageObject();
  }
  */
}
