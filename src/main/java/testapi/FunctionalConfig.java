package testapi;

import java.io.InputStream;
import java.util.Properties;

public class FunctionalConfig {
    private static final String PROPERTIES_DIR = "/";
    private static final String BROWSER_DIR = "browser/";
    private static final String BROWSER_PROPERTIES_FILE_PATH = PROPERTIES_DIR + BROWSER_DIR + "browser.properties";
    private static final String ENVIRONMENT_DIR = "environment/";
    private static final String ENVIRONMENT_PROPERTIES_FILE_PATH = PROPERTIES_DIR + ENVIRONMENT_DIR + "environment.properties";

    private static final String SELENIUM_BROWSER_PROPERTIES = "TEST_PROPERTIES";
    private static final String SELENIUM_ENV_PROPERTIES = "SELENIUM_ENV_PROPERTIES";

    static {
        props = new Properties();

        loadBrowserPropertiesFromFile();
        loadEnvironmentPropertiesFromFile();
    }

    private static final Properties props;

    private static void loadEnvironmentPropertiesFromFile() {
        loadPropertiesFromFile(ENVIRONMENT_PROPERTIES_FILE_PATH, SELENIUM_ENV_PROPERTIES);
    }

    private static void loadBrowserPropertiesFromFile() {
        loadPropertiesFromFile(BROWSER_PROPERTIES_FILE_PATH, SELENIUM_BROWSER_PROPERTIES);
    }


    private static void loadPropertiesFromFile(String propertiesFilePath, String envProperty) {


        try {
            InputStream propsStream;
            propsStream = FunctionalConfig.class.getResourceAsStream(propertiesFilePath);
            props.load(propsStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Problem loading test properties file. Is " + (propertiesFilePath + " on classpath?"), e);
        }
    }

    public static String getBaseUrl() {
        return getProp("test_url");
    }

    public static String getEnvironmentProperty(String sPropName) {
        String sProp = "";
        try {
            sProp = getProp(sPropName);
        } catch (Exception x) {
        }
        return sProp;
    }


    public static SupportedBrowsers getBrowserName(String browserString) {

        if (browserString.isEmpty()) {
            browserString = getProp("test_browser_name");
        }

        browserString = ((browserString != null) ? browserString.trim() : null);
        if ("firefox".equalsIgnoreCase(browserString)) {
            return SupportedBrowsers.FIREFOX;
        } else if ("chrome".equalsIgnoreCase(browserString)) {
            return SupportedBrowsers.CHROME;
        } else if ("safari".equalsIgnoreCase(browserString)) {
            return SupportedBrowsers.SAFARI;
        } else {
            throw new RuntimeException("Provided browser name is not correct!");
        }
    }

    public static SupportedBrowsers getBrowserName() {
        return getBrowserName("");
    }

    public static String getChromeDriverPath() {
        return getProp("test_chromedriver_path");
    }

    public static String getFirefoxDriverPath() {
        return getProp("test_firefoxdriver_gecko_path");
    }


    private static String getProp(String key) {
        String s = props.getProperty(key);
        return (s != null) ? s.trim() : null;
    }
}
