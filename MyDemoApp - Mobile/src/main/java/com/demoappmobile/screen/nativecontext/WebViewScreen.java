package com.demoappmobile.screen.nativecontext;

import com.demoappmobile.screen.BasicScreen;
import com.demoappmobile.screen.webviewcontext.SwagLabsLogin;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebViewScreen extends BasicScreen {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']//android.widget.TextView[@text='Webview']")
    private WebElement screenHeader;

    @AndroidFindBy(accessibility = "URL input field")
    private WebElement urlField;

    @AndroidFindBy(accessibility = "Go To Site button")
    private WebElement goToSiteButton;

    public WebViewScreen(WebDriver driver) {
        super(driver);
    }

    public WebViewScreen fillUrlField(String url) {
        commonMethods.sendKeysToElement(urlField, url);
        return this;
    }

    public SwagLabsLogin goToSiteButtonClick() {
        commonMethods.clickElement(goToSiteButton);
        contextSwitcher.switchToWebView();
        return new SwagLabsLogin(driver);
    }
}
