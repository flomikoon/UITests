package Page;

import Config.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='loginEmail']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='loginPassword']")
    private WebElement password;

    @FindBy(id = "emailFormatError")
    private WebElement emailError;

    @FindBy(id = "invalidEmailPassword")
    private WebElement emailPasswordError;

    @FindBy(xpath = "//*[@id=\"authButton\"]")
    private WebElement submitButton;

    public LoginPage() throws IOException {
        super();
        driver.get(URL);
        PageFactory.initElements(driver , this);
    }

    public FormPage login(String emailValue , String passwordValue) throws IOException {
        this.enterEmail(emailValue);
        this.enterPassword(passwordValue);
        this.pressSubmit();
        return new FormPage();
    }

    public void pressSubmit(){
        submitButton.click();
    }

    public String getEmailError(){
        return emailError.getText();
    }

    public String getPasswordError(){
        return emailPasswordError.getText();
    }

    public void enterPassword(String passwordValue){
        password.sendKeys(passwordValue);
    }

    public void enterEmail(String emailValue){
        email.sendKeys(emailValue);
    }
}
