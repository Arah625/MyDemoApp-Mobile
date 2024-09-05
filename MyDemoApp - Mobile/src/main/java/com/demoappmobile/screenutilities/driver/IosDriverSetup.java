package com.demoappmobile.screenutilities.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IosDriverSetup extends BaseDriverSetup<IOSDriver> {

    private static IosDriverSetup instance;

    private IosDriverSetup() { }

    public static IosDriverSetup getInstance() {
        if (instance == null) {
            instance = new IosDriverSetup();
        }
        return instance;
    }

    @Override
    public void initDriver(String deviceName) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setApp(Configuration.APP_PATH);

        driver = new IOSDriver(new URL(Configuration.APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, Configuration.WAIT_TIME);
    }

    @Override
    public void initDriver(String deviceName, Duration timeout) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setApp(Configuration.APP_PATH);

        driver = new IOSDriver(new URL(Configuration.APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(driver, timeout);
    }
}
