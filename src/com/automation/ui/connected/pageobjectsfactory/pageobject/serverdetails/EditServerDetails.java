package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;

public class EditServerDetails extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(EditServerDetails.class);


    public EditServerDetails open() {


        return new EditServerDetails();
    }


    public void getServerDetailsCancel() {


    }


}
