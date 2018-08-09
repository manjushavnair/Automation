package com.automation.ui.base.pageobjectsfactory.pageobject.globalnav;


import com.automation.ui.base.common.logging.Log;

import com.automation.ui.base.pageobjectsfactory.pageobject.*;

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


  public String getTitle() {
    String title = driver.getTitle();

    return driver.getTitle();
  }


  public void goBack() {
    driver.navigate().back();

  }

  public void goForward() {
    driver.navigate().forward();

  }

  public void refresh() {
    driver.navigate().refresh();

  }


  public GlobalNavigation clickUserLogo() {
    wait.forElementClickable(userAvatar).click();
    Log.info("clicked on user logo in global nav bar");

    return this;
  }

  public void clickSignOut() {
    clickUserLogo();
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
