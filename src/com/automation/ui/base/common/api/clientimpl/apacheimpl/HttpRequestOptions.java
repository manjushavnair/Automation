package com.automation.ui.base.common.api.clientimpl.apacheimpl;

import com.automation.ui.base.common.api.util.MethodType;

public class HttpRequestOptions {

    public boolean ignoreCert;

    public String url;

    public MethodType httpVerb;

    public String proxy;

    public HttpRequestOptions(String url, MethodType httpVerb) {
        this.url = url;
        this.httpVerb = httpVerb;
        this.proxy = null;
        this.ignoreCert = false;
    }
}
