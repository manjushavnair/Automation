package com.automation.ui.connected.pageobjectsfactory.componentobject.activity;

import com.automation.ui.connected.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EditActivity extends Activity {

    @FindBy(css = "a.activityfeed-diff")
    private WebElement diffLink;

    public EditActivity(WebElement activityEntry) {
        super(activityEntry);
    }

    public DiffPagePageObject showChanges() {
        scrollAndClick(diffLink);
        return new DiffPagePageObject();
    }
}
