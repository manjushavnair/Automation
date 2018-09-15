package com.automation.ui.connected.testcases.serverdetails.filter;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.common.dataprovider.SiteDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.manage.AddServerDetailsPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter.*;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;


public class FilterDetailsPageTest extends ConnectedBaseTest {

    private static Logger logger = Logger.getLogger(FilterDetailsPageTest.class);


    public FilterDetailsPageTest() {

        super();
        logger.debug(" FilterDetailsPageTest");
        filter = new FilterDetailsPage();
        serverdetail_page = new AddServerDetailsPage();
        home_page = new HomePage();
    }


    @Test(enabled = true, priority = 0, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
             , description = "FilterDetailsPage")
    public void filterAseetTreeTag() throws Throwable {
        home_page.addConnection( );
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.assetTreeTag();

    }



    @Test(enabled = true, priority = 1, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetailsPage")
    public void filterChangeFilters() throws Throwable {
        home_page.addConnection( );
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );
        filter.assetTreeTag();
        //Thread.sleep(3000);
        filter.allTag();
        //Thread.sleep(3000);
        filter.listFilterTag();
        //Thread.sleep(3000);
    }


    @Test(enabled = true, priority = 2, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetailsPage")
    public void filterListFilter() throws Throwable {
        home_page.addConnection( );
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.listFilterTag();



    }


    @Test(enabled = true, priority = 3, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
            , description = "FilterDetailsPage")
    public void filterAllTag() throws Throwable {
        home_page.addConnection( );
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.allTag();
        filter.filterEditTag();

        filter.filterExportTag();





    }

}
