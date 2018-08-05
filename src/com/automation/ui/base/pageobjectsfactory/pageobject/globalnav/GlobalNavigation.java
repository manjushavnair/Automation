package com.automation.ui.base.pageobjectsfactory.pageobject.globalnav;

import com.automation.ui.base.common.utils.*;


import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.contentpatterns.XSSContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.CommonExpectedConditions;
import com.automation.ui.base.common.core.EmailUtils;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.element.JavascriptActions;
import com.automation.ui.base.common.core.element.Wait;
import com.automation.ui.base.common.core.purge.PurgeMethod;
import com.automation.ui.base.common.core.url.Page;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.driverprovider.DriverProvider;
import com.automation.ui.base.common.logging.Log;
import com.google.common.base.Function;
import org.apache.commons.lang3.tuple.Pair;

import com.automation.ui.base.pageobjectsfactory.pageobject.*;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlobalNavigation extends BasePageObject {


  @FindBy(css = ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in")
  private WebElement userAvatar;


  @FindBy(css = ".wds-global-navigation__user-logged-in .wds-dropdown__content")
  private WebElement userMenu;

  @FindBy(css = ".wds-sign-out__button")
  private WebElement signOutButton;


  @FindBy(css = ".wds-global-navigation__dropdown-toggle span")
  private WebElement siteMenu;



  public GlobalNavigation openMenu() {
    wait.forElementClickable(siteMenu).click();
    Log.log("siteMenu", "clicked on siteMenu   in global nav bar", true);

    return this;
  }


  public GlobalNavigation clickUserAvatar() {
    wait.forElementClickable(userAvatar).click();
    Log.info("clicked on user avatar in global nav bar");

    return this;
  }

  public void clickSignOut() {
    clickUserAvatar();
    wait.forElementClickable(signOutButton).click();
    Log.info("link to sign out clicked");
  }

  public boolean isUserMenuOpened() {
    return isElementDisplayed(userMenu);
  }



  public boolean isUserLoggedOut() {
    return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
  }


  public boolean isSiteMenuVisible() {
    return isElementDisplayed(siteMenu,3 );
  }



  public boolean isUserAvatarVisible() {
    return isElementDisplayed(userAvatar, 3);
  }



}
