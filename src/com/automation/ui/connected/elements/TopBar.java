package com.automation.ui.connected.elements;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class TopBar extends BasePageObject {

    @FindBy(css = ".wds-global-navigation")
    private WebElement navBar;

    @FindBy(css = ".wds-global-navigation__logo-image")
    private WebElement logoFandom;

    @FindBy(css = ".wds-global-navigation__modal-control-search")
    private WebElement searchIcon;

    @FindBy(css = ".wds-global-navigation__modal-control-search > svg.wds-icon")
    private WebElement searchIconClickableLink;

    @FindBy(css = ".wds-global-navigation__modal-control-close")
    private WebElement closeButton;

    @FindBy(css = ".wds-global-navigation__modal-control-search")
    private WebElement navMenu;

    @FindBy(css = ".wds-global-navigation__search-input-wrapper")
    private WebElement searchInput;

    @FindBy(css = ".wds-global-navigation__search-suggestions .wds-global-navigation__dropdown-link")
    private List<WebElement> searchSuggestions;

    private By navigationComponent = By.cssSelector(".wds-search-modal");
    private By parentBy = By.xpath("./..");



    public String typeInDesktopSearchAndSelectSuggestion(String query, int suggestionIndex) {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchInput);
        searchInput.sendKeys(query);

        WebElement selectedSearchSuggestion = searchSuggestions.get(suggestionIndex);
        wait.forElementClickable(selectedSearchSuggestion);

        String selectedSearchSuggestionText = selectedSearchSuggestion.getText();
        selectedSearchSuggestion.click();

        return selectedSearchSuggestionText;
    }





    public void clickFandomLogo() {
        Log.info("Click Wikia logoFandom");
        wait.forElementClickable(logoFandom).click();
    }


    public boolean isNavigationBarVisible() {
        try {
            return navBar.isDisplayed();
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public boolean isLogoVisible() {
        try {
            return logoFandom.isDisplayed();
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        }
    }


    public boolean isSearchIconVisible() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(searchIcon).isDisplayed();
    }

    public boolean isSearchIconClickable() {
        try {
            searchIconClickableLink.isDisplayed();
            wait.forElementClickable(searchIconClickableLink, 0);
            return true;
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        } catch (TimeoutException e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public boolean isCloseIconVisible() {
        try {
            return closeButton.isDisplayed();
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        }
    }
}
