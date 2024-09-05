package com.demoappmobile.screen.nativecontext;

import com.demoappmobile.screen.BasicScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ApiCalls extends BasicScreen {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='API calls']")
    private WebElement screenHeader;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Google Pixel 9'])[1]")
//    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Honor 10 Lite'])[1]")
//    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='iPad 10.2'])[1]")
    private WebElement iPhoneXModel;

    public ApiCalls(WebDriver driver) {
        super(driver);
    }

    public boolean isScreenHeaderVisible() {
        return visibilityHandler.elementVisibility().isElementVisible(screenHeader);
    }

    public void findMobile() {
        swipeHandler.swipeUntilElementFound(iPhoneXModel, "up", Duration.ofSeconds(50));

//        swipeHandler.swipeUntilElementFound(iPhoneXModel, "up", Duration.ofSeconds(50), Duration.ofMillis(500), Duration.ofMillis(1));

        System.out.println("Widzę element: " + commonMethods.getTextFromElement(iPhoneXModel));
    }


}
