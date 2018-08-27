package com.automation.ui.base.common.auth;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class UserWithEmail {

    private User user;
    private String email;
    private String emailPassword;

    public String getUsername() {
        return user.getUserName();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
