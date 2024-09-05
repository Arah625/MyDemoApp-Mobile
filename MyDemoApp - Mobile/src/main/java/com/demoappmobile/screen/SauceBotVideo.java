package com.demoappmobile.screen;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SauceBotVideo extends BasicScreen {

    @AndroidFindBy(accessibility = "video icon backward")
    private WebElement rewindButton;

    @AndroidFindBy(accessibility = "video icon stop")
    private WebElement stopButton;

    @AndroidFindBy(accessibility = "video icon start")
    private WebElement startButton;

    @AndroidFindBy(accessibility = "video icon forward")
    private WebElement fastForwardButton;

    @AndroidFindBy(accessibility = "video icon volume-up")
    private WebElement volumeUpButton;

    @FindBy(css = ".ytp-time-current")
    private WebElement currentTimeStamp;

    @FindBy(css = "iframe.video")
    private WebElement iframe;

    public SauceBotVideo(WebDriver driver) {
        super(driver);
    }


    public SauceBotVideo rewindButtonClick() {
        commonMethods.clickElement(rewindButton);
        return this;
    }

    public SauceBotVideo stopButtonClick() {
        commonMethods.clickElement(stopButton);
        return this;
    }

    public SauceBotVideo startButtonClick() {
        commonMethods.clickElement(startButton);
        return this;
    }

    public SauceBotVideo fastForwardButtonClick() {
        commonMethods.clickElement(fastForwardButton);
        return this;
    }

    public SauceBotVideo volumeUpButtonClick() {
        commonMethods.clickElement(volumeUpButton);
        return this;
    }

    public String getCurrentTimeStamp() {
        contextSwitcher.switchToWebView();
        frame.switchToiFrame(iframe);
        String currentTimestamp = commonMethods.getTextFromElement(currentTimeStamp);
        System.out.println("Timestamp: " + currentTimestamp);
        frame.switchToDefaultContent();
        contextSwitcher.switchToNative();
        return currentTimestamp;
    }



}
