package com.automation.ui.connected.common.prpreader;

import java.util.Locale;
import java.util.ResourceBundle;

public class AssertDataReader {


    private static ResourceBundle assertreader;

    public static ResourceBundle readProperty() {

        if (assertreader == null) {
            assertreader = ResourceBundle.getBundle("assertdata", Locale.US);

        }


        return assertreader;
    }


}
