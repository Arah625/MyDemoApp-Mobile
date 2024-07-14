package com.demoappmobile.screenutilities.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverManager {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private static final String APP_PATH = "C:\\Users\\Chrystian\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public void initDriver(String deviceName) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(APP_PATH);

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return wait;
    }

    public WebDriverWait getWebDriverWait(Duration duration) {
        return new WebDriverWait(driver, duration);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
