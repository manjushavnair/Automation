package com.automation.ui.connected.elements.oasis.components.templatecontent;

import com.automation.ui.connected.common.core.api.ArticleContent;

public class TemplateContent {

    private static final String NAMESPACE = "Template:";

    public TemplateContent push(String templateName) {
        new ArticleContent().push("", NAMESPACE + templateName);

        return this;
    }
}
