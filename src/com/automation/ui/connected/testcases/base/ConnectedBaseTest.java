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
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.*;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.*;
import com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection.* ;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ConnectedBaseTest extends NewTestTemplate  {

    private static Logger logger = Logger.getLogger(ConnectedBaseTest.class);
    protected ExcelUtil eu = null;
    protected LoginPage login_page = null;
    protected HomePage home_page = null;
    protected AddCloudDataConnection data_page = null;

    public ConnectedBaseTest() {
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




}