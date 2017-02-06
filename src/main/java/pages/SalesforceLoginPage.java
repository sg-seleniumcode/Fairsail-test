package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testapi.PageInteractionHelper;

public class SalesforceLoginPage extends PageInteractionHelper {

    private By USERNAME = By.id("username");
    private By PASSWORD = By.id("password");
    private By LOGIN_BUTTON = By.id("Login");

    public SalesforceLoginPage(WebDriver driver) {
        super(driver);
    }

    public SalesforceLoginPage enterUserName(String userName) {
        typeText(USERNAME, userName);
        return this;
    }

    public SalesforceLoginPage enterPassword(String userName) {
        typeText(PASSWORD, userName);
        return this;
    }

    public SalesforceLoginPage clickLogin() {
        clickOnElement(LOGIN_BUTTON);
        return this;
    }
}
