package org.example.autotesting;

import PageObject.RegistrationPage;
import org.junit.jupiter.api.Test;
import test.TestBase;

import static com.codeborne.selenide.Selenide.$;

class RegSiteTestPageObject extends TestBase { // extends TestBase сделано для убирания BeforeAll
    RegistrationPage registrationPage = new RegistrationPage(); //Сделано для экономии памяти
    private String Name = "Jonh";
    private String LastName = "Smith";
    private String Email = "test@gmail.com";
    private String PhoneNumb = "8256485221";
    private String img = "img/1.png"; //тут указан путь до расположения загружаемого файла

    @Test //Сам тест

    void RegistrationPrimerPageObjeckt()  {
        registrationPage.OpenPage() // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetFirstName(Name) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetLastName(LastName) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetEmail(Email) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetGender("Male")
        .SetPhoneNumber(PhoneNumb)
        .SetBirthDay("30","July","2008")
        .SetSubjects("English")
        .SetHobbies("Sports")
        .UploadPicture(img)
        .SetAdressRegistration("sdsqa")
        .SetState("NCR")
        .SetCity("Delhi")
        .ClickSubmit();
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
