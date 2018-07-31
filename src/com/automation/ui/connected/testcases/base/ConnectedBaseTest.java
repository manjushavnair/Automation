/**
 *
 */
package com.automation.ui.connected.testcases.base;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.utils.ExcelUtil;
import com.automation.ui.connected.common.constants.ExcelCONSTANTS;
import com.automation.ui.connected.common.core.url.SiteUrlBuilder;
import com.automation.ui.connected.common.prpreader.AssertDataReader;
import com.automation.ui.connected.common.templates.NewTestTemplate;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ConnectedBaseTest extends NewTestTemplate  {

    private static Logger logger = Logger.getLogger(ConnectedBaseTest.class);
    protected ExcelUtil eu = null;

    public ConnectedBaseTest() {
        super();
        AssertDataReader.readProperty();

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

    protected void prepareURLs() {


        urlBuilder = UrlBuilder.createUrlBuilder();
        siteUrlBuilder = new SiteUrlBuilder();
        siteURL = urlBuilder.getUrl();
        siteCorporateURL = urlBuilder.getSiteGlobalURL();
        logger.info("prepareURLs siteCorporateURL : " + siteCorporateURL);
        siteCorpSetupURL = UrlBuilder.createUrlBuilderForSiteAndLang("corp", Configuration.DEFAULT_LANGUAGE).getUrl();
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