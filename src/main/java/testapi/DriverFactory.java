package testapi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.List;

public class DriverFactory extends FunctionalConfig {
    private WebDriver driver;

    public DriverFactory(String testClassName, List<String> unsupportedBrowserCapabilities) {
        if (this.driver == null) {
            driver = getDriver(testClassName);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver getDriver(String testClassName) {
        if (getBrowserName() != null) {
            return getLocalBrowser();
        } else {
            throw new RuntimeException("Browser is null");
        }
    }

    private WebDriver getLocalBrowser() {
        switch (getBrowserName()) {
            case FIREFOX: {
                FirefoxProfile fp = new FirefoxProfile();
                fp.setPreference("getBrowser.download.manager.showWhenStarting", false);
                fp.setPreference("getBrowser.helperApps.neverAsk.saveToDisk", "application/pdf");
                System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
                driver = new FirefoxDriver(fp);
                break;
            }
            case CHROME: {
                System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("chrome.switches", Arrays.asList("--disable-web-security"));
                driver = new ChromeDriver(capabilities);
                break;
            }
            case SAFARI: {
                driver = new SafariDriver();
                break;
            }
            default: {
                throw new RuntimeException("Proper local browser not provided! ");
            }
        }
        return driver;
    }

}
