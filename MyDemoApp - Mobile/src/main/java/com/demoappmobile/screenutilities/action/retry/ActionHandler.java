package com.demoappmobile.screenutilities.action.retry;

import com.demoappmobile.Exceptions.ExceptionNotHandledException;
import com.demoappmobile.Exceptions.MaximumRetriesExceededException;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * A utility class that provides a set of methods for executing actions with retry logic.
 * It's designed to handle transient web automation exceptions that can occur due to dynamic content loading,
 * asynchronous page updates, and other common issues encountered in web automation with Selenium.
 * <p>
 * The retry logic helps to enhance the robustness and reliability of web automation scripts by attempting
 * to perform actions multiple times (as specified) in case of specific exceptions before failing the test.
 */
public class ActionHandler {

    private static final int DEFAULT_RETRY_COUNT = 1;

    private ActionHandler() {
        // Prevent instantiation
    }

    /**
     * Executes a supplier action with retry logic, retrying the action up to a specified number of times
     * if it fails due to certain exceptions. This method is particularly useful for handling operations
     * that may intermittently fail in web automation scenarios.
     *
     * @param action             The supplier action to be executed, which returns a value of type T.
     * @param retryCount         The number of retry attempts before giving up.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @param <T>                The type of the result returned by the action.
     * @return The result of the action if it succeeds within the retry attempts.
     * @throws ExceptionNotHandledException    if an exception is thrown that is not specified in exceptionsToHandle.
     * @throws MaximumRetriesExceededException if the number of retry attempts is exceeded without successful execution.
     *                                         <p>
     *                                         Usage Example:
     *                                         <pre>{@code
     *                                                                                                                                                                                                                                                                                         @FindBy(id = "retryable-action-element")
     *                                                                                                                                                                                                                                                                                         WebElement retryableActionElement;
     *
     *                                                                                                                                                                                                                                                                                         public T performActionWithRetry() {
     *                                                                                                                                                                                                                                                                                             return ActionHandler.executeWithRetry(() ->
     *                                                                                                                                                                                                                                                                                             retryableActionElement.getText(), 3, NoSuchElementException.class,
     *                                                                                                                                                                                                                                                                                             StaleElementReferenceException.class);
     *                                                                                                                                                                                                                                                                                         }
     *                                                                                                                                                                                                                                                                                         }</pre>
     */
    @SafeVarargs
    public static <T> T executeWithRetry(Supplier<T> action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        int attempt = 0;
        while (true) {
            try {
                return action.get();
            } catch (Exception e) {
                if (!isExceptionHandled(e, exceptionsToHandle)) {
                    // If the exception is not among the handled exceptions, throw a specific exception for unhandled cases
                    throw new ExceptionNotHandledException("Unhandled exception occurred", e);
                }
                if (++attempt > retryCount) {
                    // If all retries are exhausted, throw a specific exception indicating maximum retries exceeded
                    throw new MaximumRetriesExceededException("Exceeded max retry attempts", e);
                }
            }
        }
    }

    /**
     * Determines if the caught exception is among the exceptions that should be handled
     * (and thus retried) by the retry logic.
     * <p>
     * This method iterates through the provided array of exceptions to handle and checks if the caught exception
     * is an instance of them. It is utilized internally by the public retry methods
     * (e.g., {@code executeWithRetry}) to decide whether an exception thrown during an
     * attempted action should lead to a retry or be propagated immediately.
     *
     * @param e                  The exception that was caught during the execution of the action.
     * @param exceptionsToHandle An array of exceptions that are eligible for retry logic.
     * @return true if the exception is one of the handled types; false otherwise.
     * <p>
     * Internal Usage Example:
     * This method is called within {@link #executeWithRetry} to check if an exception should trigger a retry.
     * For instance, when {@link #executeWithRetry} catches an exception, it calls {@code isExceptionHandled}
     * passing the caught exception and the list of exceptions it should handle. Based on the return value,
     * it decides whether to retry the action or to fail.
     */
    private static boolean isExceptionHandled(Exception e, Class<? extends Exception>[] exceptionsToHandle) {
        return Arrays.stream(exceptionsToHandle).anyMatch(handledException -> handledException.isInstance(e));
    }


