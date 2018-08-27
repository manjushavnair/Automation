package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.testcases.base.ServerType;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class EditServerDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(EditServerDetails.class);



    public EditServerDetails open() {


        return new EditServerDetails();
    }


    public void getServerDetailsCancel() {





    }



}
