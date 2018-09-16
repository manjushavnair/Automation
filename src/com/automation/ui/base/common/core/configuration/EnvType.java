package com.automation.ui.base.common.core.configuration;

import lombok.Getter;

//NEEDTOCHECK CHANGE URL DOMAIN
public enum EnvType {
  //main domain,envtype,port
    PROD("com", "prod", ""),
    STAGING("co.in", "staging", ""),
    DEV("co.in", "dev", "9000"),
    TEST("",  "test", "9000"),
    SANDBOX("honeywell.com", "sandbox", "9000");

    @Getter
    private final String siteDomain;

    @Getter
    private final String key;


    @Getter
    private final String port;

    EnvType(String siteDomain, String key, String port) {
        this.siteDomain = siteDomain;
        this.key = key;
        this.port = port;
    }

    public String getSiteDomain() {


        return this.siteDomain;
    }

    public String getPort() {


        return this.port;
    }

    public String getKey() {


        return this.key;
    }
}
