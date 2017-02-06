package testapi;

import components.DatePicker;
import forms.NewObjectiveForm;
import org.openqa.selenium.WebDriver;
import pages.FairsailWXpage;
import pages.SalesforceHomePage;
import pages.SalesforceLoginPage;

import java.util.Collections;
import java.util.List;

public class BaseTest {

    private DriverFactory driverFactory;
    private WebDriver driver;
    protected Browser browser;

    //page definitions
    protected SalesforceLoginPage salesforceLoginPage;
    protected SalesforceHomePage salesforceHomePage;
    protected FairsailWXpage fairsailWXpage;

    //form definitions
    protected NewObjectiveForm newObjectiveForm;

    //component definitions
    protected DatePicker datePicker;


    public void setupBaseTest(String testClassName, List<String> unsupportedBrowserCapabilities) {
        // start browser session - one session per test class
        this.driverFactory = new DriverFactory(testClassName, unsupportedBrowserCapabilities);
        this.driver = driverFactory.getDriver();
        this.browser = new Browser(driver);

        // page objects
        this.salesforceLoginPage = new SalesforceLoginPage(driver);
        this.salesforceHomePage = new SalesforceHomePage(driver);
        this.fairsailWXpage = new FairsailWXpage(driver);

        // form objects
        this.newObjectiveForm = new NewObjectiveForm(driver);

        //component objects
        this.datePicker = new DatePicker(driver);

        // Navigate to SalesforceHomePage
        browser.goToHomePage();
    }

    public void setupBaseTest(String testClassName) {
        setupBaseTest(testClassName, Collections.<String>emptyList());
    }

    protected void endSession() {
        try {
            browser.quit();
        } catch (NullPointerException e) {
        }
    }
}
