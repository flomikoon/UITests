package Page;

import Config.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FormPage extends BasePage {

    @FindBy(xpath = "//input[@id='dataEmail']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='dataName']")
    private WebElement name;

    @FindBy(xpath = "//select//option[text()='Женский']")
    private WebElement femaleGender;

    @FindBy(xpath = "//select//option[text()='Мужской']")
    private WebElement manGender;

    @FindBy(xpath = "//input[@id='dataCheck11']")
    private WebElement checkBox11;

    @FindBy(xpath = "//input[@id='dataCheck12']")
    private WebElement checkBox12;

    @FindBy(xpath = "//input[@id='dataSelect21']")
    private WebElement radioButton21;

    @FindBy(xpath = "//input[@id='dataSelect22']")
    private WebElement radioButton22;

    @FindBy(xpath = "//input[@id='dataSelect23']")
    private WebElement radioButton23;

    @FindBy(xpath = "//button[@id='dataSend']")
    private WebElement submit;

    @FindBy(id = "emailFormatError")
    private WebElement emailError;

    @FindBy(id = "blankNameError")
    private WebElement nameError;
    public FormPage(){
        PageFactory.initElements(driver , this);
    }

    public boolean checkSuccessLogin(){
        return name.isDisplayed();
    }

    public void sendEmail(String emailValue){
        email.clear();
        email.sendKeys(emailValue);
    }

    public void sendName(String nameValue){
        name.clear();
        name.sendKeys(nameValue);
    }

    public void changeGender(String genderValue){
        if (genderValue.equals("Мужской")) {
            manGender.click();
        } else {
            femaleGender.click();
        }
    }
    public void clickCheckBox11(){
        checkBox11.click();
    }

    public void clickCheckBox12(){
        checkBox12.click();
    }

    public void clickRadioButton21(){
        radioButton21.click();
    }

    public void clickRadioButton22(){
        radioButton22.click();
    }

    public void clickRadioButton23(){
        radioButton23.click();
    }

    public void clickSubmit(){
        submit.click();
    }
    public boolean checkAddData(String addDateResult){
        return  (new WebDriverWait(driver , Duration.ofSeconds(4))
                .until(ExpectedConditions
                        .textToBe(By.xpath("//div[@class='uk-margin uk-modal-content'] ") , addDateResult)));
    }

    public void clickOk(){
        new WebDriverWait(driver , Duration.ofSeconds(4)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[text()='Ok']"))).click();
    }

    public List<String> getTableData(){
        List<WebElement> webElement = driver.findElements(By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td"));

        List<String> webElements = new ArrayList<>();
        for (int i = 5 ; i>0 ; i--){
            webElements.add(webElement.get(webElement.size() - i ).getText());
        }
        return webElements;
    }

    public void fillOutForm(ArrayList<String> list){
        this.sendEmail(list.get(0));
        this.sendName(list.get(1));
        this.changeGender(list.get(2));
        switch (list.get(3)) {
            case "1.1, 1.2" -> {
                this.clickCheckBox11();
                this.clickCheckBox12();
            }
            case "1.1" -> this.clickCheckBox11();
            case "1.2" -> this.clickCheckBox12();
            default -> {}
        }

        switch (list.get(4)) {
            case "2.1" -> this.clickRadioButton21();
            case "2.2" -> this.clickRadioButton22();
            case "2.3" -> this.clickRadioButton23();
            default -> {
            }
        }
    }

    public String getEmailError(){
        return emailError.getText();
    }

    public String getNameError(){
        return nameError.getText();
    }

}
