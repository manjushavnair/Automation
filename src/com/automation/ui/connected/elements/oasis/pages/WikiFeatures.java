package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WikiFeatures extends BasePageObject {

    @FindBy(css = "#WikiFeatures li[data-name=\"wgEnablePortableInfoboxEuropaTheme\"] .actions .button")
    private WebElement europaInfoboxThemeSlider;

    @FindBy(css = "#WikiFeatures li[data-name=\"wgEnablePortableInfoboxEuropaTheme\"] .actions .texton")
    private WebElement europaInfoboxThemeSliderText;

    @FindBy(css = "#DeactivateDialog")
    private WebElement deactivateDialog;

    @FindBy(css = "#DeactivateDialog > footer > div > button.button.normal.primary")
    private WebElement confirmDeactivation;

    @FindBy(css = ".feature")
    private List<WebElement> features;

    public WikiFeatures() {
        super();
    }

    public WikiFeatures openWikiFeatures(String wikiURL) {
        getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.SPECIAL_WIKI_FEATURES);
        Log.log("openWikiFeaturesPage", "special wiki features page opened", true, driver);

        return this;
    }

    public boolean isEuropaInfoboxThemeEnabled() {
        return "Enabled".equalsIgnoreCase(europaInfoboxThemeSliderText.getText());
    }

    public WikiFeatures enableEuropaInfoboxTheme() {
        wait.forElementClickable(europaInfoboxThemeSlider);
        europaInfoboxThemeSlider.click();

        return this;
    }

    public WikiFeatures disableEuropaInfoboxTheme() {
        if (this.isEuropaInfoboxThemeEnabled()) {
            wait.forElementClickable(europaInfoboxThemeSlider);
            europaInfoboxThemeSlider.click();

            wait.forElementClickable(confirmDeactivation);
            confirmDeactivation.click();
        }

        return this;
    }

}
