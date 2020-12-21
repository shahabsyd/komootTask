package de.komoot.webdriver;

import com.codeborne.selenide.Configuration;
import de.komoot.webdriver.configuration.ConfigurationEnvironment;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;



@Getter
public class TestCase extends ConfigurationEnvironment {
    protected static final Logger LOGGER = LogManager.getLogger(TestCase.class);
    public static final String ENABLE_VNC = "enableVNC";

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        setInitialConfiguration();
        if (!useLocalDriver) {
            Configuration.remote = remoteHost;
            Configuration.driverManagerEnabled = false;
            if (enableVNC) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", enableVNC);
                Configuration.browserCapabilities = capabilities;
            }
        }
        Configuration.fastSetValue = true;
        Configuration.timeout = 6000;
        Configuration.browser = browser;
        Configuration.baseUrl = homePageUrl;
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
    }

    @AfterMethod
    public void teardown() {
        closeWebDriver();
    }


    }

