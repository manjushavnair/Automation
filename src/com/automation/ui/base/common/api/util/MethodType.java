package com.automation.ui.base.common.api.util;

public enum MethodType {

    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    /**
     * Will actually use DELETE verb, but also allow a payload to be sent. This
     * is to work around the Apache HTTP client removing the payload for DELETE
     * requests. This value is for internal use only and not to be used directly
     * when calling the HttpRequest action.
     */
    DELETE_WITH_BODY("DELETE_WITH_BODY"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH"),
    PUT("PUT");

    private String methodType;

    private MethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodType() {
        return methodType;
    }

    @Override
    public String toString() {
        return this.getMethodType();
    }
}
