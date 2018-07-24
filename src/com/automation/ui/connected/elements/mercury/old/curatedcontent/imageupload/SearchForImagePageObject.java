package com.automation.ui.connected.elements.mercury.old.curatedcontent.imageupload;

import com.automation.ui.base.common.core.element.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchForImagePageObject {

    @FindBy(css = "input#search")
    private WebElement searchInput;
    @FindBys(@FindBy(css = ".search-results img"))
    private List<WebElement> images;

    private Wait wait;
    private WebDriver driver;

    public SearchForImagePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);

        PageFactory.initElements(driver, this);
    }

    public SearchForImagePageObject type(String searchPhrase) {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchInput);
        searchInput.sendKeys(searchPhrase);

        return this;
    }

    public CroppingToolPageObject clickOnImage(int imageIndex) {
        WebElement image = images.get(imageIndex);
//NEEDTOCHECK
        wait.forElementVisibleW(image);
        image.click();

        return new CroppingToolPageObject(driver);
    }
}
