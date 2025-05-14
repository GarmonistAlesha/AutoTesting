package org.example.autotesting;

import RegistrationPage.RegistrationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import tests.TestBase;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

class RegSiteTestPageObject extends TestBase { // extends TestBase сделано для убирания BeforeAll
    RegistrationPage registrationPage = new RegistrationPage(); //Сделано для экономии памяти
    private String Name = "Jonh";
    private String LastName = "Smith";
    private String Email = "test@gmail.com";
    private String PhoneNumb = "8256485221";
   /*
    @BeforeAll //Операции, производимые до прохождения теста
    - @BeforeEach - операции, проводимые до прохождения теста( каждый раз повторяется перед тестом)
    - @AfterAll и @AfterEach - операции проводимые после теста
    public static void beforeAll() //
    {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }
    */
    //@BeforeEach - операции, проводимые до прохождения теста( каждый раз повторяется перед тестом)
    //@AfterAll и @AfterEach - операции проводимые после теста
    public static void beforeAll() //
    {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }
    @Test //Сам тест

    void RegistrationPrimerPageObjeckt()  {
        registrationPage.OpenPage() // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetFirstName(Name) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetLastName(LastName) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetEmail(Email) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetGender("Male")
        .SetPhoneNumber(PhoneNumb)
        .SetBirthDay("30","July","2008");
        //.veryfyRegistrationModalWindow(Name,LastName,Email,PhoneNumb );


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

     registrationPage.veryfyRegistrationModalWindow().verifyResult("Student Name",Name + " Smith")
     .verifyResult("Student Email",Email)
     .verifyResult("Gender","Male")
     .verifyResult("Mobile",PhoneNumb);

        /*
        По символам в CSS селекторах
        # Cтавится, когда мы вводим данные с показателя id
        . Cтавится, когда мы вводим данные с показателя class
         */
    }

}
