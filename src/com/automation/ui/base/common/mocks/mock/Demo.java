package com.automation.ui.base.common.mocks.mock;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Demo {
    private WebDriver mockDriver;
    private WebElement mockElement;

    public void setUp() {
        this.mockDriver = mock(WebDriver.class);
        // this.mockElement = mock(WebElement.class,
        // withSettings().name("elementName"));
        when(this.mockElement.getAttribute("name")).thenReturn("elementName");

    }

    public String getName(String id) {
        WebElement testElement = mockDriver.findElement(By.id(id));
        return testElement.getAttribute("name");
    }

    @Test
    public void assertElementName() throws InterruptedException {
        Assert.assertTrue(getName("testmock").equals("elementName"));
    }

}
