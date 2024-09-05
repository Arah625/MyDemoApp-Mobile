package com.demoappmobile.screen.webviewcontext.component;

import com.demoappmobile.screen.BasicScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SwagLabsTopNavigationBar extends BasicScreen {

    @FindBy(css = "div.app_logo")
    private WebElement screenHeader;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenuButton;

    @FindBy(css = "#shopping_cart_container")
    private WebElement cartButton;

    public SwagLabsTopNavigationBar(WebDriver driver) {
        super(driver);
    }

    public boolean isScreenHeaderVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(screenHeader);
    }

    public boolean isHamburgerMenuButtonVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(hamburgerMenuButton);
    }

    public SwagLabsTopNavigationBar hamburgerMenuButtonClick() {
        commonMethods.clickElement(hamburgerMenuButton);
        return this;
    }

    public boolean isCartButtonVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(cartButton);
    }

    public SwagLabsTopNavigationBar cartButtonClick() {
        commonMethods.clickElement(cartButton);
        return this;
    }
}
