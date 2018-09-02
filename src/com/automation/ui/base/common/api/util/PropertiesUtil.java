package com.automation.ui.base.common.api.util;

import com.automation.ui.base.common.prpreaders.BasePropertyReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class PropertiesUtil {
    private String dataLocation;
    private BasePropertyReader properties;

    private PropertiesUtil(String dataLocation) throws IOException {
        this.dataLocation = dataLocation;
        properties = BasePropertyReader.readProperty(this.dataLocation);
    }

    public static PropertiesUtil create(String dataLocation) throws IOException {
        return new PropertiesUtil(dataLocation);
    }

    static <T> T valueOf(Class<T> klazz, String arg) {
        Exception cause = null;
        T ret = null;
        try {
            ret = klazz.cast(klazz.getDeclaredMethod("valueOf", String.class).invoke(null, arg)
            );
        } catch (NoSuchMethodException e) {
            cause = e;
        } catch (IllegalAccessException e) {
            cause = e;
        } catch (InvocationTargetException e) {
            cause = e;
        }
        if (cause == null) {
            return ret;
        } else {
            throw new IllegalArgumentException(cause);
        }
    }


    @SuppressWarnings("unchecked")
    public <T> T getProperties(String name) {
        T prop = null;
        try {

            String temp = properties.getProperty(name);
            prop = (T) temp;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public <T> T getProperties(String name, Class<T> type) {
        T prop = null;
        try {

            String temp = properties.getProperty(name);
            prop = valueOf(type, temp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String data(Repository value) {
        return getProperties(value.getValue());
    }
}
