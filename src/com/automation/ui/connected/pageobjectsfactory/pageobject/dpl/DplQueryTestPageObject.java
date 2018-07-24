package com.automation.ui.connected.pageobjectsfactory.pageobject.dpl;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;

/**
 * A wiki page that contains <a href="http://community.wikia.com/wiki/Help%3ADynamicPageList">DPL</a> queries
 * Each query is wrapped in an element with an unique ID so that their result set can be tested separately
 */
public class DplQueryTestPageObject extends SiteBasePageObject {
    private static final String DPL_QUERY_TEST_PAGE = "DplQueryTest";

    private static final String DPL_TEST_PAGE_ONE = "DplTestPageOne";
    private static final String DPL_TEST_PAGE_TWO = "DplTestPageTwo";
    private static final String DPL_TEST_PAGE_THREE = "DplTestPageThree";

    @FindBy(css = "#dpl-query-category a")
    private List<WebElement> dplCategoryQuery;

    @FindBy(css = "#dpl-query-createdby a")
    private List<WebElement> dplCreatedByQuery;

    public void openDplQueryTestPage(String wikiURL) {
        getUrl(wikiURL + "/wiki/" + DPL_QUERY_TEST_PAGE);
        Log.log("openDplQueryTestPage",
                "DPL query test page was opened", true);
    }

    public boolean dplCategoryQueryContainsExpectedPageSet() {
        Set<String> resultSet = of(DPL_TEST_PAGE_ONE, DPL_TEST_PAGE_TWO, DPL_TEST_PAGE_THREE);
        return dplQueryHasExpectedPages(resultSet, dplCategoryQuery);
    }

    public boolean dplCreatedByQueryContainsExpectedPageSet() {
        Set<String> resultSet = of(DPL_TEST_PAGE_TWO, DPL_TEST_PAGE_THREE);
        return dplQueryHasExpectedPages(resultSet, dplCreatedByQuery);
    }

    private boolean dplQueryHasExpectedPages(Set<String> expectedPageSet, List<WebElement> resultSet) {
        return resultSet.stream().map(WebElement::getText).allMatch(expectedPageSet::contains);
    }
}
