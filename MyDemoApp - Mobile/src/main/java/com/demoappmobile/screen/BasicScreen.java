package com.demoappmobile.screen;

import com.demoappmobile.screenutilities.ContextSwitcher;
import com.demoappmobile.screenutilities.Frame;
import com.demoappmobile.screenutilities.action.CommonMethods;
import com.demoappmobile.screenutilities.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.autoutils.action.SwipeHandler;
import org.autoutils.detection.ElementFinder;
import org.autoutils.visibility.VisibilityHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicScreen {

    protected AppiumDriver appiumDriver;
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    protected FluentWait<WebDriver> fluentWait;
    protected ElementFinder elementFinder;
    protected CommonMethods commonMethods;
    protected ContextSwitcher contextSwitcher;
    protected Frame frame;
//    protected ElementVisibilityHandler elementVisibilityHandler;
    protected VisibilityHandler visibilityHandler;
    protected SwipeHandler swipeHandler;

    public BasicScreen(WebDriver driver) {
        this.driver = driver;
        // Casting WebDriver to AppiumDriver
        if (driver instanceof AppiumDriver) {
            this.appiumDriver = (AppiumDriver) driver;
        } else {
            throw new IllegalArgumentException("Driver must be an instance of AppiumDriver");
        }
        this.webDriverWait = DriverManager.getInstance().getWebDriverWait();
        this.elementFinder = new ElementFinder(driver, webDriverWait);
        this.commonMethods = new CommonMethods();
        this.visibilityHandler = new VisibilityHandler(driver, webDriverWait,fluentWait);
        this.contextSwitcher = DriverManager.getInstance().getContextSwitcher();
        this.swipeHandler = new SwipeHandler(appiumDriver);
        this.frame = DriverManager.getInstance().getFrame();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}