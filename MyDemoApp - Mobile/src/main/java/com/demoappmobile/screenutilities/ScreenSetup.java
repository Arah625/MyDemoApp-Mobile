package com.demoappmobile.screenutilities;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for all screen objects within the application. It initializes the WebDriver and WebDriverWait instances
 * necessary for interacting with mobile screen. Additionally, it uses Selenium's PageFactory to initialize web elements.
 */
public class ScreenSetup {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    /**
     * Constructor for ScreenSetup. Initializes the WebDriver and WebDriverWait instances using the singleton WebDriverSetup.
     * Also initializes screen elements with Selenium's PageFactory.
     *
     * @param webDriver The WebDriver instance to be used for screen interactions. This is typically obtained
     *                  from WebDriverSetup's singleton instance, ensuring consistent WebDriver usage across screen objects.
     */
    public ScreenSetup(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(webDriver), this);
    }
}