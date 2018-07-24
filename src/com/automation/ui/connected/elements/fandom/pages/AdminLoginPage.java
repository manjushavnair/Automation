package com.automation.ui.connected.elements.fandom.pages;

import com.automation.ui.connected.common.core.helpers.FandomUser;
import com.automation.ui.connected.elements.fandom.FandomPage;
import com.automation.ui.connected.elements.fandom.components.WPLoginBox;
import lombok.Getter;

public class AdminLoginPage extends FandomPage<AdminLoginPage> {
    private final String url = SITE_URL + "wp-admin";

    @Getter(lazy = true)
    private final WPLoginBox loginBox = new WPLoginBox();

    @Override
    public AdminLoginPage open() {
        getUrl(url);

        return this;
    }

    public AdminLoginPage Login(FandomUser user) {
        getLoginBox().login(user);

        return this;
    }
}
