package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.elements.oasis.components.templateclassificiation.TemplateClassification;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TemplateEditPage extends SiteBasePageObject {

    @FindBy(css = "#editarea")
    private WebElement editArea;

    @FindBy(css = "#PermissionsError")
    private WebElement permissionsErrorNotice;

    public TemplateEditPage() {
        super();
    }

    public TemplateEditPage open(String templateName) {
        getUrl(urlBuilder.appendQueryStringToURL(String.format("%s%s%s:%s", urlBuilder.getUrl(),
                URLsContent.SITE_DIR, URLsContent.TEMPLATE_NAMESPACE, templateName),
                URLsContent.ACTION_EDIT));

        return this;
    }

    public TemplateClassification getTemplateClassification() {
        return new TemplateClassification();
    }

    public boolean isEditAreaDisplayed() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(editArea);
            return editArea.isDisplayed();
        } catch (TimeoutException e) {
            Log.info("Edit are was not displayed");
        }
        return false;
    }

    public boolean isEditAreaEmpty() {

        return ((List<String>) jsActions.execute("$('.ace_line_group')")).isEmpty();
    }

    public boolean isPermissionErrorDisplayed() {
        wait.forElementPresent(By.cssSelector("#PermissionsError"));

        return permissionsErrorNotice.isDisplayed();
    }
}
