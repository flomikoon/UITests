package Config;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

abstract public class BasePage {


    protected  String URL = getFile()+"/src/test/java/Config/qa-test.html";

    protected static WebDriver driver;

    protected BasePage() throws IOException {
    }

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public String getFile() throws IOException {
        return new java.io.File(".").getCanonicalPath();
    }

}
