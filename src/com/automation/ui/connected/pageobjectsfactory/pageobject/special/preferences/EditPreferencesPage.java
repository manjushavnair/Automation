package com.automation.ui.connected.pageobjectsfactory.pageobject.special.preferences;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditPreferencesPage extends PreferencesPageObject {

    @FindBy(css = "select#mw-input-wpeditor")
    private WebElement preferredEditorDropdown;

    @FindBy(css = "input#mw-input-wpemailaddress")
    private WebElement emailAddressInput;

    private EditPreferencesPage openEditingSection(String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_EDITING_PREFERENCES);
        return this;
    }

    public EditPreferencesPage openEditingSection() {
        return openEditingSection(urlBuilder.getUrl());
    }

    private EditPreferencesPage openEmailSection(String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_EDITING_PREFERENCES_EMAIL);
        return this;
    }

    public EditPreferencesPage openEmailSection() {
        return openEmailSection(urlBuilder.getUrl());
    }

    public void selectPreferredEditor(String value) {
        wait.forElementClickable(preferredEditorDropdown);
        Select select = new Select(preferredEditorDropdown);
        select.selectByValue(value);
        Log.log("selectPreferredEditor", "Selected " + value + " from preference", true);
    }

    public EditPreferencesPage changeEmail(String value) {
        //NEEDTOCHECK
        wait.forElementVisibleW(emailAddressInput);
        emailAddressInput.clear();
        emailAddressInput.sendKeys(value);

        return this;
    }

    public String getEmailAdress() {
        //NEEDTOCHECK
        wait.forElementVisibleW(emailAddressInput);
        return emailAddressInput.getAttribute("value");
    }
}
