package com.automation.ui.base.common.prpreaders;

import com.automation.ui.connected.common.constants.TestCONSTANTS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class PropertyReader extends Properties {

    private static PropertyReader corereader;

    public static PropertyReader readProperty() {

        if (corereader == null) {
            corereader = new PropertyReader();
        }

        try {
            InputStream inStream = new FileInputStream(new File(
                    TestCONSTANTS.CONFIGPATH));
            corereader.load(inStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return corereader;
    }

    public String getValue(final String key) {
        return corereader.getProperty(key);
    }

    public boolean getBoolean(final String key) {
        return parseBoolean(getValue(key));
    }

    public int getInt(final String key) {
        return parseInt(getValue(key));
    }
}
