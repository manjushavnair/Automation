package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails;

public interface AddServerDetailsCONSTANTS {

    //button[@class='primary next ng-binding']


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

    public String SECURITY_ENABLE_FLAG = "//body[@class='ng-scope']//div[@class='ch-content ch-menuOpen']//div[@class='ng-scope']//div[@class='ch-pageContent ng-scope']//div[@class='ng-scope']//div[@class='ch-wizard ng-scope']//div[@class='ch-contentWrapper']//div[@class='ng-scope']//section[@class='content']//ul[@class='form verticalForm']//li//span//span[@class='highlightOption']//label[2]//span[1]";

    public String SECURITY_CERT_ACCEPT = "//ul[@class='form verticalForm']//li[1]//span[1]//span[1]//label[1]//span[1]";
    public String SECURITY_DOMAIN_CHECK = "//div[@class='ch-pageContent ng-scope']";


}
