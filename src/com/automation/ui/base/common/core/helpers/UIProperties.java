package com.automation.ui.base.common.core.helpers;

import com.automation.ui.base.common.core.element.JavascriptActions;
import org.openqa.selenium.WebDriver;

/**
 * Here you can find methods to get properties for a current site
 */
public class UIProperties {

    /**
     * Check if current site for children
     */
    public static boolean isSiteForChildren(WebDriver webDriver) {
        return "ec".equals(new JavascriptActions(webDriver).execute("ads.context.targeting.esrbRating"));
    }
}
