package de.komoot.webdriver.Utils;

import org.openqa.selenium.WebElement;

public class CommonUtils {

    public static void click(WebElement element)  {
        highlightElement(element);
        element.click();

    }

    public static void sendkeys(WebElement element, String text)  {
        highlightElement(element);
        element.sendKeys(text);

    }

    public static void highlightElement(WebElement element) {
        //((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);

    }


}
