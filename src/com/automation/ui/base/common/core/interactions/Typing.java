package com.automation.ui.base.common.core.interactions;

import com.automation.ui.base.common.logging.Log;
import org.openqa.selenium.WebElement;

public class Typing {

    private Typing() {
    }


    public static void sendKeysHumanSpeed(WebElement input, String keys) {
        int interval = 182;
        for (char c : keys.toCharArray()) {
            String character = String.valueOf(c);
            input.sendKeys(character);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Log.log("ERROR WHILE TYPING", e, false);
            }
        }
    }
}
