package com.automation.ui.base.pageobjectsfactory.pageobject.auth;


public interface FormPage {

    String getError();

    void submit();

    FormPage open();

    boolean isDisplayed();

    boolean submitButtonNotClickable();

}
