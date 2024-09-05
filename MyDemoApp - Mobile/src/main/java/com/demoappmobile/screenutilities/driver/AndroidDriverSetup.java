package com.demoappmobile.screenutilities.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverSetup extends BaseDriverSetup<AndroidDriver> {

    private static AndroidDriverSetup instance;

    private AndroidDriverSetup() { }

    public static AndroidDriverSetup getInstance() {
        if (instance == null) {
            instance = new AndroidDriverSetup();
        }
        return instance;
    }

    @Override
    public void initDriver(String deviceName) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(Configuration.APP_PATH);
        options.setCapability("chromedriver_autodownload", true);

        driver = new AndroidDriver(new URL(Configuration.APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, Configuration.WAIT_TIME);
    }

    @Override
    public void initDriver(String deviceName, Duration timeout) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(Configuration.APP_PATH);
        options.setCapability("chromedriver_autodownload", true);

        driver = new AndroidDriver(new URL(Configuration.APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, timeout);
    }
}
