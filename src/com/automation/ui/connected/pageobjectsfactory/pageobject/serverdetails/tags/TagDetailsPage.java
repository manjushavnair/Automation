package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.tags;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TagDetailsPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(TagDetailsPage.class);

    @FindBy(xpath = TagDetailsCONSTANTS.Tag)
    private WebElement dcDropdownButton;




    public TagDetailsPage open() {


        return new TagDetailsPage();
    }



    public void dcDetails() {

    }


}
