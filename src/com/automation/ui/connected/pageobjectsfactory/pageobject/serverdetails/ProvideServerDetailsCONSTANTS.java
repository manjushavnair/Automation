package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

public interface ProvideServerDetailsCONSTANTS {

    //button[@class='primary next ng-binding']


    public String ADDCONNECTIONSCANELBUTTON = "//button[@class='ng-binding ng-scope secondaryLink']";
    public String ADDCONNECTIONSNEXTBUTTON = "//button[@class='primary next ng-binding']";
    public String ADDCONNECTIONNAME= "//input[@id='connName']";
    public String ADDCONNECTIONNAMEERROR= "//label[contains(text(),'Connection name is required')]";
    public String CUSTOMERNAME_ERMSG= "//li[@class='multipleElements']//label[@class='errorText ng-binding ng-scope'][contains(text(),'Connection name is required')]";
    public String CUSOTMERNAME= "//input[@id='custName']";
    public String SITENAME= "//input[@id='siteName']";
    public String SITENAME_ERMSG= "//label[contains(text(),'Site name is required')]";









}
