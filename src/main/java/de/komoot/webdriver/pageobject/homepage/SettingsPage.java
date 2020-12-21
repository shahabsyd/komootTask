package de.komoot.webdriver.pageobject.homepage;

import com.codeborne.selenide.SelenideElement;
import de.komoot.webdriver.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SettingsPage extends BasePage {

    protected static final Logger LOGGER = LogManager.getLogger(SettingsPage.class);

    private final SelenideElement selectHiking = $x("/ul[@class='o-nav tw-mb-0 tw-inline-block c-sport-selector ']/li[1]");
    private final SelenideElement enterLocation = $x("//input[@placeholder='Where to?']");
    private final SelenideElement searchButton = $x("//span[contains(text(),'Search')]");
    private final SelenideElement chooseDifficultyLevel = $x("//div[contains(text(),'Expert')]");
    private final SelenideElement editButton = $x("//div[@class='css-1bgnsec'] [contains(text(),'Edit')]//preceding::button[@class='css-15jg18l']");
    private final SelenideElement changeEmail = $(By.id("email"));
    private final SelenideElement confirmEmail = $(By.id("emailConfirm"));
    private final SelenideElement saveButton = $x("//button[@type='submit']");


    HomePage hp = new HomePage();
    LoginPage lp = new LoginPage();

    public void goToSettingsPage() {
        hp.topManu();
        hp.settingsLink();
    }

    public WebElement changeEmailInput() {
        return changeEmail;
    }

    public WebElement confirmEmailInput() {
        return changeEmail;
    }


    public void clickEditButton() {
        editButton.click();
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clickCancel(){

    }

}
