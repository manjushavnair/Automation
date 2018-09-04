package com.automation.ui.base.common.core;

import com.automation.ui.base.common.core.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationRuntimeException;
import org.apache.commons.configuration.XMLConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XMLReader {

    private static final File defaultConfigFile = new File(Configuration.getCredentialsFilePath());
    private static XMLReader xml;
    private static XMLConfiguration xmlConf;
    private XMLReader() {

    }

    public static XMLReader readProperty(String fileName) {

        if (xml == null) {
            synchronized (XMLReader.class) {
                if (xml == null) {

                    xml = new XMLReader();
                    try {

                        xmlConf = new XMLConfiguration();
                        InputStream inStream = new FileInputStream(new File(fileName));
                        xmlConf.load(inStream);

                    } catch (ConfigurationException e) {
                        throw new ConfigurationRuntimeException(e);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
        }

        return xml;
    }

  /*
    public static String getValue(final String key) {
        return xmlConf.getString(key);
    }
    */

    /**
     * method used to get credentials from configuration xml
     */
    public static String getValue(File file, String key) {

        if (!file.exists() || file.isDirectory()) {
            throw new ConfigurationRuntimeException("Cannot find a file with credentials " + file);
        }

        try {
            XMLConfiguration xml = new XMLConfiguration(file);
            return xml.getString(key);
        } catch (ConfigurationException e) {
            throw new ConfigurationRuntimeException(e);
        }
    }

    public static String getValue(String key) {
        return getValue(defaultConfigFile, key);
    }
}
