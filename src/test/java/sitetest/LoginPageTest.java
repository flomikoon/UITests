package sitetest;

import Config.BaseTest;
import Page.FormPage;
import Page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;

public class LoginPageTest extends BaseTest {

    @ParameterizedTest
    @Tag("Successful Login Test")
    @CsvFileSource(resources = "resources/SuccessfulLoginTest.csv")
    public void successfulLogin(String email , String password) throws IOException {
        LoginPage loginPage = new LoginPage();
        FormPage formPage = loginPage.login(email , password);
        Assertions.assertTrue(formPage.checkSuccessLogin());
    }

    @Test
    @Tag("Empty Fields Login Test")
    public void emptyFieldsLogin() throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.pressSubmit();
        Assertions.assertEquals( emailError ,loginPage.getEmailError());
    }

    @ParameterizedTest
    @Tag("Empty Email Field Login Test")
    @CsvFileSource(resources = "resources/EmptyEmailFieldLoginTest.csv")
    public void emptyEmailFieldLogin(String password) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.enterPassword(password);
        loginPage.pressSubmit();
        Assertions.assertEquals( emailError ,loginPage.getEmailError());
    }

    @ParameterizedTest
    @Tag("Empty Password Field Login Test")
    @CsvFileSource(resources = "resources/EmptyPasswordFieldLoginTest.csv")
    public void emptyPasswordFieldLogin(String email) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(email);
        loginPage.pressSubmit();
        Assertions.assertEquals( passwordError ,loginPage.getPasswordError());
    }


    @ParameterizedTest
    @Tag("Unsuccessful Login Test")
    @CsvFileSource(resources = "resources/UnsuccessfulTest.csv")
    public void unsuccessfulLogin(String email , String password) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.login(email , password);
        Assertions.assertEquals( passwordError ,loginPage.getPasswordError());
    }

}
