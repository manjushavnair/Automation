package com.automation.ui.connected.common.dataprovider;

import org.testng.annotations.DataProvider;

public class SiteDataProvider {

    private SiteDataProvider() {

    }

    @DataProvider
    private static final Object[][] getLangs() {
        return new Object[][]
                {
                        {"de", "Detch", "2"},
                        {"es", "Espion", "2"},
                        {"fr", "French", "2"},
                        {"it", "Italian", "2"},
                        {"ja", "Jakrthe", "2"},
                        {"nl", "Newland", "2"}
                };
    }

    @DataProvider
    private static final Object[][] getLangSecondHalf() {
        return new Object[][]{{"no"}, {"pl"}, {"pt"}, {"pt-br"}, {"ru"}, {"zh"}};
    }

    public static void main(String ar[]) {
        System.out.println(getLangs()[0][0]);
        System.out.println(getLangs()[0][1]);
        System.out.println(getLangs()[0][2]);

        System.out.println(getLangs()[1][0]);
        System.out.println(getLangs()[1][1]);

    }
}
