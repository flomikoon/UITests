package Config;

import org.openqa.selenium.WebDriver;

abstract public class BasePage {

    protected static final String URL = "file:///C:/Users/flomi/Downloads/qa-test.html";
    protected static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }
}
