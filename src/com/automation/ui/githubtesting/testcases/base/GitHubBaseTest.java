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

public class GitHubBaseTest extends NewTestTemplate {

    private static Logger logger = Logger.getLogger(GitHubBaseTest.class);
    protected ExcelUtil eu = null;

    public GitHubBaseTest() {
        super();


    }


    protected void getDataReaders() {

        try {
            eu = new ExcelUtil();

            eu.setExcelFile("C:\\Software\\selenium\\githubdataselenium.xls",
                    ExcelCONSTANTS.LOGINGITHUB);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }

    }


    protected void loadFirstPage() {
        logger.info("driver" + driver + "urlBuilder" + urlBuilder);
        driver.get(urlBuilder.getUrlForPage(URLsContent.SITE_DIR));
    }


}