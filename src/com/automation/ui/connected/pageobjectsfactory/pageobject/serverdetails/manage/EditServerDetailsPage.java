package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.manage;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;

public class EditServerDetailsPage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(EditServerDetailsPage.class);


    public EditServerDetailsPage open() {


        return new EditServerDetailsPage();
    }


    public void getServerDetailsCancel() {


    }


}
