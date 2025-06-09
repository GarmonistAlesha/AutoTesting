package org.example.autotesting;

import PageObject.RegistrationPage;
import org.junit.jupiter.api.Test;
import test.TestBase;

import static utils.RandomUtils.getRandonString;

class RegSiteTestPageObjectRandomTestData extends TestBase  { // extends TestBase сделано для убирания BeforeAll
    RegistrationPage registrationPage = new RegistrationPage(); //Сделано для экономии памяти
       String   userName = getRandonString(10),
                lastName = getRandonString(10),
                userEmail,
                gender,
                phoneNumb,
                dateBirth,
                monthBirth,
                yearBirth,
                subjects,
                hobbies,
                adressRegistration;
    private String img = "img/1.png";

    @Test //Сам тест

    void RegistrationPrimerPageObjeckt()  {
        registrationPage.OpenPage() // Открытие страницы сайта с помощью метода RegistrationPage, который вынесен отдельно
        .SetFirstName(userName)
        .SetLastName(lastName)
        .SetEmail(userEmail)
        .SetGender(gender)
        .SetPhoneNumber(phoneNumb)
        .SetBirthDay(dateBirth,monthBirth,yearBirth)
        .SetSubjects(subjects)
        .SetHobbies(hobbies)
        .UploadPicture(img)
        .SetAdressRegistration(adressRegistration)
        .SetState("NCR")
        .SetCity("Delhi")
        .ClickSubmit();
        registrationPage.veryfyRegistrationModalWindow().verifyResult("Student Name",userName + " Smith")
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
