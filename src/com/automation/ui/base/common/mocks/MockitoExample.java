package com.automation.ui.base.common.mocks;

import com.automation.ui.base.common.mocks.mock.Login;
import junit.framework.Assert;

import static org.mockito.Mockito.*;

public class MockitoExample {

    public static void main(String args[]) {

        // test with actual Login class
        runTestWithActualClass();

        // tests with Mockito mock of the Login class
        runVerifyTestWithMockito();
        runStubTestWithMockito();
        runExceptionTestWithMockito();
    }

    public static void runTestWithActualClass() {

        // create new instance of the login class
        Login login = new Login();

        // test with correct credentials
        login.setUsername("bas");
        login.setPassword("ok");
        Assert.assertEquals("login_ok", login.login());

        // test with incorrect credentials
        login.setUsername("bas");
        login.setPassword("nok");
        Assert.assertEquals("login_nok", login.login());

    }

    public static Login createLoginMock() {

        return mock(Login.class);
    }

    public static void runVerifyTestWithMockito() {

        // create mock instance of the login class
        Login loginMock = createLoginMock();

        // call methods on the mock instance
        loginMock.setUsername("bas");
        loginMock.setPassword("ok");

        // verify that methods are called properly
        verify(loginMock).setUsername("bas");
        verify(loginMock).setPassword("ok");


        verify(loginMock).getUsername();

    }

    public static void runStubTestWithMockito() {

        Login loginMock = createLoginMock();

        // stub mock behavior
        when(loginMock.login()).thenReturn("login_ok");

        // call mock method and verify result
        Assert.assertEquals("login_ok", loginMock.login());

    }

    public static void runExceptionTestWithMockito() {

        // create mock instance of the login class
        Login loginMock = createLoginMock();

        // have the mock return an Exception
        when(loginMock.getPassword()).thenThrow(new RuntimeException());

        // call the mock method that throws the exception
        loginMock.getPassword();

    }
}