package com.demoappmobile.screen.webviewcontext.component;

import com.demoappmobile.screen.BasicScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsSideNavigationBar extends BasicScreen {

    @FindBy(css = "div.bm-menu-wrap")
    private WebElement sideNavBarContainer;

    @FindBy(css = "inventory_sidebar_link")
    private WebElement allItemsButton;

    @FindBy(css = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(css = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(css = "reset_sidebar_link")
    private WebElement resetAppStateButton;

    @FindBy(css = "button#react-burger-cross-btn")
    private WebElement closeSideNavBarButton;

    public SwagLabsSideNavigationBar(WebDriver driver) {
        super(driver);
    }

}
