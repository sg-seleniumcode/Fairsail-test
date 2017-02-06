package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testapi.PageInteractionHelper;

public class SalesforceHomePage extends PageInteractionHelper {

    private By PAGE_ID = By.id("phHeaderLogoImage");
    private By WORKFORCE_EXPERIENCE_LINK = By.linkText("Workforce eXperience");

    public SalesforceHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        shortSleep();
        return isElementDisplayed(PAGE_ID);
    }

    public void clickWorkforceExperienceLink() {
        clickOnElement(WORKFORCE_EXPERIENCE_LINK);
    }

}
