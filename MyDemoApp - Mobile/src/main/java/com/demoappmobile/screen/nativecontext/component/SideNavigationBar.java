package com.demoappmobile.screen.nativecontext.component;

import com.demoappmobile.screen.BasicScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SideNavigationBar extends BasicScreen {

    @AndroidFindBy(accessibility = "menu item catalog")
    private WebElement catalogButton;

    @AndroidFindBy(accessibility = "menu item webview")
    private WebElement webViewButton;

    @AndroidFindBy(accessibility = "menu item qr code scanner")
    private WebElement qrCodeScannerButton;

    @AndroidFindBy(accessibility = "menu item geo location")
    private WebElement geolocationButton;

    @AndroidFindBy(accessibility = "menu item drawing")
    private WebElement drawingButton;

    @AndroidFindBy(accessibility = "menu item about")
    private WebElement aboutButton;

    @AndroidFindBy(accessibility = "menu item reset app")
    private WebElement resetAppStateButton;

    @AndroidFindBy(accessibility = "menu item biometrics")
    private WebElement fingerprintButton;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement logInButton;

    @AndroidFindBy(accessibility = "menu item log out")
    private WebElement logOutButton;

    @AndroidFindBy(accessibility = "menu item api calls")
    private WebElement apiCallsButton;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"menu item sauce bot video\"));")
    private WebElement sauceBotVideoButton;

    public SideNavigationBar(WebDriver driver) {
        super(driver);
    }

    public SideNavigationBar catalogButtonCLick(){
        commonMethods.clickElement(catalogButton);
        return this;
    }

    public SideNavigationBar loginButtonCLick(){
        commonMethods.clickElement(logInButton);
        return this;
    }

    public SideNavigationBar webViewButtonCLick(){
        commonMethods.clickElement(webViewButton);
        return this;
    }

    public SideNavigationBar apiCallsButtonClick(){
        swipeHandler.swipeUntilElementFound(apiCallsButton, "down", Duration.ofSeconds(3));
        commonMethods.clickElement(apiCallsButton);
        return this;
    }

    public SideNavigationBar sauceBotVideoButtonCLick(){
        commonMethods.clickElement(sauceBotVideoButton);
        return this;
    }


}
