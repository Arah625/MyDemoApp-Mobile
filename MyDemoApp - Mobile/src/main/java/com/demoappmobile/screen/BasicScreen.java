package com.demoappmobile.screen;

import com.demoappmobile.screenutilities.action.CommonMethods;
import com.demoappmobile.screenutilities.detection.ElementFinder;
import com.demoappmobile.screenutilities.driver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicScreen {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected ElementFinder elementFinder;
    protected CommonMethods commonMethods;

    public BasicScreen(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = DriverManager.getInstance().getWebDriverWait();
        this.elementFinder = new ElementFinder();
        this.commonMethods = new CommonMethods();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
