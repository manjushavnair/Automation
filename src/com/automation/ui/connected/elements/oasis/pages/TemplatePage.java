package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.connected.elements.oasis.components.templateclassificiation.TemplateClassification;
import com.automation.ui.connected.elements.oasis.components.templatecontent.TemplateContent;
import com.automation.ui.connected.pageobjectsfactory.pageobject.PortableInfobox;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatePage extends SiteBasePageObject {

    @Getter(lazy = true)
    private final TemplateClassification templateClassification = new TemplateClassification();
    private final PortableInfobox portableInfobox = new PortableInfobox();

    @FindBy(css = "#ca-edit")
    protected WebElement editUsingClassicEditor;

    @FindBy(css = "#WikiaPageBackground")
    private WebElement pageBackgroundColor;

    public TemplatePage() {
        super();
    }

    public TemplatePage open(String templateName) {
        getUrl(String.format("%s%s%s:%s", urlBuilder.getUrl(), URLsContent.SITE_DIR,
                URLsContent.TEMPLATE_NAMESPACE, templateName));

        return this;
    }

    public boolean isTemplatePagePresent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(pageBackgroundColor);

        return pageBackgroundColor.isDisplayed();
    }

    public String getRawContent(String templateName) {
        getUrl(urlBuilder.appendQueryStringToURL(String.format("%s%s%s:%s", urlBuilder.getUrl(),
                URLsContent.SITE_DIR,
                URLsContent.TEMPLATE_NAMESPACE,
                templateName),
                URLsContent.ACTION_RAW));
        wait.forElementPresent(By.cssSelector("body"));
        return body.getText();
    }

    public SourceEditModePageObject editArticleInSrcUsingDropdown() {
        editUsingClassicEditor.click();
        return new SourceEditModePageObject();
    }

    public TemplatePage openArticleByName(String wikiURL, String articleName) {
        getUrl(wikiURL + URLsContent.SITE_DIR + articleName);
        return new TemplatePage();
    }

    public PortableInfobox getPortableInfobox() {
        return portableInfobox;
    }

    public String getPageBackgroundColor() {
        //NEEDTOCHECK
        wait.forElementVisibleW(pageBackgroundColor);

        return pageBackgroundColor.getCssValue("background-color");
    }

    public TemplatePage createTemplate(String templateName) {
        new TemplateContent().push(templateName);

        return this;
    }
}
