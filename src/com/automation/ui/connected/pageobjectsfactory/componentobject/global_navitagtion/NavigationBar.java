package com.automation.ui.connected.pageobjectsfactory.componentobject.global_navitagtion;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.interactions.Typing;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
 import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavigationBar extends SiteBasePageObject {

    final private String suggestionCss = ".autocomplete div";

    @FindBy(css = ".wds-global-navigation__search-toggle")
    private WebElement searchButton;
    @FindBy(css = ".wds-global-navigation__search-input-wrapper input")
    private WebElement searchInput;
    @FindBy(css = ".wds-global-navigation__search-submit")
    private WebElement searchSubmit;
    @FindBy(css = suggestionCss)
    private List<WebElement> suggestionsList;
    @FindBy(css = ".wds-global-navigation__user-menu.wds-global-navigation__user-anon")
    private WebElement anonAvatar;
    @FindBy(css = "a[data-tracking-label='account.sign-in']")
    private WebElement signInLink;
    @FindBy(css = "a[data-tracking-label='account.register']")
    private WebElement registerLink;

    private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

    public void triggerSuggestions(String query) {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchInput);
        searchInput.clear();
        wait.forElementClickable(searchInput);
        searchInput.click();
        wait.forElementPresent(jqueryAutocompleteBy);
        Typing.sendKeysHumanSpeed(searchInput, query);
        wait.forElementVisible(By.cssSelector(suggestionCss));
        //NEEDTOCHECK
        wait.forElementVisibleW(suggestionsList.get(0));
    }

    public void verifySuggestions(String suggestionText) {
        //NEEDTOCHECK
        wait.forElementVisibleW(suggestionsList.get(0));
        String allSuggestionTexts = "";
        for (int i = 0; i < suggestionsList.size(); i++) {
            if (suggestionsList.get(i).getAttribute("title") != null) {
                allSuggestionTexts += suggestionsList.get(i).getAttribute("title");
            }
        }
        Assertion.assertStringContains(allSuggestionTexts, suggestionText);
    }


    public void typeQuery(String query) {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchInput);
        searchInput.clear();
        searchInput.sendKeys(query);
        Log.log("typeQuery", "typed query: " + query, true);
    }




    public AttachedSignInPage clickOnSignIn() {
        anonAvatar.click();
        signInLink.click();
        return new AttachedSignInPage();
    }

    public AttachedRegisterPage clickOnRegister() {
        anonAvatar.click();
        registerLink.click();
        return new AttachedRegisterPage();
    }
}
