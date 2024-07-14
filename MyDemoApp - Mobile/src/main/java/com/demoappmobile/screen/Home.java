package com.demoappmobile.screen;

import com.demoappmobile.screen.component.SideNavigationBar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home extends BasicScreen {
    private SideNavigationBar sideNavigationBar;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Products\"]")
    private WebElement productsHeader;

    @AndroidFindBy(accessibility = "open menu")
    private WebElement hamburgerMenu;

    public Home(WebDriver webDriver) {
        super(webDriver);
        this.sideNavigationBar = new SideNavigationBar(webDriver);
    }


    public boolean isProductHeaderVisible() {
        return true;
    }

    public Home hamburgerMenuButtonCLick(){
            commonMethods.clickElement(hamburgerMenu);
        return this;
    }

    public Home goToLoginScreen(){
        sideNavigationBar.loginButtonCLick();
        return this;
    }





}
