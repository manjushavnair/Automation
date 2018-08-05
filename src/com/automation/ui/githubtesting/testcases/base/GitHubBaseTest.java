/**
 *
 */
package com.automation.ui.githubtesting.testcases.base;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.utils.ExcelUtil;
import com.automation.ui.githubtesting.common.constants.ExcelCONSTANTS;
import com.automation.ui.githubtesting.common.templates.NewTestTemplate;
import org.apache.log4j.Logger;

public class GitHubBaseTest extends NewTestTemplate  {

    private static Logger logger = Logger.getLogger(GitHubBaseTest.class);
    protected ExcelUtil eu = null;

    public GitHubBaseTest() {
        super();

        eu = getExcelUtil();

    }

    protected ExcelUtil getExcelUtil() {
   	     ExcelUtil eu = null;
        try {
            eu = new ExcelUtil();

            eu.setExcelFile(ExcelCONSTANTS.LOGINXLSDATAPATH,
                    ExcelCONSTANTS.LOGINXLSDATASHEETNAMELOGIN);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return eu;
    }


    protected void loadFirstPage() {
        driver.get(urlBuilder.getUrlForPage(URLsContent.SITE_DIR));
    }



    /*
    @BeforeMethod
    protected void setUpBeforeTestMethod() {
        // initialize testPage
        // login to the app, if necessary
    }

    @AfterMethod
    protected void tearDownAfterTestMethod() {
        // logout of the app, if necessary
    }

    @BeforeClass
    protected void setUpBeforeTestClass() {
        // initialize a browser driver, connect to servers
    }

    @AfterClass
    protected void tearDownAfterTestClass() {
        // close connections, close browser as needed
        logger.info("Quiting browser  tearDownAfterTestClass  ::BaseTest");
        Reporter.log("Quiting browser  tearDownAfterTestClass  ::BaseTest");
        // driverManager.quitDriver();
    }
*/

}