/**
 *
 */
package com.automation.ui.connected.testcases.login;

/**
 * @author Manjusha Saju
 */

import com.automation.ui.connected.pageobjectsfactory.pageobject.login.LoginPage;
import com.automation.ui.connected.testcases.base.ConnectedBaseTest;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

//import com.uiautomation.ui.listener.LoginListener;

//@Listeners(com.uiautomation.ui.listener.LoginListener.class)


/**
 * Test Suite ID – The ID of the test suite to which this test case belongs.
 * Test Case ID – The ID of the test case.
 * Test Case Summary – The summary / objective of the test case.
 * Related Requirement – The ID of the requirement this test case relates/traces to.
 * Prerequisites – Any prerequisites or preconditions that must be fulfilled prior to executing the test.
 * Test Procedure – Step-by-step procedure to execute the test.
 * Test Data – The test data, or links to the test data, that are to be used while conducting the test.
 * Expected Result – The expected result of the test.
 * Actual Result – The actual result of the test; to be filled after executing the test.
 * Status – Pass or Fail. Other statuses can be ‘Not Executed’ if testing is not performed and ‘Blocked’ if testing is blocked.
 * Remarks – Any comments on the test case or test execution.
 * Created By – The name of the author of the test case.
 * Date of Creation – The date of creation of the test case.
 * Executed By – The name of the person who executed the test.
 * Date of Execution – The date of execution of the test.
 * Test Environment – The environment (Hardware/Software/Network) in which the test was executed.
 */


public class LoginTest extends ConnectedBaseTest {


    private static Logger logger = Logger
            .getLogger(LoginTest.class);


    public LoginTest() {

        super();


    }


    /***
     *
     * @throws Throwable
     */

    @Test(priority = 1, groups = {"base", "invalidcase"}, description = "invalid login")
    public void verifyNullUserNullPassword() throws Throwable {

        logger.debug("Entering verifyNullUserNullPassword");
        Reporter.log("Entering verifyNullUserNullPassword");
        login_page = new LoginPage();
        login_page.open();
        // Call the method

        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();

        String userName = eu.getCellData(5, 1);
        String password = eu.getCellData(5, 2);

        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();
        //  driver.navigate().back();
    }
    /***
     *
     * @throws Throwable
     */

    @Test(priority = 2, groups = {"base", "invalidcase"}, description = "invalid login")
    public void verifyInValidUserInvalidPassword() throws Throwable {

        logger.debug("Entering verifyInValidLogin");
        Reporter.log("Entering verifyInValidLogin");
        login_page = new LoginPage();
        login_page.open();


        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();

        String userName = eu.getCellData(4, 1);
        String password = eu.getCellData(4, 2);


        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();
        //  driver.navigate().for();
    }

    /***
     *
     * @throws Throwable
     */

    @Test(priority = 3, groups = {"base", "invalidcase"})
    public void verifyValidUserNullPassword() throws Throwable {

        logger.debug("Entering verifyValidUserNullPassword");
        Reporter.log("Entering verifyValidUserNullPassword");
        login_page = new LoginPage();
        login_page.open();
        // Call the method
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();

        String userName = eu.getCellData(3, 1);
        String password = eu.getCellData(3, 2);


        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();

    }

    /***
     *
     * @throws Throwable
     */

    @Test(priority = 4, groups = {"base", "invalidcase"})
    public void verifyInValidUserNullPassword() throws Throwable {
        Reporter.log("Entering verifyInValidUserNullPassword");
        login_page = new LoginPage();
        login_page.open();
        // Call the method
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();


        String userName = eu.getCellData(2, 1);
        String password = eu.getCellData(2, 2);

        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();

    }


    /***
     *
     * @throws Throwable
     */

    @Test(priority = 0, groups = {"base", "validcase"})
    public void verifyValidLogin() throws Throwable {

        Reporter.log("Entering verifyValidLogin");
        login_page = new LoginPage();
        login_page.open();
        String methodname = new Object() {
        }.getClass().getEnclosingMethod().getName();
        // Call the method


        String userName = eu.getCellData(1, 1);
        String password = eu.getCellData(1, 2);


        login_page.enterUser(userName);
        login_page.enterPassword(password);
        login_page.login();
        //  driver.navigate().back();

    }







}
