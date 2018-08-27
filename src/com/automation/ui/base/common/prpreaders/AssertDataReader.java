package com.automation.ui.base.common.prpreaders;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.utils.i18n.LocaleUtil;

import java.util.ResourceBundle;

public class AssertDataReader {


    public static AssertDataReader assertreader;

    private ResourceBundle rBundle = null;

    private AssertDataReader(String langauge) {
        rBundle = ResourceBundle.getBundle(BASEConstants.ASSERTPATH, LocaleUtil.getLocaleFromLanguage(langauge));
    }

    public static AssertDataReader readProperty(String langauge) {
        if (assertreader == null) {
            synchronized (AssertDataReader.class) {
                if (assertreader == null) {
                    assertreader = new AssertDataReader(langauge);
                }
            }
        }
        return assertreader;
    }

    public String getValue(final String key) {
        return rBundle.getString(key);
    }


}
