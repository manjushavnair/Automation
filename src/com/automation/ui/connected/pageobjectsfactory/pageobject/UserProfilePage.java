package com.automation.ui.connected.pageobjectsfactory.pageobject;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.AlertHandler;
import com.automation.ui.base.common.logging.Log;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePage extends SiteBasePageObject {

    @FindBy(css = "li[data-id='blog'] a")
    private WebElement blogTab;
    @FindBy(css = "a[data-id='createblogpost']")
    private WebElement createBlogPostButton;
    @FindBy(css = ".WikiaBlogListingPost h2>a")
    private List<WebElement> blogPostList;
    @FindBy(css = ".masthead-avatar")
    private WebElement avatarWrapper;
    @FindBy(css = "#userAvatarEdit")
    private WebElement avatarEditButton;
    @FindBy(css = "#UserAvatarRemove")
    private WebElement avatarRemoveButton;
    @FindBy(css = ".masthead-avatar img.avatar")
    private WebElement avatar;
    @FindBy(css = ".mw-userpage-userdoesnotexist")
    private WebElement notExistsMessage;
    @FindBy(css = "div.masthead-info h1")
    @Getter
    private WebElement userNameTextBox;


    private By avatarImage = By.cssSelector("img.avatar");

    /**
     * Open User Profile Page
     *
     * @param userName
     * @return
     */
    public UserProfilePage open(String userName) {
        getUrl(urlBuilder.getUrlForPage(URLsContent.USER_PROFILE.replace("%userName%", userName)));

        return this;
    }

    public void clickOnBlogTab() {
        //NEEDTOCHECK
        wait.forElementVisibleW(blogTab);
        wait.forElementClickable(blogTab);
        blogTab.click();
        Log.log("clickOnBlogTab", "Click on blog tab", true);
    }

 /* public BlogPage openBlogPage(int blogNumber) {
    String blogURL = blogPostList.get(blogNumber).getAttribute("href");
    getUrl(blogURL);
    Log.log("openBlogPage", "blog post " + blogURL + " opened", true);
    return new BlogPage();
  }

  public BlogPage openFirstPost() {
    for (int i = 0; i < blogPostList.size(); i++) {
      BlogPage blogPage = openBlogPage(i);
      String pageContent = blogPage.getAtricleTextRaw().toLowerCase();
      if (!(pageContent.contains("deleted") || pageContent.contains("redirected"))) {
        Log.log("openFirstPost", "valid post found on " + i + " position", true);
        break;
      }
      Log.log("openFirstPost",
          "deleted post found on " + i + " position, trying next one", true);
      driver.navigate().back();
    }
    return new BlogPage();
  }
  */



    private void showAvatarControls() {
        setDisplayStyle(".avatar-controls", "block");
    }

    private void hideAvatarControls() {
        setDisplayStyle(".avatar-controls", "none");
    }


    public void clickRemoveAvatar() {
        showAvatarControls();
        wait.forElementClickable(avatarRemoveButton);
        avatarRemoveButton.click();
        AlertHandler.acceptPopupWindow(driver, 10);
        hideAvatarControls();
        //NEEDTOCHECK
        wait.forElementVisibleW(avatarWrapper);
        Log.log("clickRemoveAvatar", "avatar remove button clicked", true);
    }

    public void verifyAvatar() {
        //NEEDTOCHECK
        wait.forElementVisibleW(avatar);
        Log.log("verifyAvatar", "Desired avatar is visible on user profile page", true);
    }

    public void verifyAvatarChanged(String url) {
        wait.forValueToBeNotPresentInElementsAttribute(avatar, "src", url);
        Log.log("verifyAvatarChanged", "avatar src value has changed", true);
    }

    public String getAvatarImageSrc() {
        return avatarWrapper.findElement(avatarImage).getAttribute("src");
    }

    public void verifyProfilePage(String userName) {
        verifyUrlContains(URLsContent.USER_PROFILE.replace("%userName%", userName), 30);
        Log.log("verifyProfilePage", userName + " user profile page verified", true);
    }

    public String getUserName() {
        return userNameTextBox.getText();
    }

    public String getNotExistsMessage() {
        return notExistsMessage.getText();
    }
}
