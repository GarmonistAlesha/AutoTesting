package org.example.autotesting;

import PageObject.RegistrationPage;
import com.codeborne.selenide.commands.ToString;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import test.TestBase;
import test.TestData;

import static test.TestData.*;
import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomIthemFromArray;


class RegSiteTestPageObjectFakerTestData extends TestBase  { // extends TestBase сделано для убирания BeforeAll
    RegistrationPage registrationPage = new RegistrationPage(); //Сделано для экономии памяти;
    TestData testData = new TestData();
    Faker faker = new Faker();
    String   userName = getFakerName(),
            lastName = getFakerLastName(),
            userEmail= getFakerEmail(),
            gender = getRandomIthemFromArray(Gender),
            phoneNumb = "8" + faker.phoneNumber().subscriberNumber(9), // Номер начинается с 8 и дальше рандомные 9 цифр
            dateBirth = String.format("%02d", faker.number().numberBetween(1, 28)),
            monthBirth = getRandomValue(months),
            yearBirth =String.valueOf(faker.number().numberBetween(1960, 2005)),
            Subjects = getRandomIthemFromArray(subjects),
            hobbies = getRandomIthemFromArray(hobby),
            adressRegistration = getFakerAdressRegistration(),
            userState = getRandomIthemFromArray(state),
            userCity = getRandomCity(userState);
    private String img = "img/1.png";

    @Test //Сам тест

    void RegistrationPrimerPageObjeckt()  {
        registrationPage.OpenPage() // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetFirstName(userName) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetLastName(lastName) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetEmail(userEmail) // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetGender(gender)
        .SetPhoneNumber(phoneNumb)
        .SetBirthDay(dateBirth,monthBirth,yearBirth)
        .SetSubjects(Subjects)
        .SetHobbies(hobbies)
        .UploadPicture(img)
        .SetAdressRegistration(adressRegistration)
        .SetState(userState)
        .SetCity(userCity)
        .ClickSubmit();
        registrationPage.veryfyRegistrationModalWindow().verifyResult("Student Name",userName + lastName )
                .verifyResult("Student Email",userName)
                .verifyResult("Gender","Male")
                .verifyResult("Mobile",phoneNumb);
        
        /*
        По символам в CSS селекторах
        # Cтавится, когда мы вводим данные с показателя id
        . Cтавится, когда мы вводим данные с показателя class
         */
    }

}
