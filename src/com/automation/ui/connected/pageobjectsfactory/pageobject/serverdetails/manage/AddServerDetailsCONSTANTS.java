package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface AddServerDetailsCONSTANTS {





    public String ADDCONNECTIONSCANELBUTTON = "//button[@class='ng-binding ng-scope secondaryLink']";
    public String ADDCONNECTIONSNEXTBUTTON = "//button[@class='primary next ng-binding']";

    public String ADDCONNECTIONNAMEERROR = "//label[contains(text(),'Connection name is required')]";
    public String CUSTOMERNAME_ERMSG = "//li[@class='multipleElements']//label[@class='errorText ng-binding ng-scope'][contains(text(),'Connection name is required')]";
    public String CUSOTMERNAME = "//input[@id='custName']";
    public String SITENAME = "//input[@id='siteName']";
    public String SITENAME_ERMSG = "//label[contains(text(),'Site name is required')]";

    public String ADDCONNECTIONNAME = "//input[@id='connName']";

    public String CONNECTION_SERVERNAME = "//input[@id='sName']";

    public String CONNECTION_SERVERTYPE = "//select[@id='stype']";


    public String CONNECTION_PORT = "//input[@id='port']";
    public String CONNECTION_SERVER_AUTHUSERNAME = "//input[@id='uName']";
    public String CONNECTION_SERVER_AUTHPASSWORD = "//input[@id='pass']";

    public String CONNECTION_TO_SERVER = "//a[@class='link ng-binding ng-scope']";

    public String QUERY ="//textarea[@id='tagQuery']";
    public String OPCUA_SERVER_URL = "//input[@id='opcName']";

    public String MIN_KEY_SIZE = "//select[@id='mks']";

    public String SECURITY_ENABLE_FLAG = "//input[@id='es2']";
    public String SECURITY_CERT_ACCEPT = "//ul[@class='form verticalForm']//li[1]//span[1]//span[1]//label[1]//span[1]";
    public String SECURITY_DOMAIN_CHECK = "//div[@class='ch-pageContent ng-scope']";


    public String SECURITY_YES = "//label[@for='es1']//span";
    public String SECURITY_NO = "//label[@for='es2']//span";

    public String SECURITY_CONNECTION_WARNING= "//h1[contains(text(),'Connection is not secure')]";
    public String CONNECTION_STATUS = "//label[@class='success ng-binding ng-scope']";

    public String CONNECTION_NEXT = "//button[@class='primary next ng-binding']";

    public String CONNECTION_MAKEITPRIVATE ="//a[@id='makeItPrivate']";

    public String CONNECTION_MAKEITPRIVATE_CLOSE="//div[@id='nonSecBanner']//a[@id='closeFlag']";
}
