package com.demoappmobile.screen.webviewcontext;

import com.demoappmobile.screen.BasicScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsLogin extends BasicScreen {

    @FindBy(xpath = "//div[@class='login_logo' and contains(text(),'Swag Labs')]")
    private WebElement screenHeader;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public SwagLabsLogin(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginScreenHeaderVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(screenHeader);
    }

    public SwagLabsLogin fillUsernameField(String username) {
        commonMethods.sendKeysToElement(usernameField, username);
        return this;
    }

    public SwagLabsLogin fillPasswordField(String password) {
        commonMethods.sendKeysToElement(passwordField, password);
        return this;
    }

    public boolean isLoginButtonVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(loginButton);
    }

    public SwagLabsHome loginButtonClick() {
        commonMethods.clickElement(loginButton);
        return new SwagLabsHome(driver);
    }
}
