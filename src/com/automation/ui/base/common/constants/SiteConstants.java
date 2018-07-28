package com.automation.ui.base.common.constants;

import java.io.File;

public interface SiteConstants {
    public static final String IMAGE_UPLOAD_RESOURCES_PATH =
            "." + File.separator + "src" + File.separator + "test" + File.separator + "resources"
                    + File.separator + "ImagesForUploadTests" + File.separator;

    public static final String TITLE_FOR_PASS = "Forgot password";

    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

    public static final int DEFAULT_TIMEOUT = 15;
    public static final int DEFAULT_SLEEP = 5000;
    public static final String INIT_MESSAGE = "INIT ELEMENT";
    public static final String INIT_ERROR_MESSAGE = "PROBLEM WITH ELEMENT INIT";
    public static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
    public static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

}
