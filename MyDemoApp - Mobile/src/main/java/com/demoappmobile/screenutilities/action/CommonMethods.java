package com.demoappmobile.screenutilities.action;

import com.demoappmobile.Logger.ErrorMessage;
import com.demoappmobile.Logger.InfoMessage;
import com.demoappmobile.screenutilities.action.retry.ActionHandler;
import com.demoappmobile.screenutilities.detection.ElementFinder;
import com.demoappmobile.screenutilities.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Supplier;

public class CommonMethods {
    private static final int DEFAULT_RETRY_COUNT = 3;

    private final ElementFinder elementFinder = new ElementFinder();

    /**
     * Sends text to a specified web element, retrying on failure.
     * This method waits for the element to be visible, clears it, and sends the provided text.
     * It's particularly useful in form filling scenarios in a Page Object Model pattern.
     *
     * @param webElement  The web element to which text will be sent.
     * @param stringValue The text to send to the element.
     *                    <p>
     *                    Usage Example:
     *                    <pre>{@code
     *     @FindBy(xpath = "//input[@id='email']")
     *     private WebElement emailField;
     *
     *     public Login fillEmail(String emailAddress) {
     *         commonMethods.sendKeysToElement(emailField, emailAddress);
     *         return this;
     *                      }
     *             }</pre>
     */
    public void sendKeysToElement(WebElement webElement, String stringValue) {
        ActionHandler.executeVoidWithRetry(() -> {
            prepareForSendingKeys(webElement);
            InfoMessage.sendingKeysToElement(stringValue, webElement);
            webElement.sendKeys(stringValue);
        }, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class, ElementNotInteractableException.class);
    }

    public void sendKeysToElement(By locator, CharSequence charSequence) {
        ActionHandler.executeVoidWithRetry(() -> {
            WebElement webElement = elementFinder.getElement(locator);
            prepareForSendingKeys(webElement);
            InfoMessage.sendingKeysToElement(charSequence, webElement);
            webElement.sendKeys(charSequence);
        }, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class, ElementNotInteractableException.class);
    }

