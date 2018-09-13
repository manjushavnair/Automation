package com.automation.ui.base.common.api.util;

public enum ContentType {

    JSON("application/json; charset=utf-8"),
    XML("text/xml; charset=utf-8"),


    APPLICATION_JAVASCRIPT("application/javascript"),
     APPLICATION_XML ("application/xml"),
   TEXT_CSV ( "text/csv"),
 TEXT_HTML ( "text/html"),
  TEXT_PLAIN ( "text/plain");

    private String contentType;

    private ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return this.getContentType();
    }
}