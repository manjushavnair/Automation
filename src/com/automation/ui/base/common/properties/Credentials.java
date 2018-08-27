package com.automation.ui.base.common.properties;

import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;

import java.io.File;

public class Credentials {

    public final String youTubeApiKey;
    public final String userName;
    public final String password;

    public final String userName2;
    public final String password2;


    public final String email;
    public final String emailPassword;

    public final String forgottenPasswordEmail1Address;
    public final String forgottenPasswordEmail1Password;


    public final String apiToken;

    public Credentials() {
        File file = new File(Configuration.getCredentialsFilePath());
        userName = XMLReader.getValue(file, "ci.user.regular.username");
        password = XMLReader.getValue(file, "ci.user.regular.password");
        userName2 = XMLReader.getValue(file, "ci.user.regular2.username");
        password2 = XMLReader.getValue(file, "ci.user.regular2.password");


        email = XMLReader.getValue(file, "ci.email.generic.username");
        emailPassword = XMLReader.getValue(file, "ci.email.generic.password");


        forgottenPasswordEmail1Address = XMLReader.getValue(file, "ci.email.forgotPass1.username");
        forgottenPasswordEmail1Password = XMLReader.getValue(file, "ci.email.forgotPass1.password");


        apiToken = XMLReader.getValue(file, "ci.api.token");

        youTubeApiKey = XMLReader.getValue(file, "ci.api.youtube.key");
    }

    public String getYouTubeApiKey() {
        return youTubeApiKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName2() {
        return userName2;
    }

    public String getPassword2() {
        return password2;
    }


    public String getEmail() {
        return email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }


    public String getForgottenPasswordEmail1Address() {
        return forgottenPasswordEmail1Address;
    }

    public String getForgottenPasswordEmail1Password() {
        return forgottenPasswordEmail1Password;
    }


    public String getApiToken() {
        return apiToken;
    }


}
