package com.automation.ui.connected.pageobjectsfactory.pageobject.special.block;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SpecialBlockPage extends SiteBasePageObject {

    @FindBy(css = "input[name='wpTarget']")
    private WebElement userNameField;
    @FindBy(css = "select#mw-input-wpExpiry")
    private WebElement expiry;
    @FindBy(css = "#mw-input-wpExpiry-other")
    private WebElement expiryInput;
    @FindBy(css = "#mw-input-wpReason-other")
    private WebElement reasonInput;
    @FindBy(css = ".mw-htmlform-submit")
    private WebElement blockButton;
    @FindBy(css = ".mw-input [type='checkbox']")
    private List<WebElement> checkBoxes;

    public SpecialBlockPage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public SpecialBlockPage open() {
        return open(urlBuilder.getUrl());
    }

    public SpecialBlockPage open(String wikiUrl) {
        getUrl(wikiUrl + URLsContent.SITE_DIR + URLsContent.SPECIAL_BLOCK);
        Log.log("openSpecialBlockPage", "history page opened", true);
        //NEEDTOCHECK
        wait.forElementVisibleW(blockButton);

        return this;
    }

    public void typeInUserName(String userName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(userNameField);
        userNameField.sendKeys(userName);
    }

    public void selectExpiration(String period) {
        //NEEDTOCHECK
        wait.forElementVisibleW(expiry);
        Select exp = new Select(expiry);
        exp.selectByValue(period);
    }

    /**
     * @param period you can type here '5 min', '10 year', ...
     */
    public void typeExpiration(String period) {
        //NEEDTOCHECK
        wait.forElementVisibleW(expiryInput);
        expiryInput.sendKeys(period);
    }

    public void typeReason(String reason) {
        //NEEDTOCHECK
        wait.forElementVisibleW(reasonInput);
        reasonInput.sendKeys(reason);
    }

    public void clickBlockButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(blockButton);
        scrollAndClick(blockButton);
    }

    public void deselectAllSelections() {
        checkBoxes.stream()
                .filter(WebElement::isSelected)
                .forEach(WebElement::click);

        checkBoxes.stream()
                .map(WebElement::isSelected)
                .forEach(Assertion::assertFalse);

        Log.log("deselectAllSelections", "all selections deselected", true);
    }
}
