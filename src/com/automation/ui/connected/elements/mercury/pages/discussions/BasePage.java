package com.automation.ui.connected.elements.mercury.pages.discussions;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;

import java.time.Duration;

public class BasePage extends SiteBasePageObject {

    private static final String
            EMBER_IS_LOADED_SELECTOR = ".application-wrapper.is-ember-loaded";

    private static final String
            TRANSITION_SPINNER_SELECTOR = ".wds-spinner__overlay";

    public void waitForEmberLoad() {
        wait.forElementPresent(By.cssSelector(EMBER_IS_LOADED_SELECTOR), 3);
    }

    public void waitForLoadingSpinner() {
        waitSafely(
                () -> wait.forElementVisible(
                        By.cssSelector(TRANSITION_SPINNER_SELECTOR),
                        Duration.ofSeconds(3)
                )
        );
        waitSafely(
                () -> wait.forElementNotVisible(By.cssSelector(TRANSITION_SPINNER_SELECTOR))
        );
    }
}
