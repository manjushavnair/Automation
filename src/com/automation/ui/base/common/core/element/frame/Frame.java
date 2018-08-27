package com.automation.ui.base.common.core.element.frame;

import com.automation.ui.base.common.driverprovider.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Frame {
    private final WebDriver webDriver;
    private final WebElement frameElement;

    public Frame(WebElement frameElement) {
        this.webDriver = DriverProvider.getActiveDriver();
        this.frameElement = frameElement;
    }

    public void switchToFrame(By locator) {
        //   webDriver.switchTo().frame(getElement(locator));


    }

    public void switchToFrame(String nameOrId) {
        webDriver.switchTo().frame(nameOrId);

    }


    public void frameScope(FrameScope frameScope) {
        try {
            webDriver.switchTo().frame(frameElement);
            frameScope.execute();
        } finally {
            webDriver.switchTo().defaultContent();
        }
    }

    public <T> T frameScope(Supplier<T> frameScopedSupplier) {
        try {
            webDriver.switchTo().frame(frameElement);
            return frameScopedSupplier.get();
        } finally {
            webDriver.switchTo().defaultContent();
        }
    }

}
