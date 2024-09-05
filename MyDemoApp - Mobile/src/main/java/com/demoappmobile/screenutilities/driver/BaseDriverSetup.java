package com.demoappmobile.screenutilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class BaseDriverSetup<T extends WebDriver> implements DriverFactory<T> {

    protected T driver;
    protected WebDriverWait wait;

    @Override
    public T getDriver() {
        return driver;
    }

    @Override
    public WebDriverWait getWebDriverWait() {
        return wait;
    }

    @Override
    public WebDriverWait getWebDriverWait(Duration duration) {
        return new WebDriverWait(getDriver(), duration);
    }

    @Override
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration timeout, Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        ensureWebDriverInitialized();
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval);
        fluentWait.ignoreAll(Arrays.asList(exceptionsToIgnore));
        return fluentWait;
    }

    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval, Class<? extends Throwable>... exceptionsToIgnore) {
        return getFluentWait(Configuration.WAIT_TIME, pollingInterval, exceptionsToIgnore);
    }

    @SafeVarargs
    public final FluentWait<WebDriver> getFluentWait(Class<? extends Throwable>... exceptionsToIgnore) {
        return getFluentWait(Configuration.WAIT_TIME, Configuration.POLLING_INTERVAL, exceptionsToIgnore);
    }

    public final FluentWait<WebDriver> getFluentWait() {
        return getFluentWait(Configuration.WAIT_TIME, Configuration.POLLING_INTERVAL, NoSuchElementException.class);
    }

    public final FluentWait<WebDriver> getFluentWait(Duration pollingInterval) {
        return getFluentWait(Configuration.WAIT_TIME, pollingInterval, NoSuchElementException.class);
    }

    private void ensureWebDriverInitialized() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized.");
        }
    }
}
