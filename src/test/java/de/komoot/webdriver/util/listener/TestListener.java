package de.komoot.webdriver.util.listener;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class TestListener implements ITestListener, ISuiteListener {
    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);
    private static final String ALLURE_ENV_PATH = "target/allure-results/environment.properties";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onStart(ISuite suite) {
        LOGGER.info("Start Suit ==>" + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("Finish Suit ==> " + suite.getName());
        attachEnvironmentConfiguration();
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Start Test ==> " + context.getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Finish Test ==> " + context.getName());
        var passedTestNames = context.getPassedTests().getAllResults().stream().map(ITestResult::getName).collect(Collectors.toSet());
        var failedTestNames = context.getFailedTests().getAllResults().stream().map(ITestResult::getName).collect(Collectors.toSet());
        var skippedTestNames = context.getSkippedTests().getAllResults().stream().map(ITestResult::getName).collect(Collectors.toSet());
        LOGGER.info("Passes tests: " + passedTestNames);
        LOGGER.info("Failed tests: " + failedTestNames);
        LOGGER.info("Skipped tests: " + skippedTestNames);
    }

    @Override
    public void onTestStart(ITestResult result) {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .enableLogs(io.qameta.allure.selenide.LogType.BROWSER, Level.ALL));
        ThreadContext.push(result.getName());
        LOGGER.info("New Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test Successfully Finished: " + result.getName());
        ThreadContext.clearStack();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test Failed: " + result.getName());
        LOGGER.error(result.getThrowable());
        if (hasWebDriverStarted()) {
            attachScreenshotToReport();
            attachBrowserLogs();
        }
        ThreadContext.clearStack();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test Skipped: " + result.getName());
        ThreadContext.clearStack();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "SCREENSHOT", type = "image/png")
    private byte[] attachScreenshotToReport() {
        File screenshot = takeScreenShotAsFile();
        return transformFileToBytes(screenshot);
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "BROWSER-LOG", type = "text/html")
    private String attachBrowserLogs() {
        Logs logs = getWebDriver().manage().logs();
        LogEntries logEntries = logs.get(LogType.BROWSER);
        return gson.toJson(logEntries.toJson());
    }

    private byte[] transformFileToBytes(File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException ignored) {
        }
        return null;
    }

    private void attachEnvironmentConfiguration() {
        Properties prop = new Properties();
        prop.setProperty("base_url", Configuration.baseUrl);
        prop.setProperty("browser_name", Configuration.browser);
        //prop.setProperty("browser_version", Configuration.browserCapabilities.getVersion());
        //prop.setProperty("platform", Configuration.browserCapabilities.getPlatform().name());
        try (FileWriter writer = new FileWriter(ALLURE_ENV_PATH)) {
            prop.store(writer, "store properties in allure directory");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Failed to write properties for allure report");
        }
    }

}
