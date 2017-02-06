package testapi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class PageInteractionHelper {

    private static final int IMPLICIT_WAIT_TIMEOUT = Integer.parseInt(FunctionalConfig.getEnvironmentProperty("IMPLICIT_WAIT_TIMEOUT"));

    public final WebDriver driver;

    protected PageInteractionHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits
     */
    public void shortSleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void veryShortSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private WebDriverWait driverWait() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        return new WebDriverWait(driver, IMPLICIT_WAIT_TIMEOUT, 5);
    }

    public WebElement waitUntilPresent(By locator) {
        return driverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(final By locator) {
        return driverWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement getElement(By locator) {
        return waitUntilPresent(locator);
    }


    public void clickOnElement(By locator) {
        waitUntilClickable(locator).click();
    }


    public void typeText(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (TimeoutException te) {
            return false;
        } catch (StaleElementReferenceException sere) {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException nsee) {
            return getElement(locator).isDisplayed();
        }
    }

    protected boolean isElementEnabled(By locator) {
        try {
            return getElement(locator).isEnabled();
        } catch (TimeoutException te) {
            return false;
        } catch (StaleElementReferenceException sere) {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException nsee) {
            return getElement(locator).isDisplayed();
        }
    }

    public void selectDropdownItem(By locator, String item) {
        getDropDownList(locator).selectByVisibleText(item);
    }

    public Select getDropDownList(By locator) {
        return new Select(getElement(locator));
    }

}