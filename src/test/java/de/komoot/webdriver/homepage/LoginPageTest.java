package de.komoot.webdriver.homepage;

import de.komoot.webdriver.TestCase;
import de.komoot.webdriver.pageobject.homepage.DiscoverPage;
import de.komoot.webdriver.pageobject.homepage.HomePage;
import de.komoot.webdriver.pageobject.homepage.LoginPage;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestCase {

    protected static final Logger LOGGER = LogManager.getLogger(LoginPageTest.class);
    HomePage hp = new HomePage();
    LoginPage lp = new LoginPage();
    DiscoverPage ds = new DiscoverPage();

    @BeforeMethod
    public void init() {
        HomePage.openHomePage();
        hp.signUpLink();
        LOGGER.info("Navigated to sign up Or Login Page");

    }

    @Test
    @Description("Login with registered User")
    public void testLoginWithValidCredentials() {

        String expectedTest = "Find the Perfect Tour";
        LOGGER.info("Test login functionality");
        lp.enterEmailAddress("shahab2815@gmail.com");
        lp.clickContinueWithEmail();
        lp.enterPassword("abc123");
        lp.clickLogin();
        String actualText = ds.textOnDiscoverPage().getText();
        expectedTest.equalsIgnoreCase(actualText);


    }

    @Test
    @Description("Sign for the first time")
    public void testSignUp() {
        LOGGER.info("Test Sign up functionality");
        lp.clickContinueWithEmail();

    }

}
