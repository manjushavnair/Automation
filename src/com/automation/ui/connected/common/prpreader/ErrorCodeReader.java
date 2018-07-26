package com.automation.ui.connected.common.prpreader;

import com.automation.ui.connected.common.constants.TestCONSTANTS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class ErrorCodeReader extends Properties {

    private static ErrorCodeReader errorCodereader;

    public static ErrorCodeReader readProperty() {


        if (errorCodereader == null) {
            synchronized (ErrorCodeReader.class) {
                if (errorCodereader == null) {

                    errorCodereader = new ErrorCodeReader();
                    try {
                        InputStream inStream = new FileInputStream(new File(
                                TestCONSTANTS.EXCEPTIONPATH));
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
