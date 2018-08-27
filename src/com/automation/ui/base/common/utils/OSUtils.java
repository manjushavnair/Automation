package com.automation.ui.base.common.utils;

import org.apache.log4j.Logger;

import java.util.Properties;

public class OSUtils {

    public final static String FILE_SEPARATOR;
    private static Logger logger = Logger
            .getLogger(OSUtils.class);


    static {
        Properties props = System.getProperties();
        String separator = (String) props.get("file.separator");
        if (separator.equals("/")) {
            FILE_SEPARATOR = "/";
        } else {
            FILE_SEPARATOR = "\\";
        }
    }


    public static void callingGC() {
        Runtime getRT = Runtime.getRuntime();
        System.out.println("Memory status before gc at:	" + new java.util.Date() + " totalmemory : " + getRT.totalMemory() + " : freememory:" + getRT.freeMemory());
        System.gc();
        getRT = Runtime.getRuntime();
        System.out.println("Memory after gc at:	" + new java.util.Date() + " totalmemory : " + getRT.totalMemory() + " : freememory:" + getRT.freeMemory());
    }//Ending callingGC

}
