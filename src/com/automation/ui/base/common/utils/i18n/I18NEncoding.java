package com.automation.ui.base.common.utils.i18n;


public class I18NEncoding {

    private String fTitle;
    private String fShortTitle;
    private String fEncoding;

    private I18NEncoding() {
    }

    public I18NEncoding(String encoding, String title, String short_title) {
        this.fEncoding = encoding;
        this.fTitle = title;
        this.fShortTitle = short_title;
    }

    public String getEncoding() {
        return this.fEncoding;
    }

    public String getTitle() {
        return this.fTitle;
    }

    public String getShortTitle() {
        return this.fShortTitle;
    }

}
