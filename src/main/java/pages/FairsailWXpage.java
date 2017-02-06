package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testapi.PageInteractionHelper;

public class FairsailWXpage extends PageInteractionHelper {

    public FairsailWXpage(WebDriver driver) {
        super(driver);
    }

    private By PAGE_ID = By.xpath("//div[@id='portaltitle']/span[contains(text(),'Fairsail WX')]");
    private By PERFORMANCE_LINK = By.xpath("//*[@id='nav-left-normal']//div[@id='navigation-you']//b[contains(text(),'Performance')]/..");
    private By OBJECTIVES_LINK = By.xpath("//*[@id='nav-left-normal']//div[@id='navigation-you']//a[text()='Objectives']");
    private By NEW_BUTTON = By.xpath("//button[contains(text(),'New')]");
    private By DRAFT_TAB = By.xpath("//div[@id='tabs']//li[contains(text(),'Draft')]");
    private String DRAFT_OBJECTIVE = "//div[@id='viewOne']//p/b[contains(text(),'%s')]";


    public boolean isPageDisplayed() {
        return isElementDisplayed(PAGE_ID);
    }


    public FairsailWXpage clickPerformanceLink() {
        shortSleep();
        clickOnElement(PERFORMANCE_LINK);
        return this;
    }

    public FairsailWXpage clickObjectivesLink() {
        shortSleep();
        clickOnElement(OBJECTIVES_LINK);
        return this;
    }

    public FairsailWXpage clickNew() {
        clickOnElement(NEW_BUTTON);
        return this;
    }

    public FairsailWXpage clickDraftTab(){
        clickOnElement(DRAFT_TAB);
        return this;
    }

    public boolean isDraftObjectivePresent(String objectiveName){
        shortSleep();
        return isElementDisplayed(By.xpath(String.format(DRAFT_OBJECTIVE,objectiveName)));
    }

}
