package com.demoappmobile.screenutilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class Frame {

    private AppiumDriver driver;

    public Frame(AppiumDriver driver) {
        this.driver = driver;
    }

    public void switchToiFrame(WebElement webElement) {
        driver.switchTo().frame(webElement);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
