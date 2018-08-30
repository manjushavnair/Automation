package com.automation.ui.connected.testcases.serverdetails.filter;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.common.dataprovider.SiteDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter.*;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;


public class FilterDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(FilterDetailsTest.class);


    public FilterDetailsTest() {

        super();
        logger.info(" FilterDetailsTest");
         filter = new FilterDetails();
    }

       /** */
    @Test(enabled = true, priority = 10, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
             , description = "FilterDetails")
    public void filterAseetTreeTag() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.assetTreeTag();



    }



}
