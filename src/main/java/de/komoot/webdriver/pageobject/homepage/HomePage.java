package de.komoot.webdriver.pageobject.homepage;

import com.codeborne.selenide.SelenideElement;
import de.komoot.webdriver.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.*;
import static de.komoot.webdriver.configuration.ConfigurationEnvironment.getHomePageUrl;

public class HomePage extends BasePage {
    protected static final Logger LOGGER = LogManager.getLogger(HomePage.class);


    private final SelenideElement signUpOrLogin = $x("//*[@id='pageMountNode']/div/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/button/span");
    private final SelenideElement cookie = $x("//div[contains(text(),'Accept All')]");
    private final SelenideElement settings = $x("//div[@class='c-topmenu__scrollable']/ul[2]/li[3]");
    private final SelenideElement menu = $x("//div[@class='tw-flex tw-items-center']/div[3]/a");
    private final SelenideElement hiking = $x("//ul[@class='o-nav tw-mb-0 tw-inline-block c-sport-selector ']/li[1]");
    private final SelenideElement whereTo = $x("//input[@placeholder='Where to?']");
    private final SelenideElement searchButton = $x("//span[contains(text(),'Search')]");


    /**
     * Open the Home page
     *
     * @return HomePage
     */
    @Step("Open Home Page")
    public static void openHomePage() {
        open(getHomePageUrl());
        sleep(4000);
        //clearBrowserCookies();
        LOGGER.info("Opening Homepage");

    }


    public void dismissCookie() {
        cookie.click();
    }

    public void signUpLink() {
        //Objects.requireNonNull(signUpOrLogin);
        signUpOrLogin.click();
    }

    public void topManu() {
        menu.click();
        sleep(2000);
    }

    public SettingsPage settingsLink() {
        settings.click();
        sleep(2000);
        return new SettingsPage();
    }

    public void selectJourneyMedium() {
        hiking.click();
    }

    public void destinationAddress(String address) {
        whereTo.sendKeys(address);
    }

    public void enterSearchButton() {
        searchButton.click();
    }


}
