package com.automation.ui.githubtesting.pageobjectfactory.pageobject.clouddataconnection;

import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;

public class AddCloudDataConnection extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddCloudDataConnection.class);




    public AddCloudDataConnection open( )
            {
        getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();

        return new AddCloudDataConnection();
    }



}
