package com.automation.ui.connected.elements.fandom;

import com.automation.ui.connected.elements.fandom.components.WPNotifications;
import lombok.Getter;

public abstract class FandomWPPage<T> extends FandomPage<T> {

    @Getter(lazy = true)
    private final WPNotifications notifications = new WPNotifications();
}
