package sitetest;

import Config.BaseTest;
import Page.FormPage;
import Page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;

public class FormPageTest extends BaseTest {

    private ArrayList<String> createInputList(String emailForm , String nameForm , String genderForm
            , boolean checkBox1 , boolean checkBox2 , int radioButton){

        ArrayList<String> inputList = new ArrayList<>();
        inputList.add(emailForm);
        inputList.add(nameForm);
        inputList.add(genderForm);
        if(checkBox1&&checkBox2){
            inputList.add("1.1, 1.2");
        } else if (checkBox1){
            inputList.add("1.1");
        } else if (checkBox2){
            inputList.add("1.2");
        } else {
            inputList.add("Нет");
        }

        switch (radioButton) {
            case 1 -> inputList.add("2.1");
            case 2 -> inputList.add("2.2");
            case 3 -> inputList.add("2.3");
            default -> inputList.add("");
        }
        return inputList;
    }

    private FormPage login(){
        LoginPage loginPage = new LoginPage();
        FormPage formPage = loginPage.login(email , password);

        Assertions.assertTrue(formPage.checkSuccessLogin());
        return formPage;
    }

    @ParameterizedTest
    @Tag("Successful completion form")
    @CsvFileSource(resources = "resources/SuccessfulCompletionFormTest.csv")
    public void successfulCompletionForm(String emailForm , String nameForm , String genderForm
            , boolean checkBox1 , boolean checkBox2 , int radioButton){

        FormPage formPage = login();

        ArrayList<String> inputList = createInputList(emailForm , nameForm , genderForm , checkBox1 , checkBox2 , radioButton);

        formPage.fillOutForm(inputList);
        formPage.clickSubmit();

        Assertions.assertTrue(formPage.checkAddData(addDateResult));

        formPage.clickOk();
        List<String> outputList = formPage.getTableData();
        Assertions.assertArrayEquals(inputList.toArray() , outputList.toArray());
    }

    @ParameterizedTest
    @Tag("Submit form too many times")
    @CsvFileSource(resources = "resources/SubmitFormTooManyTimesTest.csv")
    public void addAllotDataForm(String emailForm , String nameForm , String genderForm
            , boolean checkBox1 , boolean checkBox2 , int radioButton , int time){

        FormPage formPage = login();
        ArrayList<String> inputList = createInputList(emailForm , nameForm , genderForm , checkBox1 , checkBox2 , radioButton);

        formPage.fillOutForm(inputList);

        for(int i = 0 ; i < time; i++){
            formPage.clickSubmit();
            formPage.clickOk();
            List<String> outputList = formPage.getTableData();
            Assertions.assertArrayEquals(inputList.toArray() , outputList.toArray());
        }
    }

    @ParameterizedTest
    @Tag("Submit Form With Uncorrected Email Field Test.csv")
    @CsvFileSource(resources = "resources/SubmitFormWithUncorrectedEmailFieldTest.csv")
    public void submitFormWithUncorrectedEmailFiled(String emailForm , String nameForm , String genderForm
            , boolean checkBox1 , boolean checkBox2 , int radioButton){

        FormPage formPage = login();
        ArrayList<String> inputList = createInputList(emailForm , nameForm , genderForm , checkBox1 , checkBox2 , radioButton);

        formPage.fillOutForm(inputList);

        formPage.clickSubmit();
        Assertions.assertEquals( emailError, formPage.getEmailError());
    }

    @Tag("Submit Form With Empty Email Field Test")
    @Test
    public void submitFormWithEmptyEmailFiled(){

        FormPage formPage = login();
        ArrayList<String> inputList = createInputList("", name, femaleGender , true , true , 1);

        formPage.fillOutForm(inputList);

        formPage.clickSubmit();
        Assertions.assertEquals( emailError, formPage.getEmailError());
    }

    @Tag("Submit Form With Empty Name Field Test")
    @Test
    public void submitFormWithEmptyNameFiled(){

        FormPage formPage = login();
        ArrayList<String> inputList = createInputList(email , "", femaleGender , true , true , 1);

        formPage.fillOutForm(inputList);

        formPage.clickSubmit();
        Assertions.assertEquals( nameError, formPage.getNameError());
    }

    @ParameterizedTest
    @Tag("Submit Form With Uncorrected Name Field Test.csv")
    @CsvFileSource(resources = "resources/SubmitFormWithUncorrectedNameFieldTest.csv")
    public void submitFormWithUncorrectedNameFiled(String emailForm , String nameForm , String genderForm
            , boolean checkBox1 , boolean checkBox2 , int radioButton){

        FormPage formPage = login();
        ArrayList<String> inputList = createInputList(emailForm , nameForm , genderForm , checkBox1 , checkBox2 , radioButton);

        formPage.fillOutForm(inputList);

        formPage.clickSubmit();
        Assertions.assertEquals( nameError, formPage.getNameError());
    }
}
