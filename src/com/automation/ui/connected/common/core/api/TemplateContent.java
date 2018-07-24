package com.automation.ui.connected.common.core.api;

import com.automation.ui.connected.common.contentpatterns.PageContent;

public class TemplateContent extends ArticleContent {
    @Override
    public void push(String text, String templateTitle) {
        super.push(text, String.format("%s:%s", PageContent.TEMPLATE_NAMESPACE, templateTitle));
    }
}
