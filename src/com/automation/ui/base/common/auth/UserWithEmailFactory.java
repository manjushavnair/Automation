package com.automation.ui.base.common.auth;


import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.properties.Credentials;

public class UserWithEmailFactory {

    private static final Credentials CREDENTIALS = Configuration.getCredentials();

    private UserWithEmailFactory() {
        // no-op
    }

    public static UserWithEmail getUser() {
        return new UserWithEmail(User.FORGOTTEN_PASSWORD,
                CREDENTIALS.forgottenPasswordEmail1Address,
                CREDENTIALS.forgottenPasswordEmail1Password);
    }


}
