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
        logger.info(" FilterDetailsTest");
        filter = new FilterDetails();
        serverdetail_page = new AddServerDetails();
        home_page = new HomePage();
    }

       /** */
    @Test(enabled = true, priority = 10, groups = {"validcase"}, dataProviderClass = SiteDataProvider.class
             , description = "FilterDetails")
    public void filterAseetTreeTag() throws Throwable {
        home_page.addConnection();
        Map<String, Object> connInfo=null;
        serverdetail_page.provideAllServerDetails(connInfo );

        filter.assetTreeTag();
        Thread.sleep(600000);

/*
<span ng-class="{'partSelect':item.partialSelect}"></span>
<label for="t1" class="ng-binding">Square1</label>

<td class="cell-name" width="30%">
                                                    <div class="indent" style="padding-left: 53px" ng-click="steps.loadChildTreeNodes(item)"></div>
                                                    <span class="draggableItem">
                                                        <input type="checkbox" name="ch1" id="ns=2;s=MySwitch" ng-click="steps.toggleChildren(item); steps.toggleParent(item.parentNode); steps.ShowNodeSelectCount();" ng-model="item.selected" class="ng-pristine ng-untouched ng-valid ng-not-empty">
                                                        <label for="ns=2;s=MySwitch"><span ng-class="{'partSelect':item.partialSelect}"></span></label>
                                                        <label for="t1" class="ng-binding">MySwitch</label>
                                                    </span>
                                                </td>


<button class="primaryLink ng-binding" ng-click="steps.startAddingNewFilter()">New tag filter</button>
<input ch-focus="" class="textbox ng-pristine ng-valid ng-empty ng-touched" placeholder="Type to search" ng-model="steps.previewResults.FilterOn">
<a href="#" class="search" ng-click="steps.fetchFilteredTags()"></a>
<a href="#" class="link ng-binding" ng-click="steps.viewAllClick(filter)">View all</a>
<a href="#" class="delete icon" ng-click="steps.deleteFilterClick($index)"></a>

<input type="checkbox" name="ch1" id="ns=2;s=MyLevel.Alarm/0:AckedState/0:FalseState" ng-click="steps.toggleChildren(item); steps.toggleParent(item.parentNode); steps.ShowNodeSelectCount();" ng-model="item.selected" class="ng-untouched ng-valid ng-not-empty ng-dirty ng-valid-parse">
 */



    }



}
