package com.demoappmobile.screenutilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public interface DriverFactory<T extends WebDriver> {

    void initDriver(String deviceName) throws Exception;
    void initDriver(String deviceName, Duration timeout) throws Exception;
    T getDriver();
    WebDriverWait getWebDriverWait();
    WebDriverWait getWebDriverWait(Duration duration);
    void quitDriver();
}