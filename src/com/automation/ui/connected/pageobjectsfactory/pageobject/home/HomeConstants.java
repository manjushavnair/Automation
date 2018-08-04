package com.automation.ui.connected.pageobjectsfactory.pageobject.home;

import org.openqa.selenium.support.FindBy;

public interface HomeConstants {

    public static String LOGOUTBUTTON = "//a[contains(text(),'Logout')]";
    public static String MENUBUTTON =  "//button[@id='ch-menu']";
   // public String ADDCONNECTIONSBUTTON = "//button[@class='primary ng-binding']";



    public String ADDCONNECTIONSBUTTON ="button.primary.ng-binding";



    public String ADDCONNECTIONSBUTTON_ERROR_MSG="//h1[contains(text(),'Access Denied')]";
    public String ADDCONNECTIONSBUTTON_ERROR_MSG_EXTRA ="//p[contains(text(),'You do not have permission to view this applicatio')]";

    //for github test
    public String NEWREPOCREATION = "a.btn.btn-sm.btn-primary.text-white";



}
