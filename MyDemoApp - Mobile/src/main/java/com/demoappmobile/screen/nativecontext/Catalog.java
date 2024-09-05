package com.demoappmobile.screen.nativecontext;

import com.demoappmobile.screen.BasicScreen;
import com.demoappmobile.screen.SauceBotVideo;
import com.demoappmobile.screen.nativecontext.component.SideNavigationBar;
import com.demoappmobile.screen.nativecontext.component.TopNavigatorBar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Catalog extends BasicScreen {

    private TopNavigatorBar topNavigatorBar;
    private SideNavigationBar sideNavigationBar;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement productsHeader;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ImageView[1]")
    private WebElement twitterIcon;

    public Catalog(WebDriver webDriver) {
        super(webDriver);
        this.topNavigatorBar = new TopNavigatorBar(webDriver);
        this.sideNavigationBar = new SideNavigationBar(webDriver);
    }

    public boolean isProductHeaderVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(productsHeader);
    }

    public Login goToLoginScreen() {
        topNavigatorBar.hamburgerMenuButtonCLick();
        sideNavigationBar.loginButtonCLick();
        return new Login(driver);
    }

    public WebViewScreen goToWebViewScreen() {
        topNavigatorBar.hamburgerMenuButtonCLick();
        sideNavigationBar.webViewButtonCLick();
        return new WebViewScreen(driver);
    }

    public ApiCalls goToApiCallsScreen() {
        topNavigatorBar.hamburgerMenuButtonCLick();
        sideNavigationBar.apiCallsButtonClick();
        return new ApiCalls(driver);
    }

    public SauceBotVideo goToSauceBotVideoScreen() {
        topNavigatorBar.hamburgerMenuButtonCLick();
        sideNavigationBar.sauceBotVideoButtonCLick();
        return new SauceBotVideo(driver);
    }

    public Catalog twitterButtonClick() {
        swipeHandler.swipeUntilElementFound(twitterIcon, "up", Duration.ofSeconds(20));
        commonMethods.clickElement(twitterIcon);
        return this;
    }
}
