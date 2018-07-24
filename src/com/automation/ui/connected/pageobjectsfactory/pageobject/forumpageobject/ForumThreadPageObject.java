package com.automation.ui.connected.pageobjectsfactory.pageobject.forumpageobject;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Abstract representation of a Forum Thread. Example: http://mediawiki119.wikia.com/wiki/Thread:41679
 */

public class ForumThreadPageObject extends BasePageObject {

    MiniEditorComponentObject miniEditor;
    @FindBy(css = "div.msg-title a")
    private WebElement discussionTitle;
    @FindBy(css = ".replyButton")
    private WebElement replyButton;
    @FindBy(css = ".speech-bubble-message nav")
    private WebElement moreButton;
    @FindBy(css = ".WikiaMenuElement .remove-message")
    private WebElement removeButton;
    @FindBy(css = ".WikiaMenuElement .move-thread")
    private WebElement moveThreadButton;
    @FindBy(css = ".WikiaMenuElement .close-thread")
    private WebElement closeThreadButton;
    @FindBy(css = ".WikiaMenuElement .reopen-thread")
    private WebElement reopenThreadButton;
    @FindBy(css = "#WallMoveModalWrapper select")
    private WebElement moveThreadModalSelectElement;
    @FindBy(css = "#WallMoveModalWrapper .primary")
    private WebElement moveThreadModalMoveThreadButton;
    @FindBy(css = ".wall-action-reason")
    private WebElement removeThreadModalTextarea;
    @FindBy(css = ".reason")
    private WebElement closeThreadMessage;
    @FindBy(css = "#reason")
    private WebElement closeThreadTextarea;
    @FindBy(css = "#WikiaConfirmOk")
    private WebElement removeThreadModalRemoveButton;
    @FindBy(css = ".speech-bubble-message-removed")
    private WebElement threadRemovedMessage;
    @FindBy(css = ".speech-bubble-message-removed a")
    private WebElement undoThreadRemoveButton;
    @FindBys(@FindBy(css = "div.msg-body p"))
    private List<WebElement> discussionBody;
    @FindBy(className = "speech-bubble-message")
    private WebElement message;
    @FindBy(css = ".page-header__page-subtitle nav")
    private WebElement breadCrumbs;
    @FindBy(className = "speech-bubble-message")
    private WebElement speechBubble;
    private String wikiaEditorTextarea = "textarea.replyBody";

    public ForumThreadPageObject(WebDriver driver) {
        super();
        miniEditor = new MiniEditorComponentObject(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyDiscussionTitleAndMessage(String title, String message) {
        //NEEDTOCHECK
        wait.forElementVisibleW(discussionTitle);
        wait.forElementVisibleW(discussionBody.get(0));
        wait.forTextInElement(discussionTitle, title);
        wait.forTextInElement(discussionBody.get(0), message);
        Log
                .log("verifyDiscussionWithTitle", "discussion with title and message verified", true);
    }

    public void reply(String message) {
        wait.forElementVisible(By.cssSelector(wikiaEditorTextarea));
        jsActions.focusCss(wikiaEditorTextarea);
        driver.switchTo().frame(miniEditor.miniEditorIframe);
        miniEditor.writeMiniEditor(message);
        driver.switchTo().defaultContent();
        clickReplyButton();
        Log
                .log("reply", "write a reply with the following text: " + message, true, driver);
    }

    public void verifyReplyMessage(int replyNumber, String message) {
        WebElement
                replyMessage =
                driver.findElement(By.cssSelector(".replies li:nth-child(" + replyNumber + ") p"));
        wait.forTextInElement(replyMessage, message);
        Log.log("verifyReplyMessage", "verify that message number " + replyNumber
                + " has the following message: " + message, true);
    }

    public void clickReplyButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(replyButton);
        scrollAndClick(replyButton);
        wait.forElementVisible(By.cssSelector(".speech-bubble-buttons"));
        Log.log("clickReplyButton", "reply button clicked", true, driver);
    }

    public void removeThread(String reason) {
        clickOnMoreButton();
        clickOnRemoveButton();
        //NEEDTOCHECK
        wait.forElementVisibleW(removeThreadModalTextarea);
        removeThreadModalTextarea.sendKeys(reason);
        //NEEDTOCHECK
        wait.forElementVisibleW(removeThreadModalRemoveButton);
        wait.forElementClickable(removeThreadModalRemoveButton);
        scrollAndClick(removeThreadModalRemoveButton);
        Log
                .log("removeThread", "removed thread with the following reason: " + reason, true, driver);
    }

    public void clickOnRemoveButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(removeButton);
        jsActions.click(".WikiaMenuElement .remove-message");
        Log.log("clickOnRemoveButton", "click on 'remove' button", true, driver);
    }

