package com.demoappmobile.screenutilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverSetup extends BaseDriverSetup<WebDriver> {

    private static WebDriverSetup instance;

    private WebDriverSetup() { }

    public static WebDriverSetup getInstance() {
        if (instance == null) {
            instance = new WebDriverSetup();
        }
        return instance;
    }

    @Override
    public void initDriver(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Configuration.WAIT_TIME);
    }

    @Override
    public void initDriver(String chromeDriverPath, Duration timeout) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, timeout);
    }
}
