package com.demoappmobile.screen.nativecontext;

import com.demoappmobile.screen.BasicScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login extends BasicScreen {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='container header']//android.widget.TextView[@text='Login']")
    private WebElement screenHeader;

    @AndroidFindBy(accessibility = "Username input field")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "Password input field")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "Login button")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sorry, this user has been locked out.']")
    private WebElement lockedOutErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Provided credentials do not match any user in this service.']")
    private WebElement unknownUserErrorMessage;

    public Login(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginScreenHeaderVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(screenHeader);
    }

    public Login fillUsernameField(String username) {
        commonMethods.sendKeysToElement(usernameField, username);
        return this;
    }

    public Login fillPasswordField(String password) {
        commonMethods.sendKeysToElement(passwordField, password);
        return this;
    }

    public boolean isLoginButtonVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(loginButton);
    }

    public Catalog loginButtonClick() {
        commonMethods.clickElement(loginButton);
        return new Catalog(driver);
    }

    public boolean isLockedOutErrorMessageVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(lockedOutErrorMessage);
    }

    public boolean isUnknownUserErrorMessageVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(unknownUserErrorMessage);
    }
}
