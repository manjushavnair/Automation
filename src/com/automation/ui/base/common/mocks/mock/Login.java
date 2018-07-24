package com.automation.ui.base.common.mocks.mock;

public class Login {

    String username;
    String password;

    public Login() {
        this.username = "";
        this.password = "";
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (this.username.toLowerCase().equals("bas") && this.password.toLowerCase().equals("ok")) {
            return "login_ok";
        } else {
            return "login_nok";
        }
    }
}