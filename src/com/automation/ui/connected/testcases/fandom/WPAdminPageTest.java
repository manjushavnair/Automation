package com.automation.ui.connected.testcases.fandom;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.connected.common.core.helpers.FandomUser;
import com.automation.ui.connected.common.templates.fandom.FandomTestTemplate;
import com.automation.ui.connected.elements.fandom.pages.AdminLoginPage;
import com.automation.ui.connected.elements.fandom.pages.NewPostPage;
import org.testng.annotations.Test;

@Test(groups = {"Fandom", "Fandom_WPAdmin"})
public class WPAdminPageTest extends FandomTestTemplate {

    @Test
    public void cannotPublishPostWithoutFeatureImage() {
        new AdminLoginPage().open().getLoginBox().login(FandomUser.EDITOR);

        NewPostPage postPage = new NewPostPage().open();

        postPage.typeTitle("This is My new Title");

        postPage
                .getTextEditor()
                .type("This is my new message");

        postPage.publish();

        Assertion.assertTrue(postPage.getNotifications().isNotificationVisible(
                "A featured image is required for this post type. This post is saved as a draft."));
    }
}
