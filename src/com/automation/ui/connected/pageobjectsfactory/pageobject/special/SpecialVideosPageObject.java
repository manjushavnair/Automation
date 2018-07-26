package com.automation.ui.connected.pageobjectsfactory.pageobject.special;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialVideosPageObject extends SpecialPageObject {

    private static final String LONG_TITLE_SUFFIX = " ...";
    private static final String NEWEST_VIDEO_CSS = ".special-videos-grid li:nth-child(1) .title a";
    @FindBy(css = ".page-header__title")
    private WebElement h1Header;
    @FindBy(css = "a.addVideo")
    private WebElement addVideo;
    @FindBy(css = ".special-videos-grid li:nth-child(1)")
    private WebElement newestVideo;
    @FindBy(css = ".special-videos-grid li:nth-child(1) .title")
    private WebElement newestVideoTitle;
    @FindBy(css = ".special-videos-grid li:nth-child(1) .remove")
    private WebElement newestVideoDeleteIcon;
    @FindBys(@FindBy(css = ".image.video > img"))
    private List<WebElement> videos;
    @FindBy(css = ".special-videos-grid a.video")
    private List<WebElement> videoItem;
    @FindBy(css = "#WikiaConfirmOk")
    private WebElement deleteConfirmButton;
    @FindBy(css = "#sorting-dropdown")
    private WebElement sortDropdown;
    @FindBys(@FindBy(css = ".special-videos-grid li"))
    private List<WebElement> videoTileElements;

    public String getRandomVideo() {
        List<String> names = new ArrayList();
        for (WebElement elem : videos) {
            names.add(elem.getAttribute("data-video-key"));
        }
        Random r = new Random();
        int rnd = r.nextInt(names.size() - 1);
        return names.get((rnd) + 1);
    }

 /* public WatchPageObject unfollowVideo(String wikiURL, String videoName) {
    getUrl(wikiURL + URLsContent.SITE_DIR + URLsContent.FILE_NAMESPACE + videoName
        + "?action=unwatch");
    return new WatchPageObject();
  }

  public SpecialVideosPageObject clickAddButton() {
    wait.forElementClickable(addVideo);
    scrollAndClick(addVideo);
    return this;
  }
  public SpecialVideosPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public VetAddVideoComponentObject addAVideo() {
    clickAddButton();
    return new VetAddVideoComponentObject(driver);
  }

  public List<VideoTile> getVideoTiles(int numberOfTiles){
    wait.forElementPresent(By.cssSelector(NEWEST_VIDEO_CSS));
    List<VideoTile> videoTileList = new ArrayList<>();
    //numberOfTilesToFetch set to max number of possible elements to fetch
    int numberOfTilesToFetch = numberOfTiles>videoTileElements.size()?videoTileElements.size():numberOfTiles;
    List<WebElement> subList = videoTileElements.subList(0,numberOfTilesToFetch);
    for (WebElement videoTileElement : subList){
      VideoTile notification = new VideoTile(videoTileElement);
      videoTileList.add(notification);
    }
    return videoTileList;
  }

  public void verifyVideoAdded(String expectedVideoTitle) {

    List<String> patternList = getVideoTiles(2).stream().map(
            v->v.getTitle().endsWith(LONG_TITLE_SUFFIX)
                    ? v.getTitle().replace(LONG_TITLE_SUFFIX, "")
                    : v.getTitle()
    ).collect(Collectors.toList());

    Log.log("verifyVideoAdded",
        "verify that video with following description was added: " + expectedVideoTitle, true);
    Assertion.assertStringContainsAnyPattern(expectedVideoTitle, patternList);
  }
  */

    public LightboxComponentObject openLightboxForGridVideo(int itemNumber) {
        scrollAndClick(videoItem, itemNumber);
        return new LightboxComponentObject(driver);
    }

    public String getNewestVideoTitle() {
        return newestVideoTitle.getText();
    }

    public void deleteNewestVideo() {
        String videoTitle = getNewestVideoTitle();
        newestVideoDeleteIcon.click();
        //NEEDTOCHECK
        wait.forElementVisibleW(deleteConfirmButton);
        deleteConfirmButton.click();
        Log.log("Delete video", "Deleted video with title [" + videoTitle + "]", true);
    }
/*
  public boolean isNewVideoAdded() {
    try {
      openSpecialVideoPageMostRecent(getWikiUrl());
      //NEEDTOCHECK
      wait.forElementVisibleW(newestVideoTitle);
      jsActions.execute("$('.special-videos-grid .remove').first().show()");
      //NEEDTOCHECK
      wait.forElementVisibleW(newestVideo);
      return true;
    } catch (TimeoutException e) {
      Log.info("Title is not visible", e);
      return false;
    }
  }
  */

    public boolean isHeaderVisible() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(h1Header);
            return true;
        } catch (TimeoutException e) {
            Log.info("Header is not visible", e);
            return false;
        }
    }

    public boolean isAddVideoButtonClickable() {
        try {
            wait.forElementClickable(addVideo);
            return true;
        } catch (TimeoutException e) {
            Log.info("Add video button is not clickable", e);
            return false;
        }
    }

    public boolean isNewestVideoVisible() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(newestVideo);
            return true;
        } catch (TimeoutException e) {
            Log.info("Newest video is not visible", e);
            return false;
        }
    }

}