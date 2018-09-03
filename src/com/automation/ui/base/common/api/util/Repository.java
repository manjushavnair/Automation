package com.automation.ui.base.common.api.util;

public enum Repository {

    USER_NAME("username"),
    PASSWORD("password"),
    METHOD("method"),
    ENDPOINT("endpoint"),
    ID("id"),
    APPID("APPID");


    public static final String PROPERTIES = "resources/properties/restapi.properties";
    private String value;

    private Repository(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}