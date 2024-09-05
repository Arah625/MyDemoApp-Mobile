package tests;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import com.demoappmobile.screen.nativecontext.Login;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.CredentialManager;

public class LoginToAccount extends BaseTest {

    private Login login;
    @Test
    public void successfulLoginToAccount(){
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        login = catalog.goToLoginScreen();
        Assert.assertTrue(login.isLoginScreenHeaderVisible());
        login.fillUsernameField(CredentialManager.credentialReader("validUsername"));
        login.fillPasswordField(CredentialManager.credentialReader("validPassword"));
        catalog = login.loginButtonClick();
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        login = catalog.goToLoginScreen();
        Assert.assertFalse(login.isLoginButtonVisible());
    }

    @Test
    public void lockedOutLoginToAccount(){
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        login = catalog.goToLoginScreen();
        Assert.assertTrue(login.isLoginScreenHeaderVisible());
        login.fillUsernameField(CredentialManager.credentialReader("lockeOutUsername"));
        login.fillPasswordField(CredentialManager.credentialReader("validPassword"));
        catalog = login.loginButtonClick();
        Assert.assertTrue(login.isLockedOutErrorMessageVisible());
    }

    @Test
    public void unknownUserLoginToAccount_invalidUsername(){
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        login = catalog.goToLoginScreen();
        Assert.assertTrue(login.isLoginScreenHeaderVisible());
        login.fillUsernameField(CredentialManager.credentialReader("invalidUsername"));
        login.fillPasswordField(CredentialManager.credentialReader("validPassword"));
        catalog = login.loginButtonClick();
        Assert.assertTrue(login.isUnknownUserErrorMessageVisible());
    }

    @Test
    public void unknownUserLoginToAccount_invalidPassword(){
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        login = catalog.goToLoginScreen();
        Assert.assertTrue(login.isLoginScreenHeaderVisible());
        login.fillUsernameField(CredentialManager.credentialReader("validUsername"));
        login.fillPasswordField(CredentialManager.credentialReader("invalidPassword"));
        catalog = login.loginButtonClick();
        Assert.assertTrue(login.isUnknownUserErrorMessageVisible());
    }


}