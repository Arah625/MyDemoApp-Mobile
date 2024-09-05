package com.demoappmobile.screen.nativecontext.component;

import com.demoappmobile.screen.BasicScreen;
import com.demoappmobile.screen.nativecontext.Cart;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopNavigatorBar extends BasicScreen {
    @AndroidFindBy(accessibility = "open menu")
    private WebElement hamburgerMenuButton;

    @AndroidFindBy(accessibility = "longpress reset app")
    private WebElement navigationBarTitle;

    @AndroidFindBy(accessibility = "sort button")
    private WebElement sortButton;

    @AndroidFindBy(accessibility = "cart badge")
    private WebElement cartButton;

    public TopNavigatorBar(WebDriver driver) {
        super(driver);
    }

    public boolean isAppTitleVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(navigationBarTitle);
    }

    public SideNavigationBar hamburgerMenuButtonCLick() {
        contextSwitcher.switchToNative();
        commonMethods.clickElement(hamburgerMenuButton);
        return new SideNavigationBar(driver);
    }

    public TopNavigatorBar sortButtonClick() {
        commonMethods.clickElement(sortButton);
        return this;
    }

    public Cart cartButtonClick() {
        commonMethods.clickElement(cartButton);
        return new Cart(driver);
    }
}