    /**
     * Clicks on a specified web element, retrying on failure.
     * This method waits for the element to be both visible and clickable before attempting the click.
     * Useful for interacting with elements that may not immediately be interactive due to dynamic content loading.
     *
     * @param webElement The web element to click.
     *                   <p>
     *                   Usage Example:
     *                   <pre>{@code
     *                                                                                                                                                                                                                                                                                                                                                       @FindBy(id = "submit-button")
     *                                                                                                                                                                                                                                                                                                                                                       private WebElement submitButton;
     *
     *                                                                                                                                                                                                                                                                                                                                                       public void submitForm() {
     *                                                                                                                                                                                                                                                                                                                                                           commonMethods.clickElement(submitButton);
     *                                                                                                                                                                                                                                                                                                                                                       }
     *                                                                                                                                                                                                                                                                                                                                                       }</pre>
     */
    public void clickElement(WebElement webElement) {
        ActionHandler.executeVoidWithRetry(() -> {
            waitForVisibilityAndClickability(webElement);
            InfoMessage.clickingElement(webElement);
            webElement.click();
        }, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    /**
     * Clicks on a web element identified by a locator, retrying on failure.
     * This method finds the element using the provided locator, then waits for it to be visible and clickable before clicking.
     * Ideal for handling dynamically generated elements where direct reference is not possible.
     *
     * @param locator The locator of the web element to click.
     *                <p>
     *                Usage Example:
     *                <pre>{@code
     *                                                                                                                         public void navigateToLoginPage() {
     *                                                                                                                            commonMethods.clickElement(By.id("login-link"));
     *                                                                                                                         }
     *                                                                                                                         }</pre>
     */
    public void clickElement(By locator) {
        ActionHandler.executeVoidWithRetry(() -> {
            WebElement webElement = elementFinder.findElement(locator);
            waitForVisibilityAndClickability(webElement);
            InfoMessage.clickingElement(webElement);
            webElement.click();
        }, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    /**
     * Retrieves text from a specified web element, retrying on failure.
     * This method waits for the element to be visible before retrieving its text. Useful for assertions or when data needs to be extracted from the page.
     *
     * @param webElement The web element from which to retrieve text.
     * @return The text of the web element.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "confirmation-message")
     * private WebElement confirmationMessage;
     *
     * public String getConfirmationMessage() {
     *     return commonMethods.getTextFromElement(confirmationMessage);
     * }
     * }</pre>
     */
    public String getTextFromElement(WebElement webElement) {
        Supplier<String> getTextAction = () -> {
            waitForVisibility(webElement);
            return webElement.getText();
        };
        return ActionHandler.executeStringWithRetry(getTextAction, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class, ElementClickInterceptedException.class);
    }

    /**
     * Retrieves text from a web element identified by a locator, retrying on failure.
     * This method finds the element using the provided locator and waits for it to be visible before retrieving its text.
     * Especially useful for elements that are not immediately loaded or may change content dynamically.
     *
     * @param locator The locator of the web element from which to retrieve text.
     * @return The text of the web element.
     * <p>
     * Usage Example:
     * <pre>{@code
     * public String getUserGreetingMessage() {
     *     return commonMethods.getTextFromElement(By.id("greeting-text"));
     * }
     * }</pre>
     */
    public String getTextFromElement(By locator) {
        Supplier<String> getTextAction = () -> {
            WebElement webElement = elementFinder.findElement(locator);
            waitForVisibility(webElement);
            return webElement.getText();
        };
        return ActionHandler.executeStringWithRetry(getTextAction, DEFAULT_RETRY_COUNT, StaleElementReferenceException.class);
    }

    /**
     * Waits for a web element to be visible and clickable. This is typically used before performing actions that require user interaction such as clicking.
     *
     * @param webElement The web element to check for visibility and clickability.
     *                   <p>
     *                   Usage Example:
     *                   <pre>{@code
     *                                                                                                              @FindBy(xpath = "//button[@id='submit']")
     *                                                                                                              private WebElement submitButton;
     *
     *                                                                                                              public void clickSubmitButton() {
     *                                                                                                                  waitForVisibilityAndClickability(submitButton);
     *                                                                                                                  submitButton.click();
     *                                                                                                              }
     *                                                                                                              }</pre>
     */
    private void waitForVisibilityAndClickability(WebElement webElement) {
        waitForVisibility(webElement);
        InfoMessage.waitingForElementToBeClickable(webElement);
        DriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Waits for a web element to be visible. This method is used to ensure an element is present and visible before performing read operations like getText.
     *
     * @param webElement The web element to check for visibility.
     *                   <p>
     *                   Usage Example:
     *                   <pre>{@code
     *                                                                                                                                                                                                                                                             @FindBy(xpath = "//div[@id='message']")
     *                                                                                                                                                                                                                                                             private WebElement messageDiv;
     *
     *                                                                                                                                                                                                                                                             public String getMessageText() {
     *                                                                                                                                                                                                                                                                 waitForVisibility(messageDiv);
     *                                                                                                                                                                                                                                                                 return messageDiv.getText();
     *                                                                                                                                                                                                                                                             }
     *                                                                                                                                                                                                                                                             }</pre>
     */
    private void waitForVisibility(WebElement webElement) {
        InfoMessage.waitingForVisibilityOfElement(webElement);
        DriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Prepares a web element by ensuring it is visible and then clears its content if it's an input field.
     * This is mainly used before sending keys to a text input.
     *
     * @param webElement The web element to prepare.
     *                   <p>
     *                   Usage Example:
     *                   <pre>{@code
     *                                                                                                                                                                                                                                                                               @FindBy(xpath = "//input[@id='email']")
     *                                                                                                                                                                                                                                                                               private WebElement emailField;
     *
     *                                                                                                                                                                                                                                                                               public Login fillEmail(String emailAddress) {
     *                                                                                                                                                                                                                                                                                   commonMethods.prepareForSendingKeys(emailField);
     *                                                                                                                                                                                                                                                                                   emailField.sendKeys(emailAddress);
     *                                                                                                                                                                                                                                                                                   return this;
     *                                                                                                                                                                                                                                                                               }
     *                                                                                                                                                                                                                                                                               }</pre>
     */
    private void prepareForSendingKeys(WebElement webElement) {
        waitForVisibility(webElement);
        webElement.clear();
    }

    public boolean isElementEnabled(WebElement webElement) {
        try {
            InfoMessage.waitingForVisibilityOfElement(webElement);
            DriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
            InfoMessage.isElementEnabled(webElement);
            return webElement.isEnabled();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, webElement);
            return false;
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            InfoMessage.waitingForVisibilityOfElement(locator);
            WebElement webElement = DriverManager.getInstance()
                                                 .getWebDriverWait()
                                                 .until(ExpectedConditions.visibilityOfElementLocated(locator));
            InfoMessage.isElementEnabled(webElement);
            return webElement.isEnabled();
        } catch (TimeoutException | NoSuchElementException exception) {
            ErrorMessage.caughtElementException(exception, locator);
            return false;
        }
    }
}
