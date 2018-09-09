package com.automation.ui.base.common.constants;

import java.io.File;

public interface BASEConstants {

    public static String FIREFOX = "FIREFOX";
    public static String CHROME = "CHROME";
    public static String IE = "internet-explorer";

    public static String EXCEPTIONPATH = "resources/properties/exception.properties";
    public static String ASSERTPATH = "i18n.assertdata";

    public static String LOGINJSONDATAPATH = "resources/logindataselenium.json";

    public static String X_UI_Internal_Request = "X-UI-Internal-Request";
    public static String X_CLIENT_IP = "X-Client-Ip";
    public static String X_SITE_INTERNAL_REQUEST = "X-Site-Internal-Request";
    public static String X_Site_AccessToken = "X-Site-AccessToken";

    public static final String IMAGE_UPLOAD_RESOURCES_PATH =
            File.separator + "resources"
                    + File.separator + "ImagesForUploadTests" + File.separator;


    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

    public static final int DEFAULT_TIMEOUT = 15;
    public static final int DEFAULT_SLEEP = 5000;
    public static final String INIT_MESSAGE = "INIT ELEMENT";
    public static final String INIT_ERROR_MESSAGE = "PROBLEM WITH ELEMENT INIT";
    public static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
    public static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

    public static int WAITTIME300SEC = 300;
    public static int WAITTIME500SEC = 300;
    public static int WAITTIME160SEC = 160;
    public static int WAITTIME120SEC = 120;
    public static int WAITTIME100SEC = 100;
    public static int WAITTIME80SEC = 80;
    public static int WAITTIME60SEC = 60;
    public static int WAITTIME30SEC = 30;
    public static int WAITTIME20SEC = 20;
    public static int WAITTIME15SEC = 15;
    public static int WAITTIME10SEC = 10;
    public static int WAITTIME5SEC = 5;

    public static int WAITTIME250MILLISEC = 250;
    public static int WAITTIME500MILLISEC = 500;
    public static int WAITTIME1000MILLISEC = 1000;
    public static int WAITTIME5000MILLISEC = 5000;
    public static int WAITTIME10000MILLISEC = 10000;//10 sec
    public static int WAITTIME20000MILLISEC = 20000;//20 sec
    public static int WAITTIME30000MILLISEC = 30000;//30 sec
    public static int WAITTIME40000MILLISEC = 40000;//40 sec
    public static int WAITTIME50000MILLISEC = 50000;//50 sec
    public static int WAITTIME60000MILLISEC = 60000;//60 sec
    public static int WAITTIME80000MILLISEC = 80000;//80 sec
    public static int WAITTIME100000MILLISEC = 100000;//100 sec


}