    /**
     * Executes a void action with retry logic, retrying the specified action up to the default number of times
     * if it fails due to specified exceptions. This method is particularly useful for actions
     * that do not return a value but may fail transiently, such as clicking on elements
     * that might not be immediately clickable due to asynchronous page updates.
     *
     * @param action             The void action to execute, encapsulated in a {@link Runnable}.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action. Only the exceptions
     *                           specified here will be handled; others will result in an immediate failure.
     *                           <p>
     *                           Usage Example:
     *                           <pre>{@code
     *                                                                               @FindBy(id = "submit-button")
     *                                                                               WebElement submitButton;
     *
     *                                                                               public void clickSubmitButtonWithRetry() {
     *                                                                                   ActionHandler.executeVoidWithRetry(() -> submitButton.click(),
     *                                                                                   ElementClickInterceptedException.class, StaleElementReferenceException.class);
     *                                                                               }
     *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }</pre>
     *                           <p>
     *                           Note: This method uses the default retry count defined in the class.
     *                           To specify a different number of retries, consider using
     *                           the overloaded version of this method.
     */
    @SafeVarargs
    public static void executeVoidWithRetry(Runnable action, Class<? extends Exception>... exceptionsToHandle) {
        executeWithRetry(() -> {
            action.run();
            return null; // For compatibility with Supplier<T>
        }, DEFAULT_RETRY_COUNT, exceptionsToHandle);
    }

    /**
     * Executes a void action with retry logic, retrying the specified action up to a given number of times
     * if it fails due to specified exceptions. This method is useful for actions that do not return a value
     * but may fail transiently, such as clicking on elements that might not be immediately clickable.
     *
     * @param action             The void action to execute.
     * @param retryCount         The number of times to retry the action.
     * @param exceptionsToHandle The exceptions upon which to retry the action.
     *                           <p>
     *                           Usage Example:
     *                           <pre>{@code
     *                                                                               @FindBy(id="submit-button")
     *                                                                               WebElement submitButton;
     *
     *                                                                               // Usage within a test method
     *                                                                               public void clickSubmitButtonWithRetry() {
     *                                                                                   executeVoidWithRetry(() -> submitButton.click(), 3,
     *                                                                                   ElementClickInterceptedException.class, StaleElementReferenceException.class);
     *                                                                               }
     *                                                                               }</pre>
     */
    @SafeVarargs
    public static void executeVoidWithRetry(Runnable action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        executeWithRetry(() -> {
            action.run();
            return null; // For compatibility with Supplier<T>
        }, retryCount, exceptionsToHandle);
    }

