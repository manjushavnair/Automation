package com.automation.ui.connected.testcases.serverdetails.filter;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.common.dataprovider.SiteDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.AddServerDetails;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter.*;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;


public class FilterDetailsTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(FilterDetailsTest.class);


    public FilterDetailsTest() {

        super();
        logger.debug(" FilterDetailsTest");
        filter = new FilterDetails();
        serverdetail_page = new AddServerDetails();
        home_page = new HomePage();
    }


    @Test(enabled = true, priority = 0, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
             , description = "FilterDetails")
    public void filterAseetTreeTag() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.assetTreeTag();

    }


    @Test(enabled = true, priority = 3, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetails")
    public void filterAllTag() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.allTag();
        filter.filterEditTag();
        Thread.sleep(3000);
        filter.filterExportTag();

        Thread.sleep(5000);



    }

    @Test(enabled = true, priority = 2, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetails")
    public void filterListFilter() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.listFilterTag();



    }

    @Test(enabled = true, priority = 1, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetails")
    public void filterChangeFilters() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );




        filter.assetTreeTag();

        Thread.sleep(3000);


        filter.allTag();

        Thread.sleep(3000);

         filter.listFilterTag();

        Thread.sleep(3000);


    }


}
