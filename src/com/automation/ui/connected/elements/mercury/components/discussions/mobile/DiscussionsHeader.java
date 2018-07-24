package com.automation.ui.connected.elements.mercury.components.discussions.mobile;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DiscussionsHeader extends BasePageObject {

    @Getter(lazy = true)
    private final FiltersPopOver filtersPopOver = new FiltersPopOver();
    @FindBy(css = ".header-dropdown-button")
    private WebElement filterMenuButton;

    public FiltersPopOver openFilterMenu() {
        waitAndClick(filterMenuButton);
        return getFiltersPopOver();
    }
}