    /**
     * Executes a BooleanSupplier action with retry logic, using the default retry count, and retries the action
     * if it fails due to specified exceptions. This method is suitable for boolean-returning actions,
     * such as checks or conditions, which might fail temporarily in web automation scenarios.
     *
     * @param action             The BooleanSupplier action to execute.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The boolean result of the action if it succeeds within the allowed retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "checkbox-id")
     * WebElement checkbox;
     *
     * public boolean isCheckboxSelectedWithRetry() {
     *     return ActionHandler.executeBooleanWithRetry(() -> checkbox.isSelected(), NoSuchElementException.class,
     *     StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static boolean executeBooleanWithRetry(BooleanSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsBoolean, DEFAULT_RETRY_COUNT, exceptionsToHandle);
    }

    /**
     * Executes a BooleanSupplier action with retry logic, allowing for a specified number of retry attempts if the action fails due to certain exceptions.
     * This version provides greater control over the number of retries, accommodating scenarios where a default retry count may not be sufficient.
     *
     * @param action             The BooleanSupplier action to execute.
     * @param retryCount         The number of retry attempts before giving up.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The boolean result of the action if it succeeds within the specified retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "dynamic-element-id")
     * WebElement dynamicElement;
     *
     * public boolean waitForElementVisibilityWithRetry() {
     *     return ActionHandler.executeBooleanWithRetry(() -> dynamicElement.isDisplayed(), 5, NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static boolean executeBooleanWithRetry(BooleanSupplier action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsBoolean, retryCount, exceptionsToHandle);
    }

    /**
     * Executes an IntSupplier action with retry logic, using the default retry count, and retries the action if it fails due to specified exceptions.
     * This method is ideal for actions that return an integer value, such as counts or indexes, which might temporarily fail in web automation scenarios.
     *
     * @param action             The IntSupplier action to execute.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The integer result of the action if it succeeds within the allowed retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(className = "item-class")
     * List<WebElement> items;
     *
     * public int getItemCountWithRetry() {
     *     return ActionHandler.executeIntWithRetry(() -> items.size(), NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static int executeIntWithRetry(IntSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsInt, DEFAULT_RETRY_COUNT, exceptionsToHandle);
    }

    /**
     * Executes an IntSupplier action with retry logic, allowing for a specified number of retry attempts if the action fails due to certain exceptions.
     * This version offers more control over the retry count, accommodating scenarios where a default retry count might not adequately address the issue.
     *
     * @param action             The IntSupplier action to execute.
     * @param retryCount         The number of retry attempts before giving up.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The integer result of the action if it succeeds within the specified retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "dynamic-content-id")
     * WebElement dynamicContent;
     *
     * public int calculateDynamicValueWithRetry() {
     *     return ActionHandler.executeIntWithRetry(() -> Integer.parseInt(dynamicContent.getText()), 3, NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static int executeIntWithRetry(IntSupplier action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsInt, retryCount, exceptionsToHandle);
    }

    /**
     * Executes a DoubleSupplier action with retry logic, using the default retry count, and retries the action if it fails due to specified exceptions.
     * This method is designed for actions that return a double value, which may be prone to failure in dynamic web environments, such as calculating a sum or average where the elements may not be immediately available.
     *
     * @param action             The DoubleSupplier action to execute.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The double result of the action if it succeeds within the allowed retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(css = "div.price")
     * List<WebElement> prices;
     *
     * public double calculateAveragePriceWithRetry() {
     *     return ActionHandler.executeDoubleWithRetry(() -> prices.stream().mapToDouble(element -> Double.parseDouble(element.getText())).average().orElse(Double.NaN), NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static double executeDoubleWithRetry(DoubleSupplier action, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsDouble, DEFAULT_RETRY_COUNT, exceptionsToHandle);
    }

    /**
     * Executes a DoubleSupplier action with retry logic, allowing for a specified number of retry attempts if the action fails due to certain exceptions.
     * This enhanced version allows specifying the retry count, making it more adaptable for situations where the default retry mechanism may not suffice, such as fetching and computing data that is dynamically loaded.
     *
     * @param action             The DoubleSupplier action to execute.
     * @param retryCount         The number of retry attempts before giving up.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The double result of the action if it succeeds within the specified retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "dynamic-value")
     * WebElement dynamicValue;
     *
     * public double extractValueWithRetry() {
     *     return ActionHandler.executeDoubleWithRetry(() -> Double.parseDouble(dynamicValue.getAttribute("data-value")), 5, NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static double executeDoubleWithRetry(DoubleSupplier action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action::getAsDouble, retryCount, exceptionsToHandle);
    }

    /**
     * Executes a Supplier&lt;String&gt; action with retry logic, using the default retry count, and retries the action if it fails due to specified exceptions.
     * This method is suitable for string-returning actions, such as fetching text from a web element, which might not be successful on the first attempt due to dynamic content loading or other web automation challenges.
     *
     * @param action             The Supplier&lt;String&gt; action to execute.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The String result of the action if it succeeds within the allowed retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "dynamic-text")
     * WebElement dynamicText;
     *
     * public String getTextWithRetry() {
     *     return ActionHandler.executeStringWithRetry(() -> dynamicText.getText(), NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static String executeStringWithRetry(Supplier<String> action, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action, DEFAULT_RETRY_COUNT, exceptionsToHandle);
    }

    /**
     * Executes a Supplier&lt;String&gt; action with retry logic, allowing for a specified number of retry attempts if the action fails due to certain exceptions.
     * This version provides the flexibility to specify the retry count, accommodating scenarios where the default retry count may not adequately address transient failures, especially in cases involving dynamic text retrieval or similar operations.
     *
     * @param action             The Supplier&lt;String&gt; action to execute.
     * @param retryCount         The number of retry attempts before giving up.
     * @param exceptionsToHandle The exceptions that, if thrown, will trigger a retry of the action.
     * @return The String result of the action if it succeeds within the specified retry attempts.
     * <p>
     * Usage Example:
     * <pre>{@code
     * @FindBy(id = "conditional-text")
     * WebElement conditionalText;
     *
     * public String getConditionalTextWithRetry() {
     *     return ActionHandler.executeStringWithRetry(() -> {
     *         String text = conditionalText.getText();
     *         if(text.equals("Expected Value")) {
     *             return text;
     *         } else {
     *             throw new StaleElementReferenceException("Text not as expected");
     *         }
     *     }, 3, NoSuchElementException.class, StaleElementReferenceException.class);
     * }
     * }</pre>
     */
    @SafeVarargs
    public static String executeStringWithRetry(Supplier<String> action, int retryCount, Class<? extends Exception>... exceptionsToHandle) {
        return executeWithRetry(action, retryCount, exceptionsToHandle);
    }
}