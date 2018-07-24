package com.automation.ui.connected.elements.mercury.components.discussions.desktop;

import com.automation.ui.connected.elements.mercury.components.discussions.common.SortOption;
import com.automation.ui.connected.elements.mercury.pages.discussions.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SortingFilterDesktop extends BasePage {

    @FindBy(css = ".discussion-fieldset.sortby-fieldset")
    private WebElement fieldset;

    @FindBy(css = ".sort-latest")
    private WebElement latestFilter;

    @FindBy(css = ".sort-trending")
    private WebElement trendingFilter;

    public SortingFilterDesktop chooseSortingOption(SortOption option) {
        if (option == SortOption.LATEST) {
            waitAndClick(latestFilter);
        } else if (option == SortOption.TRENDING) {
            waitAndClick(trendingFilter);
        } else {
            throw new IllegalArgumentException(String.format("Option %s not supported in sorting", option));
        }
        return this;
    }

    public boolean isEnabled() {
        //NEEDTOCHECK
        wait.forElementVisibleW(fieldset);
        return isElementEnabled(fieldset);
    }

}
