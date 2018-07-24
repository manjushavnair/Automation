package com.automation.ui.connected.pageobjectsfactory.componentobject.wikitextshortcuts;

import com.automation.ui.connected.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SiteTextShortCutsComponentObject extends SourceEditModePageObject {

    @FindBy(css = "a[onclick*='Category']")
    private List<WebElement> categories;

    public SiteTextShortCutsComponentObject(WebDriver driver) {
        super();
    }

    public SourceEditModePageObject clickCategory(int index) {
        //NEEDTOCHECK
        wait.forElementVisibleW(categories.get(index));
        categories.get(index).click();
        return new SourceEditModePageObject();
    }
}
