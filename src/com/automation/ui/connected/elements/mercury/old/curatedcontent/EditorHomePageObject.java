package com.automation.ui.connected.elements.mercury.old.curatedcontent;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform.SectionFormPageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform.SectionItemListPageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents the main editor view on mercury. The editor home is responsible for adding
 * top level curated content items
 */
public class EditorHomePageObject extends BasePageObject {

    @Getter(lazy = true)
    private final SectionItemListPageObject sectionItemList = new SectionItemListPageObject();
    @FindBy(css = ".sub-head--done")
    private WebElement publishButton;
    @FindBy(css = "section:nth-of-type(2) .curated-content-editor-add-item-btn")
    private WebElement addFeaturedContentButton;
    @FindBy(css = "section:nth-of-type(3) .curated-content-editor-add-item-btn")
    private WebElement addNewSectionButton;
    @FindBy(css = "section:nth-of-type(4) .curated-content-editor-add-item-btn")
    private WebElement addCategoryButton;

    public EditorHomePageObject open() {
        getUrl(urlBuilder.getUrl() + "/main/edit");

        return this;
    }

    public CuratedMainPagePageObject publish() {
        //NEEDTOCHECK
        wait.forElementVisibleW(publishButton);
        publishButton.click();

        return new CuratedMainPagePageObject();
    }

    public ItemFormPageObject clickAddFeaturedContent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addFeaturedContentButton);
        addFeaturedContentButton.click();

        return new ItemFormPageObject();
    }

    public SectionFormPageObject clickAddSection() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addNewSectionButton);
        addNewSectionButton.click();

        return new SectionFormPageObject();
    }

    public ItemFormPageObject clickAddCategory() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addCategoryButton);
        addCategoryButton.click();

        return new ItemFormPageObject();
    }

    public EditorHomePageObject waitForAddCategoryButtonToBeVisible() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addCategoryButton);

        return this;
    }
}
