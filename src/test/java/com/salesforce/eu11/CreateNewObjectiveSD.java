package com.salesforce.eu11;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import forms.NewObjectiveForm;
import org.springframework.test.context.ContextConfiguration;
import testapi.BaseTest;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

@ContextConfiguration("classpath:cucumber.xml")
public class CreateNewObjectiveSD extends BaseTest {

    @Given("^I am on Salesforce login page$")
    public void iAmOnSalesforceLoginPage() throws Throwable {
        super.setupBaseTest(this.getClass().getName());
    }

    @When("^I enter valid \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterValidAndPassword(String userName, String password) throws Throwable {
        salesforceLoginPage
                .enterUserName(userName)
                .enterPassword(password)
                .clickLogin();
    }

    @Then("^I see Salesforce homepage$")
    public void iSeeSalesforceHomepage() throws Throwable {
        assertTrue("Homepage is not displayed ", salesforceHomePage.isPageDisplayed());
    }

    @When("^I navigate to Workforce eXperience tab$")
    public void iNavigateToTab() throws Throwable {
        salesforceHomePage.clickWorkforceExperienceLink();
        assertTrue("Work Experience is not displayed", fairsailWXpage.isPageDisplayed());
    }

    @And("^I create new personal objective")
    public void iSelectUnderSection() throws Throwable {
        fairsailWXpage
                .clickPerformanceLink()
                .clickObjectivesLink()
                .clickNew();
    }

    @Then("^I see New Objective view$")
    public void iSeeNewObjectiveView() throws Throwable {
        assertTrue("New objective modal is not displayed", newObjectiveForm.isNewObjectiveModalDisplayed());
    }

    @When("^I enter the Description details: Objective Name \"([^\"]*)\" and Description \"([^\"]*)\" and  Measure \"([^\"]*)\"$")
    public void iEnterTheDescriptionDetailsObjectiveNameAndDescriptionAndMeasure(String objectiveName, String description, String measure) throws Throwable {

        newObjectiveForm
                .enterObjectiveName(objectiveName)
                .enterDescription(description)
                .enterMeasure(measure);
    }

    @And("^I select Contribution to business details: Strategic Goal \"([^\"]*)\" and Contributes To \"([^\"]*)\"$")
    public void iSelectContributionToBusinessDetailsStrategicGoalAndContributesTo(String strategicGoal, String contributesTo) throws Throwable {
        newObjectiveForm
                .selectStrategicGoal(NewObjectiveForm.StrategicGoal.valueOf(strategicGoal));
//                  .selectContributesTo(NewObjectiveForm.ContributesTo.valueOf(contributesTo)); //No values populated for Contributes To
    }

    @And("^I select Key dates details: Start Date \"([^\"]*)\" End Date \"([^\"]*)\" Next Review Date \"([^\"]*)\"$")
    public void iSelectKeyDatesDetailsStartDateEndDateNextReviewDate(String startDate, String endDate, String nextReviewDate) throws Throwable {
        newObjectiveForm
                .selectStartDate(startDate)
                .selectEndDate(endDate)
                .selectNextReviewDate(nextReviewDate);
    }

    @And("^I enter Attributes: Weight \"([^\"]*)\" and Priority \"([^\"]*)\" and select Required For Bonus \"([^\"]*)\"$")
    public void iEnterAttributesWeightAndPriorityAndSelectRequiredForBonus(String weight, String priority, String requiredForBonus) throws Throwable {
        newObjectiveForm
                .enterWeight(weight)
                .selectPriority(NewObjectiveForm.Priority.valueOf(priority))
                .selectRequiredForBonus(NewObjectiveForm.Options.valueOf(requiredForBonus));
    }

    @And("^I select Visibility: Is Private \"([^\"]*)\"$")
    public void iSelectVisibilityIsPrivate(String isPrivate) throws Throwable {
        newObjectiveForm
                .selectIsPrivate(NewObjectiveForm.Options.valueOf(isPrivate));
    }

    @And("^I click the Save button$")
    public void iClickTheButton() throws Throwable {
        newObjectiveForm.clickSave();
    }

    @Then("^Check record created for valid details \"([^\"]*)\"$")
    public void checkRecordCreatedForValidDetails(String objectiveName) throws Throwable {
        fairsailWXpage
                .clickObjectivesLink()
                .clickDraftTab();
        assertTrue("New draft objective is not displayed", fairsailWXpage.isDraftObjectivePresent(objectiveName));
    }

    @Then("^Save button is not enabled$")
    public void saveButtonIsNotEnabled() throws Throwable {
        assertFalse("Save button is enabled", newObjectiveForm.isSaveEnabled());
    }

    @After
    public void quit() {
        super.endSession();
    }
}
