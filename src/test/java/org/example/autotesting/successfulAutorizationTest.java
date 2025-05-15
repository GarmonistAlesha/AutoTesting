package org.example.autotesting;

import PageObject.RegistrationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import test.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class successfulAutorizationTest extends TestBase {
    String name = "mvponomarevwork@gmail.com";
    String password = "423197200893Mvp!";
    @Test
    void successfulAutorization(){
    }
    {
        Configuration.holdBrowserOpen = true; //Команда, при которой после прохождения теста браузер не закрывается
        Configuration.browserSize = "1920x1080"; // Изменение размера экрана

        //Открыть форму авторизации
        open("https://school.qa.guru/cms/system/login?required=true");
        //https://school.qa.guru/cms/system/login?required=true
        //Ввести адрес электронной почты
        $("[name=email]").setValue(name);
        //Ввести пароль
        $("[name=password]").setValue(password);
        //Нажать кнопку Войти
        $(".btn-success").click();
        //Проверить успешную авторизацию( в окне проверяем запись "Здравствуйте, Михаил")
        $(".logined-form").shouldHave(text("Здравствуйте, Михаил"));


    }

    //Можно создавать отдельный класс для сокрытия тестовых данных из самого автотеста(со статичными данными)
    public static class TestDataRegistrationTest {
        RegistrationPage registrationPage = new RegistrationPage();

        public static String userName = "Jonh",
                lastName = "Smith",
                userEmail = "test@gmail.com",
                gender,
                phoneNumb = "8256485221",
                dateBirth = "30",
                monthBirth = "July",
                yearBirth = "2008",
                subjects = "English",
                hobbies = "Sports",
                adressRegistration = "sdsqa";

    }
}
