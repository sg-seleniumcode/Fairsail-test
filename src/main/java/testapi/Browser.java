package testapi;

import org.openqa.selenium.WebDriver;

/**
 * Wrapper class for browser-related functionalities
 */
public class Browser {

    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
        maximize();
    }

    public void quit() {
        driver.quit();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    /**
     * Navigate to application's home page
     */
    public Browser goToHomePage() {
        goToUrl(FunctionalConfig.getBaseUrl());
        return this;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

}
