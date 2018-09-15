/**
 *
 */
package com.automation.ui.connected.testcases.base;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.assertion.Assertion;
import com.automation.ui.base.common.utils.ExcelUtil;
import com.automation.ui.connected.common.constants.ExcelCONSTANTS;
import com.automation.ui.connected.common.templates.NewTestTemplate;
import com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.manage.AddServerDetailsPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter.FilterDetailsPage;
import org.apache.log4j.Logger;

import java.util.List;

public class ConnectedBaseTest extends NewTestTemplate {

    private static Logger logger = Logger.getLogger(ConnectedBaseTest.class);
    protected ExcelUtil eu = null;
    protected LoginPage login_page = null;
    protected HomePage home_page = null;
    protected AddServerDetailsPage serverdetail_page = null;
   // protected UAConnectionDetailsPage connectiondetail_page = null;
    protected FilterDetailsPage filter = null;
    protected GlobalNavigationPage global_nav=null;



    public ConnectedBaseTest() {
        super();

        getDataReaders();

    }


    protected void getDataReaders() {

        try {
            eu = new ExcelUtil();

//  eu.setExcelFile(ExcelCONSTANTS.LOGINXLSDATAPATH,                   ExcelCONSTANTS.LOGINXLSDATASHEETNAMELOGIN);
            eu.setExcelFile("C:\\Software\\selenium\\dataselenium.xls",
                    ExcelCONSTANTS.LOGINXLSDATASHEETNAMELOGIN);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }

    }


    protected void loadFirstPage() {
        driver.get(urlBuilder.getUrlForPage(URLsContent.SITE_DIR));

    }

    protected void verifyElementsVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertTrue(home_page.isElementVisible(
                element), element + " is not visible"));
    }

    protected void verifyElementsNotVisible(List<String> elementsList) {
        elementsList.forEach(element -> Assertion.assertFalse(home_page.isElementVisible(
                element), element + " is visible"));
    }


}