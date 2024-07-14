package com.demoappmobile.screenutilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManager {
    private WebDriver driver;
    private WebDriverWait wait;

    public void initDriver(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
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
