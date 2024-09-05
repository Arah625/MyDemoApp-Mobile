package tests;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import com.demoappmobile.screen.nativecontext.WebViewScreen;
import com.demoappmobile.screen.webviewcontext.SwagLabsHome;
import com.demoappmobile.screen.webviewcontext.SwagLabsLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.CredentialManager;

public class SwagLabsLoginToAccount extends BaseTest {

    private WebViewScreen webViewScreen;
    private SwagLabsLogin swagLabsLogin;
    private SwagLabsHome swagLabsHome;

    @Test
    public void loginToSwagLabsValidUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("validUserSwagLabsUsername"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));
        swagLabsHome = swagLabsLogin.loginButtonClick();
        Assert.assertTrue(swagLabsHome.isScreenHeaderVisible(), VisibilityMessage.headerIsNotVisible("SwagLabs"));

    }

    @Test
    public void loginToSwagLabsLockedOutUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("lockeOutSwagLabsUsername"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));
        swagLabsHome = swagLabsLogin.loginButtonClick();
        catalog.goToLoginScreen();
    }

    @Test
    public void loginToSwagLabsProblemUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("problemUserSwagLabsUsername"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));

//        swagLabsHome = swagLabsLogin.loginButtonClick();
//        catalog.goToLoginScreen();
    }

    @Test
    public void loginToSwagLabsPerformanceGlitchUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("performanceGlitchUserSwagLabsUsername"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));
        swagLabsHome = swagLabsLogin.loginButtonClick();
        catalog.goToLoginScreen();
    }

    @Test
    public void loginToSwagLabsErrorUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("errorUserSwagLabsUsername"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));
        swagLabsHome = swagLabsLogin.loginButtonClick();
        catalog.goToLoginScreen();
    }

    @Test
    public void loginToSwagLabsVisualUser() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        webViewScreen = catalog.goToWebViewScreen();
        webViewScreen.fillUrlField(WEB_VIEW_URL);
        swagLabsLogin = webViewScreen.goToSiteButtonClick();
        Assert.assertTrue(swagLabsLogin.isLoginScreenHeaderVisible());
        swagLabsLogin.fillUsernameField(CredentialManager.credentialReader("visualUserSwagLabPassword"));
        swagLabsLogin.fillPasswordField(CredentialManager.credentialReader("validSwagLabPassword"));
        swagLabsHome = swagLabsLogin.loginButtonClick();
        catalog.goToLoginScreen();
    }


}