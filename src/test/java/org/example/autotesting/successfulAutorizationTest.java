package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class successfulAutorizationTest {
    String name = "mvponomarevwork@gmail.com";
    String password = "423197200893Mvp!";
    @Test
    void successfulAutorization(){
    }
    {
        Configuration.holdBrowserOpen = true; //Команда, при которой после прохождения теста браузер не закрывается
        Configuration.browserSize = "1920x1080"; // Изменение размера экрана
        Configuration.browser = "chrome";
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
}
