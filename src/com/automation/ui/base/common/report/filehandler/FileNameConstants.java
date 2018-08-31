package com.automation.ui.base.common.report.filehandler;

import java.io.File;

public class FileNameConstants {

    /*
     * Folder name constants
     */
    //Where the report need to be created
    public static final String ROOT_FOLDER = "." + File.separator + "logs" + File.separator + "realreport";


    public static final String HYPHEN = "-";

    public static final String CSS_FOLDER = "css";

    public static final String JS_FOLDER = "js";

    public static final String FONT_FOLDER = "fonts";

    public static final String IMAGE_FOLDER = "image";

    public static final String XML_FOLDER = "xml";

    public static final String XSL_FOLDER = "xsl";

    // where to find the source css /js to be copied to the target location
    public static final String RESOURCE_FOLDER = "." + File.separator + "resources" + File.separator + "html-rsc";

	/*
     * XML file name used in CreateXML class
	 */

    public static final String XML_FILE_NAME = "realTimeResults";

	/*
	 * XSL file name used in CreateHTML class
	 */

    public static final String DASHBOARD_XSL = "dashboard.xsl";

	/*
	 * public static final String GROUP_PAGE_XSL = "group-page.xsl";
	 *
	 * public static final String CLASS_PAGE_XSL = "class-page.xsl";
	 */

    public static final String DASHBOARD_HTML = "log";

    public static final String INDEX_HTML = "index.html";

	/*
	 * public static final String GROUP_PAGE_HTML = "bygroup.html";
	 *
	 * public static final String CLASS_PAGE_HTML = "byclass.html";
	 */

	/*
	 * index.html static content
	 */

    public static final String INDEX_HEADER = "<!DOCTYPE HTML>" + "<html>" + "<head>" + "<title>Run Time Report</title>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
            + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
            + "<link href='realreport/css/bootstrap.min.css' rel='stylesheet' type='text/css' />"
            + "<link href='realreport/css/new-style.css' rel='stylesheet' type='text/css' />"
            + "<link href='realreport/css/font-awesome.min.css' rel='stylesheet'>"
            + "<script src='realreport/js/jquery-1.10.2.min.js'></script>" + "<script src='realreport/js/bootstrap.min.js'></script>"
            + "</head>";

    public static final String INDEX_BODY_PRE = "<body class='body-w-bg'>" + "<div class='container-fluid'>"
            + "<div class='col-md-3'>" + "</div>" + "<div class='col-md-6 center hazy gap-top'>"
            + "<div class='txt'></div>" + "<h1> <i class='fa fa-line-chart'></i> Real Time Report </h1>";

    public static final String INDEX_BODY_POST = "</div>" + "<div class='col-md-3'>" + "</div>" + "</div>" + "</body>"
            + "</html>";
}
