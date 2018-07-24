package com.automation.ui.connected.common.utils;

import com.automation.ui.base.common.properties.Credentials;
import com.automation.ui.connected.common.dataprovider.VisualEditorDataProvider.EditorPref;

public class MyUtilsFuture {

    public String getUserBaseOnEditorPref(EditorPref editorPref) {
        switch (editorPref) {
            case VE:
                return new Credentials().getUserNameVEPreferred();
            case CK:

                return new Credentials().getUserNameCKPreferred();
            case SRC:
                return new Credentials().getUserNameSourcePreferred();
            default:
                return "";
        }
    }

    public String getPassBaseOnEditorPref(EditorPref editorPref) {
        switch (editorPref) {
            case VE:
                return new Credentials().getPasswordVEPreferred();
            case CK:
                return new Credentials().getPasswordCKPreferred();
            case SRC:
                return new Credentials().getPasswordSourcePreferred();
            default:
                return "";
        }
    }
}
