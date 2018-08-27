package com.automation.ui.base.common.prpreaders;

import com.automation.ui.base.common.constants.BASEConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class ExceptionErrorCodeReader extends Properties {

    private static ExceptionErrorCodeReader errorCodereader;

    public static ExceptionErrorCodeReader readProperty() {


        if (errorCodereader == null) {
            synchronized (ExceptionErrorCodeReader.class) {
                if (errorCodereader == null) {

                    errorCodereader = new ExceptionErrorCodeReader();
                    try {
                        InputStream inStream = new FileInputStream(new File(
                                BASEConstants.EXCEPTIONPATH));
                        errorCodereader.load(inStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
        }


        return errorCodereader;
    }

    public String getValue(final String key) {
        return errorCodereader.getProperty(key);
    }

    public boolean getBoolean(final String key) {
        return parseBoolean(getValue(key));
    }

    public int getInt(final String key) {
        return parseInt(getValue(key));
    }
}
