package Config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

abstract public class BaseTest {
    protected static WebDriver driver;

    protected static final String email = "test@protei.ru";
    protected static final String password = "test";
    protected static final String name = "Forex";
    protected static final String femaleGender = "Женский";
    protected static final String manGender = "Мужской";
    protected static final String addDateResult = "Данные добавлены.";

    protected static final String emailError = "Неверный формат E-Mail";

    protected static final String passwordError = "Неверный E-Mail или пароль";

    protected static final String nameError = "Поле имя не может быть пустым";

    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        BasePage.setDriver(driver);
    }


    @AfterAll
    public static void close(){
       // driver.close();
        //driver.quit();
    }
}
