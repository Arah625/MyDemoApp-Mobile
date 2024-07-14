package com.demoappmobile.screenutilities.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static DriverManager instance;
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private WebDriver webDriver;
    private WebDriverWait wait;

    private DriverManager() { }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    private static final String APP_PATH = "C:\\Users\\Chrystian\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk";
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public void initAndroidDriver(String deviceName) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(APP_PATH);

        androidDriver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(10));
    }

    public void initIOSDriver(String deviceName) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setApp(APP_PATH);

        iosDriver = new IOSDriver(new URL(APPIUM_SERVER_URL), options);
        wait = new WebDriverWait(iosDriver, Duration.ofSeconds(10));
    }

    public void initWebDriver(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        if (androidDriver != null) {
            return androidDriver;
        } else if (iosDriver != null) {
            return iosDriver;
        } else {
            return webDriver;
        }
    }

    public WebDriverWait getWebDriverWait() {
        return wait;
    }

    public WebDriverWait getWebDriverWait(Duration duration) {
        return new WebDriverWait(getDriver(), duration);
    }

    public void quitDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
        } else if (iosDriver != null) {
            iosDriver.quit();
        } else if (webDriver != null) {
            webDriver.quit();
        }
    }
}
