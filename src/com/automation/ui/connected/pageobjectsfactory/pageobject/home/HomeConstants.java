package com.automation.ui.connected.pageobjectsfactory.pageobject.home;

public interface HomeConstants {

    public static String LOGOUTBUTTON = "//a[contains(text(),'Logout')]";
    public static String MENUBUTTON = "//button[@id='ch-menu']";

    public String ADDCONNECTIONSBUTTON = "//button[@class='primary ng-binding']";
    public String ADDCONNECTIONSBUTTON_TOP = "//button[@class='ng-binding ng-scope primary']";

    public String ADDCONNECTIONSBUTTON_ERROR_MSG = "//h1[contains(text(),'Access Denied')]";
    public String ADDCONNECTIONSBUTTON_ERROR_MSG_EXTRA = "//p[contains(text(),'You do not have permission to view this applicatio')]";

    public static String CLOUDDATABUTTON = "//a[contains(text(),'Cloud Data')]";

    public static String CLOUDAVATAR = "//img[contains(@ng-src,'content/images/useravatar.png')]";

//a[@class='cloud icon']


}
