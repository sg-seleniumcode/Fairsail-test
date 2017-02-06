package forms;

import components.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testapi.PageInteractionHelper;
import testapi.DateFormatHelper;

public class NewObjectiveForm extends PageInteractionHelper {

    private By NEW_OBJECTIVE_MODAL = By.xpath("//h4[contains(text(),'New Objective:')]");
    private By OBJECTIVE_NAME = By.name("name");
    private By DESCRIPTION = By.name("description");
    private By MEASURE = By.xpath("//label[contains(text(),'Measure')]/..//textarea");
    private By STRATEGIC_GOAL = By.xpath("//label[contains(text(),'Strategic Goal')]/..//select");
    private By CONTRIBUTES_TO = By.xpath("//label[contains(text(),'Contributes To')]/..//select"); // Not populating any values
    private By START_DATE_INPUT = By.xpath("//label[contains(text(),'Start Date')]/..//input");
    private By END_DATE_INPUT = By.xpath("//label[contains(text(),'End Date')]/..//input");
    private By NEXT_REVIEW_DATE_INPUT = By.xpath("//label[contains(text(),'Next Review Date')]/..//input");
    private By WEIGHT = By.xpath("//label[contains(text(),'Weight')]/..//input");
    private By PRIORITY = By.xpath("//label[contains(text(),'Priority')]/..//select");
    private By REQUIRED_FOR_BONUS_YES = By.xpath("//label[contains(text(),'Required For Bonus')]/../..//button[contains(text(),'Yes')][2]");
    private By REQUIRED_FOR_BONUS_NO = By.xpath("//label[contains(text(),'Required For Bonus')]/../..//button[contains(text(),'No')][2]");
    private By IS_PRIVATE_YES = By.xpath("//label[contains(text(),'Is Private')]/../..//button[contains(text(),'Yes')][2]");
    private By IS_PRIVATE_NO = By.xpath("//label[contains(text(),'Is Private')]/../..//button[contains(text(),'No')][2]");
    private By SAVE_BUTTON = By.xpath("//button[contains(text(),'Save')][2]");
    private DateFormatHelper dateFormatHelper;

    public NewObjectiveForm(WebDriver driver) {
        super(driver);
        this.dateFormatHelper = new DateFormatHelper(driver);
    }

    public boolean isNewObjectiveModalDisplayed() {
        return isElementDisplayed(NEW_OBJECTIVE_MODAL);
    }

    public NewObjectiveForm enterObjectiveName(String objective) {
        shortSleep();
        typeText(OBJECTIVE_NAME, objective);
        return this;
    }

    public NewObjectiveForm enterDescription(String description) {
        typeText(DESCRIPTION, description);
        return this;
    }

    public NewObjectiveForm enterMeasure(String measure) {
        typeText(MEASURE, measure);
        return this;
    }

    public NewObjectiveForm selectStrategicGoal(NewObjectiveForm.StrategicGoal strategicGoal) {
        switch (strategicGoal) {
            case FINANCIAL:
                selectDropdownItem(STRATEGIC_GOAL, "Financial");
                break;
            case PEOPLE_AND_PROCESSES:
                selectDropdownItem(STRATEGIC_GOAL, "People and Processes");
                break;
            case SALES_AND_MARKETING:
                selectDropdownItem(STRATEGIC_GOAL, "Sales and Marketing");
                break;
            case RESEARCH_AND_DEVELOPMENT:
                selectDropdownItem(STRATEGIC_GOAL, "Research and Development");
                break;
            case OPERATIONS:
                selectDropdownItem(STRATEGIC_GOAL, "Operations");
                break;
        }
        return this;
    }

    public NewObjectiveForm selectContributesTo(NewObjectiveForm.ContributesTo contributesTo) {
        /**
         * This dropdown is not getting populated. Hence commenting code
         */

        return this;
    }

    public NewObjectiveForm selectPriority(NewObjectiveForm.Priority priority) {
        switch (priority) {
            case HIGH:
                selectDropdownItem(PRIORITY, "High");
                break;
            case MEDIUM:
                selectDropdownItem(PRIORITY, "Medium");
                break;
            case LOW:
                selectDropdownItem(PRIORITY, "Low");
                break;
        }
        return this;
    }

    public NewObjectiveForm selectStartDate(String startDate) {
        clickOnElement(START_DATE_INPUT);
        typeText(START_DATE_INPUT, "");
        new DatePicker(driver).setDate(dateFormatHelper.getDay(startDate), dateFormatHelper.getMonth(startDate), dateFormatHelper.getYear(startDate), "startdate");
        return this;
    }

    public NewObjectiveForm selectEndDate(String endDate) {

        clickOnElement(END_DATE_INPUT);
        typeText(END_DATE_INPUT, "");
        new DatePicker(driver).setDate(dateFormatHelper.getDay(endDate), dateFormatHelper.getMonth(endDate), dateFormatHelper.getYear(endDate), "endDate");
        return this;
    }

    public NewObjectiveForm selectNextReviewDate(String nextReviewDate) {

        clickOnElement(NEXT_REVIEW_DATE_INPUT);
        typeText(NEXT_REVIEW_DATE_INPUT, "");
        new DatePicker(driver).setDate(dateFormatHelper.getDay(nextReviewDate), dateFormatHelper.getMonth(nextReviewDate), dateFormatHelper.getYear(nextReviewDate), "nextreviewdate");
        return this;
    }

    public NewObjectiveForm enterWeight(String weight) {
        typeText(WEIGHT, weight);
        return this;
    }

    public NewObjectiveForm selectRequiredForBonus(NewObjectiveForm.Options option) {
        switch (option) {
            case YES:
                clickOnElement(REQUIRED_FOR_BONUS_YES);
                break;
            case NO:
                clickOnElement(REQUIRED_FOR_BONUS_NO);
                break;
        }
        return this;
    }

    public NewObjectiveForm selectIsPrivate(NewObjectiveForm.Options option) {
        switch (option) {
            case YES:
                clickOnElement(IS_PRIVATE_YES);
                break;
            case NO:
                clickOnElement(IS_PRIVATE_NO);
                break;
        }
        return this;
    }

    public NewObjectiveForm clickSave() {
        clickOnElement(SAVE_BUTTON);
        return this;
    }

    public boolean isSaveEnabled() {
        return isElementEnabled(SAVE_BUTTON);
    }

    public enum StrategicGoal {
        FINANCIAL,
        PEOPLE_AND_PROCESSES,
        SALES_AND_MARKETING,
        RESEARCH_AND_DEVELOPMENT,
        OPERATIONS
    }

    public enum ContributesTo {
        //drop-down not populated
    }

    public enum Options {
        YES,
        NO
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

}