    public void clickOnMoveThreadButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(moveThreadButton);
        jsActions.click(".WikiaMenuElement .move-thread");
        Log.log("clickOnMoveThreadButton", "click on 'move thread' button", true, driver);
    }

    public void clickOnMoreButton() {
        scrollTo(message);

        new Actions(driver).moveToElement(speechBubble).perform();
        //NEEDTOCHECK
        wait.forElementVisibleW(moreButton);
        wait.forElementClickable(moreButton);
        scrollAndClick(moreButton);
        Log.log("clickOnMoreButton", "click on 'more' button on a message", true);
    }

    public void clickOnCloseThreadButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(closeThreadButton);
        wait.forElementClickable(closeThreadButton);
        scrollAndClick(closeThreadButton);
        Log
                .log("clickOnCloseThreadButton", "click on 'close thread' button on a message", true);
    }

    public void clickOnReopenThreadButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(reopenThreadButton);
        wait.forElementClickable(reopenThreadButton);
        reopenThreadButton.click();
        Log
                .log("clickOnReopenThreadButton", "click on 'reopen thread' button on a message", true);
    }

    public void verifyThreadRemoved() {
        wait.forTextInElement(threadRemovedMessage, "thread has been removed");
        Log.log("verifyThreadRemoved", "Thread has been removed", true);
    }

    public void verifyThreadClosed() {
        //NEEDTOCHECK
        wait.forElementVisibleW(closeThreadMessage);
        Log.log("verifyThreadClosed", "Thread has been closed", true);
    }

    public void verifyThreadReopened() {
        waitForElementNotVisibleByElement(closeThreadMessage);
        Log.log("verifyThreadReopened", "Thread has been reopened", true);
    }

    public void undoRemove() {
        //NEEDTOCHECK
        wait.forElementVisibleW(undoThreadRemoveButton);
        wait.forElementClickable(undoThreadRemoveButton);
        scrollAndClick(undoThreadRemoveButton);
        Log.log("undoRemove", "click on 'undo' button", true, driver);
    }

    public String moveThread() {
        clickOnMoreButton();
        clickOnMoveThreadButton();
        //NEEDTOCHECK
        wait.forElementVisibleW(moveThreadModalSelectElement);
        Select dropList = new Select(moveThreadModalSelectElement);
        String selectedItem = dropList.getOptions().get(1).getText();
        dropList.selectByIndex(1);
        wait.forElementClickable(moveThreadModalMoveThreadButton);
        scrollAndClick(moveThreadModalMoveThreadButton);
        Log
                .log("moveThread", "thread moved to the following board: " + selectedItem, true, driver);

        return selectedItem;
    }

    public void closeThread(String reason) {
        clickOnMoreButton();
        clickOnCloseThreadButton();
        //NEEDTOCHECK
        wait.forElementVisibleW(closeThreadTextarea);
        closeThreadTextarea.sendKeys(reason);
        //NEEDTOCHECK
        wait.forElementVisibleW(removeThreadModalRemoveButton);
        wait.forElementClickable(removeThreadModalRemoveButton);
        scrollAndClick(removeThreadModalRemoveButton);
        Log
                .log("closeThread", "closed thread with the following reason: " + reason, true, driver);
    }

    public void reopenThread() {
        clickOnMoreButton();
        clickOnReopenThreadButton();
        Log.log("reopenThread", "reopened thread", true, driver);
    }

    public void verifyParentBoard(String forumBoardName) {
        driver.navigate().refresh();
        //NEEDTOCHECK
        wait.forElementVisibleW(breadCrumbs);
        wait.forTextInElement(breadCrumbs, forumBoardName + " board");
        Log.log("verifyParentBoard",
                "verify that the parent board of current thread is the following: "
                        + forumBoardName, true);
    }

    public ForumHistoryPageObject openHistory() {
        getUrl(getCurrentUrl() + "?action=history");
        Log.log("openHistory", "thread history page opened", true, driver);
        return new ForumHistoryPageObject(driver);
    }
}
