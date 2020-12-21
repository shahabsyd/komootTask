package de.komoot.webdriver.configuration;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static de.komoot.webdriver.configuration.ConfigurationConstant.*;

@Getter
public class ConfigurationEnvironment {
    protected static final Logger LOGGER = LogManager.getLogger(ConfigurationEnvironment.class);

    protected Properties properties;
    protected boolean useLocalDriver;
    public static String homePageUrl;
    protected String browser;
    protected String remoteHost;
    protected boolean enableVNC;


    public synchronized void setInitialConfiguration() {
        LOGGER.info("Set initial Configuration");
        loadProperties();
        addEnvironmentProperties();
    }

    public static String getHomePageUrl() {
        return homePageUrl;
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            InputStream file = this.getClass().getClassLoader().getResourceAsStream(TESTING_PROPERTIES);
            properties.load(file);
        } catch (Exception e) {
            LOGGER.error("Exception to load File testing.properties:", e);
            throw new RuntimeException(e);
        }
    }

    private void addEnvironmentProperties() {
        useLocalDriver = Boolean.parseBoolean(System.getProperty(USE_LOCAL_DRIVER, properties.getProperty(USE_LOCAL_DRIVER)));
        homePageUrl = System.getProperty(HOMEPAGE, properties.getProperty(HOMEPAGE));
        browser = System.getProperty(BROWSER, properties.getProperty(BROWSER));
        remoteHost = System.getProperty(REMOTE_HOST, properties.getProperty(REMOTE_HOST));
        enableVNC = Boolean.parseBoolean(System.getProperty(ENABLE_VNC, properties.getProperty(ENABLE_VNC)));

    }

}
