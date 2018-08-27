package com.automation.ui.base.common.prpreaders;

import com.automation.ui.base.common.constants.BASEConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class BasePropertyReader extends Properties {

    private static BasePropertyReader corereader;

    public static BasePropertyReader readProperty() {

        if (corereader == null) {
            synchronized (BasePropertyReader.class) {
                if (corereader == null) {

                    corereader = new BasePropertyReader();
                    try {
                        InputStream inStream = new FileInputStream(new File(
                                BASEConstants.EXCEPTIONPATH));
                        corereader.load(inStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
        }

        return corereader;
    }

    public static BasePropertyReader readProperty(String fileName) {

        if (corereader == null) {
            synchronized (BasePropertyReader.class) {
                if (corereader == null) {

                    corereader = new BasePropertyReader();
                    try {
                        InputStream inStream = new FileInputStream(new File(
                                fileName));
                        corereader.load(inStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
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
