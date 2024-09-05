package com.demoappmobile.screenutilities.driver;

import com.demoappmobile.screenutilities.ContextSwitcher;
import com.demoappmobile.screenutilities.Frame;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {

    private static DriverManager instance;
    private DriverFactory<? extends WebDriver> driverFactory;
    private WebDriverWait wait;
    private ContextSwitcher contextSwitcher;
    private Frame frame;

    private DriverManager() { }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void initAndroidDriver(String deviceName) {
        try {
            AndroidDriverSetup androidDriverSetup = AndroidDriverSetup.getInstance();
            androidDriverSetup.initDriver(deviceName);
            driverFactory = androidDriverSetup;
            wait = androidDriverSetup.getWebDriverWait();
            contextSwitcher = new ContextSwitcher(androidDriverSetup.getDriver());
            frame = new Frame(androidDriverSetup.getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Android driver", e);
        }
    }

    public void initAndroidDriver(String deviceName, Duration timeout) {
        try {
            AndroidDriverSetup androidDriverSetup = AndroidDriverSetup.getInstance();
            androidDriverSetup.initDriver(deviceName, timeout);
            driverFactory = androidDriverSetup;
            wait = androidDriverSetup.getWebDriverWait();
            contextSwitcher = new ContextSwitcher(androidDriverSetup.getDriver());
            frame = new Frame(androidDriverSetup.getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Android driver", e);
        }
    }

    public void initIOSDriver(String deviceName) {
        try {
            IosDriverSetup iosDriverSetup = IosDriverSetup.getInstance();
            iosDriverSetup.initDriver(deviceName);
            driverFactory = iosDriverSetup;
            wait = iosDriverSetup.getWebDriverWait();
            contextSwitcher = new ContextSwitcher(iosDriverSetup.getDriver());
            frame = new Frame(iosDriverSetup.getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize iOS driver", e);
        }
    }

    public void initIOSDriver(String deviceName, Duration timeout) {
        try {
            IosDriverSetup iosDriverSetup = IosDriverSetup.getInstance();
            iosDriverSetup.initDriver(deviceName, timeout);
            driverFactory = iosDriverSetup;
            wait = iosDriverSetup.getWebDriverWait();
            contextSwitcher = new ContextSwitcher(iosDriverSetup.getDriver());
            frame = new Frame(iosDriverSetup.getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize iOS driver", e);
        }
    }

    public void initWebDriver(String chromeDriverPath) {
        try {
            WebDriverSetup webDriverSetup = WebDriverSetup.getInstance();
            webDriverSetup.initDriver(chromeDriverPath);
            driverFactory = webDriverSetup;
            wait = webDriverSetup.getWebDriverWait();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Web driver", e);
        }
    }

    public void initWebDriver(String chromeDriverPath, Duration timeout) {
        try {
            WebDriverSetup webDriverSetup = WebDriverSetup.getInstance();
            webDriverSetup.initDriver(chromeDriverPath, timeout);
            driverFactory = webDriverSetup;
            wait = webDriverSetup.getWebDriverWait();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Web driver", e);
        }
    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }

    public WebDriverWait getWebDriverWait() {
        return wait;
    }

    public WebDriverWait getWebDriverWait(Duration duration) {
        return driverFactory.getWebDriverWait(duration);
    }

    public FluentWait<WebDriver> getFluentWait(Duration timeout, Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        return ((BaseDriverSetup<? extends WebDriver>) driverFactory).getFluentWait(timeout, pollingInterval, exceptionsToIgnore);
    }

    public FluentWait<WebDriver> getFluentWait(Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        return ((BaseDriverSetup<? extends WebDriver>) driverFactory).getFluentWait(pollingInterval, exceptionsToIgnore);
    }

    public FluentWait<WebDriver> getFluentWait(Class<? extends Throwable>... exceptionsToIgnore) {
        return ((BaseDriverSetup<? extends WebDriver>) driverFactory).getFluentWait(exceptionsToIgnore);
    }

    public FluentWait<WebDriver> getFluentWait() {
        return ((BaseDriverSetup<? extends WebDriver>) driverFactory).getFluentWait();
    }

    public ContextSwitcher getContextSwitcher() {
        return contextSwitcher;
    }

    public Frame getFrame() {
        return frame;
    }

    public void quitDriver() {
        if (driverFactory != null) {
            driverFactory.quitDriver();
        }
    }
}
