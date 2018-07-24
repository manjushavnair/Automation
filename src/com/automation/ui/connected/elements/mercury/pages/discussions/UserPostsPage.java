package com.automation.ui.connected.elements.mercury.pages.discussions;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.elements.mercury.components.discussions.common.DeleteAllButton;
import com.automation.ui.connected.elements.mercury.components.discussions.common.ErrorMessages;
import com.automation.ui.connected.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;
import org.joda.time.DateTime;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Pattern;

public class UserPostsPage extends PageWithPosts {

    private static final Pattern PAGE_PATTERN = Pattern.compile("/d/u/\\d+");
    private static final String PATH = "/d/u/%s";
    private static final String NON_EXISTING_USER_ID = "4809883";
    @Getter(lazy = true)
    private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();
    @Getter(lazy = true)
    private final ErrorMessages errorMessages = new ErrorMessages();
    @Getter(lazy = true)
    private final DeleteAllButton deleteAll = new DeleteAllButton();
    /**
     * moderation section visible to mod+ users in mobile view
     */
    @FindBy(className = "header-dropdown-button")
    private WebElement moderation;

    public static boolean is(String url) {
        return PAGE_PATTERN.matcher(url).find();
    }

    public UserPostsPage open(String userId) {
        driver.get(getUrlWithCacheBuster(urlBuilder.getUrl() + String.format(PATH, userId), "cb=" + DateTime.now().getMillis() + "&AbTest.DISCUSSIONS_LIGHTWEIGHT_CONTRIBUTION_MENU=OLD_1"));
        waitForEmberLoad();
        return this;
    }

    @Override
    public UserPostsPage open() {
        return open(NON_EXISTING_USER_ID);
    }

    public UserPostsPage expandModeration() {
        try {
            moderation.click();
        } catch (NoSuchElementException e) {
            Log.info("Moderation dropdown not found", e);
        }
        return this;
    }

}
