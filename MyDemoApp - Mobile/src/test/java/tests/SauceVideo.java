package tests;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import com.demoappmobile.screen.SauceBotVideo;
import com.demoappmobile.screen.nativecontext.ApiCalls;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceVideo extends BaseTest {

    SauceBotVideo sauceBotVideo;
    ApiCalls apiCalls;

    @Test
    public void timestamp() {
        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
        sauceBotVideo = catalog.goToSauceBotVideoScreen();
        sauceBotVideo.fastForwardButtonClick();
        sauceBotVideo.stopButtonClick();
        String s = sauceBotVideo.getCurrentTimeStamp();
        sauceBotVideo.fastForwardButtonClick();
        String t = sauceBotVideo.getCurrentTimeStamp();

    }

//    @Test
//    public void apiCalls() {
//        Assert.assertTrue(catalog.isProductHeaderVisible(), VisibilityMessage.headerIsNotVisible("Products"));
//        catalog.twitterButtonClick();
//        apiCalls = catalog.goToApiCallsScreen();
//        Assert.assertTrue(apiCalls.isScreenHeaderVisible(), "Header not visible");
//        apiCalls.findMobile();
//
//    }
}
