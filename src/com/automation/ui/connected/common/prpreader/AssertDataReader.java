package com.automation.ui.connected.common.prpreader;

import java.util.Locale;
import java.util.ResourceBundle;
import  com.automation.ui.connected.common.constants.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class AssertDataReader {


    private static AssertDataReader assertreader;

    ResourceBundle rBundle=null;

    private AssertDataReader()
    {
        rBundle= ResourceBundle.getBundle(SITECONSTANTS.ASSERTPATH, Locale.US);
    }

    public static AssertDataReader readProperty() {

        if (assertreader == null) {
            synchronized (AssertDataReader.class) {
                if (assertreader == null) {
                    assertreader = new AssertDataReader();
                }
            }
        }

        return assertreader;
    }

    public String getValue(final String key) {
        return rBundle.getString(key);
    }


}
