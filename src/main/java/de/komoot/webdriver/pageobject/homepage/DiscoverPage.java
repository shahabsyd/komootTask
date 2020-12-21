package de.komoot.webdriver.pageobject.homepage;

import com.codeborne.selenide.SelenideElement;
import de.komoot.webdriver.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public class DiscoverPage extends BasePage {

    protected static final Logger LOGGER = LogManager.getLogger(DiscoverPage.class);

    private final SelenideElement chooseDifficultyLevel = $x("//input[@placeholder='Where to?']");
    private final SelenideElement enterDestination = $x("//div[contains(text(),'Expert')]");
    private final SelenideElement selectHiking = $x("//ul[@class='o-nav tw-mb-0 tw-inline-block c-sport-selector ']/li[1]");
    private final SelenideElement searchButton = $x("//button[@type='button']//span[contains(text(),'Search')]");
    private final SelenideElement textDisplayed = $x("//h2[contains(text() ,'Find the Perfect Tour')]");

    HomePage hp = new HomePage();
    LoginPage lp = new LoginPage();


    public void findAPerfectTour() {
        selectHiking.click();
        enterDestination.sendKeys("Alexanderplatz");
        searchButton.click();
    }

    public WebElement textOnDiscoverPage(){
        return textDisplayed;
    }

}
