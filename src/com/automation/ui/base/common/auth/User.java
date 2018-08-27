package com.automation.ui.base.common.auth;

import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;

import java.io.File;

public enum User {


    FORGOTTEN_PASSWORD("ci.user.forgottenPassword.username", "ci.user.forgottenPassword.password"),
    USER("ci.user.regular.username", "ci.user.regular.password", "ci.user.regular.user_id", "ci.user.regular.access_token"),
    USER_2("ci.user.regular2.username", "ci.user.regular2.password"),
    ANONYMOUS("anonymous", "anonymous"),
    USER_CTEST("ci.user.ctest3.username", "ci.user.ctest3.password"),;

    private final String userName;

    private final String password;
    private final String filePath = Configuration.getCredentialsFilePath();
    private String userId;
    private String accessToken;

    User(String userNameKey, String passwordKey) {
        System.out.println("filePath" + filePath);
        this.userName = XMLReader.getValue(new File(filePath), userNameKey);
        this.password = XMLReader.getValue(new File(filePath), passwordKey);
        this.userId = "";
        this.accessToken = "";
    }

    User(String userNameKey, String passwordKey, String userId) {
        this.userName = XMLReader.getValue(new File(filePath), userNameKey);
        this.password = XMLReader.getValue(new File(filePath), passwordKey);
        this.userId = XMLReader.getValue(new File(filePath), userId);
        this.accessToken = "";
    }

    User(String userNameKey, String passwordKey, String userId, String accessToken) {
        this.userName = XMLReader.getValue(new File(filePath), userNameKey);
        this.password = XMLReader.getValue(new File(filePath), passwordKey);
        this.userId = XMLReader.getValue(new File(filePath), userId);
        this.accessToken = XMLReader.getValue(new File(filePath), accessToken);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
