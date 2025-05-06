package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
class RegSiteTestPrimer {
    private String Site = "https://demoqa.com/automation-practice-form";
    private String Name = "Jonh";
    private String Email = "test@gmail.com";
    private String LastName = "Smith";
    private String PhoneNumb = "8256485221";

    @BeforeAll //Операции, производимые до прохождения теста
    //@BeforeEach - операции, проводимые до прохождения теста( каждый раз повторяется перед тестом)
    //@AfterAll и @AfterEach - операции проводимые после теста
    public static void beforeAll()
    {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }
    @Test //Сам тест

    void RegistrationPrimer() throws InterruptedException {
        // Selenide.executeJavaScript("('fixexedban').remove()"); //Выполнение команды JQuery в селениде ( отключение банерной рекламы)
        open(Site);
        //$(By.id("#firstName")).setValue(Name);
        $("#firstName").setValue(Name);
        //$(By.id("#lastName")).setValue(LastName);
        $("#lastName").setValue(LastName);
        //$(By.id("userEmail")).setValue(Email);
        $("#userEmail").setValue(Email);

        //Пример выбора чек-бокса (точка выбора)
        // $(By.className("custom-control-inline")).click(); //Не очень хороший, но рабочий вариант(мой вариант)
        //$("#gender-radio-1").parent().click(); // хороший вариант
        $("#genterWrapper").$(byText("Male")).click(); // Лучший вариант
        $("#userNumber").setValue(PhoneNumb);

        //Пример выбора даты рождения через календарь
        $(By.id("dateOfBirthInput")).click();//открытие календаря
        $(".react-datepicker__month-select").selectOption(7); //выбор в календаре месяца
        $(".react-datepicker__year-select").selectOptionByValue("1993"); //выбор в календаре года
        //$(By.className("react-datepicker__day--020")).click(); //выбор в календаре дня( не очень хорошо)
        $(".react-datepicker__day--003:not(.react-datepicker__day--outside-month)").click(); //выбор в календаре дня
        // (Хорошо,т.к исключает выбор повторяющийся даты)


        $("#subjectsInput").setValue("English").pressEnter(); //введение в строчку слово English и нажатие клавиши enter
        //Пример выбора чек-бокса( галочка в квадрате)
        $("#hobbiesWrapper").$(byText("Sports")).click(); // Лучший вариант
        //$("#hobbies-checkbox-1").sendKeys(" "); //поиск по CSS и нажатие на чек-бокс
        //Пример загрузки файла с директории
        //$("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png")); //Загрузка файла с папки resources в директории test java
        $("#uploadPicture").uploadFromClasspath(("img/1.png")); //Загрузка файла с папки resources в
        // директории test java(сокращенно) (selenid метод)

        $("#currentAddress").setValue("sdsqa");
        //Нажатие на выпадающий список и выбор значения
        //$(By.id("state")).click(); //нажатие на выпадающий список
        $("#state").click(); //нажатие на выпадающий список
        $("#stateCity-wrapper").$(byText("NCR")).click(); //Выбор в выпадающем списке
        //$(By.id("city")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $(By.id("submit")).click();

        //Проверка правильности заполнения таблички
        $(".modal-dialog").should(appear); //Проверка открытия модального окна
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Проверка соответствия выводимого текста
        $(".table-responsive").shouldHave(text(Name), text(LastName), text(Email),text(PhoneNumb));
        //Провекрка соответствия выводимых данных

    }

}
