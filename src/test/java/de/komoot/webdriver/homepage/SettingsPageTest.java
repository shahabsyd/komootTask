package de.komoot.webdriver.homepage;

import com.codeborne.selenide.SelenideElement;
import de.komoot.webdriver.TestCase;
import de.komoot.webdriver.pageobject.homepage.HomePage;
import de.komoot.webdriver.pageobject.homepage.LoginPage;
import de.komoot.webdriver.pageobject.homepage.SettingsPage;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsPageTest extends TestCase {

    protected static final Logger LOGGER = LogManager.getLogger(SettingsPageTest.class);

    HomePage hp = new HomePage();
    LoginPage lp = new LoginPage();
    SettingsPage sp = new SettingsPage();

    @BeforeMethod
    public void firstTest() {
        HomePage.openHomePage();
        hp.signUpLink();
        lp.enterEmailAddress("shahab2815@gmail.com");
        lp.clickContinueWithEmail();

    }


    @Test
    @Description("Edit existing Email Address")
    public void changeEmailAddress() {
        SelenideElement textOnSettingsPage = (SelenideElement) By.xpath("//h1[contains(text() ,'Settings')]");
        lp.enterPassword("abc123");
        lp.signUpOrLogin();
        hp.topManu();
        hp.settingsLink();
        LOGGER.info("validate user is on settings page");
        textOnSettingsPage.isDisplayed();
        sp.clickEditButton();
        sp.changeEmailInput().click();
        sp.changeEmailInput().clear();
        sp.changeEmailInput().sendKeys("shahab28151@gmail.com");
        sp.confirmEmailInput().sendKeys("shahab28151@gmail.com");
        sp.clickCancel();//cancelling it in order to keep the same email.
    }


}
