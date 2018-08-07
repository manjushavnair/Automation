package com.automation.ui.base.common.auth;


public interface FormPage {

    String getError();

    void submit();

    FormPage open();

    boolean isDisplayed();

    boolean submitButtonNotClickable();

}
