package com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform;

import com.automation.ui.connected.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.EditorHomePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SectionItemListPageObject extends CuratedEditorFormPageObject {

    @FindBy(css = ".curated-content-editor-add-item-btn")
    private WebElement addCategoryButton;
    @FindBy(css = ".curated-content-editor-row")
    private List<WebElement> item;

    private By itemDisplayNameLocator = By.cssSelector(".title");

    public EditorHomePageObject clickDone() {
        //NEEDTOCHECK
        wait.forElementVisibleW(doneButton);
        doneButton.click();

        return new EditorHomePageObject();
    }

    public SectionItemListPageObject verifyItem(String itemDisplayName) {
        WebElement innerElem;

        for (WebElement element : item) {
            //NEEDTOCHECK
            wait.forElementVisibleW(element);
            innerElem = element.findElement(itemDisplayNameLocator);

            if (innerElem.getText().equals(itemDisplayName)) {
                break;
            }
        }

        return this;
    }

    public CategoryFormPageObject clickAddCategory() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addCategoryButton);
        addCategoryButton.click();

        return new CategoryFormPageObject();
    }

    public SectionItemListPageObject waitForAddCategoryButtonToBeVisible() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addCategoryButton);

        return this;
    }
}
