package com.demoappmobile.screenutilities.detection;

import com.demoappmobile.screenutilities.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ElementFinder {

    public WebElement findElement(By locator) {
        return DriverManager.getInstance()
                            .getWebDriverWait()
                            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElement(By locator, Duration waitTime) {
        return DriverManager.getInstance()
                            .getWebDriverWait(waitTime)
                            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement getElement(By locator) {
        return DriverManager.getInstance().getDriver().findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return DriverManager.getInstance().getDriver().findElements(locator);
    }

    public List<WebElement> getElements(List<WebElement> elements) {
        try {
            return DriverManager.getInstance()
                                .getWebDriverWait()
                                .until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            return Collections.emptyList();
        }
    }
}
