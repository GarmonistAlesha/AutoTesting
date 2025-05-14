package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CloseCookiePopop {
    @BeforeAll
    static void FirstStep()
    {
        Configuration.baseUrl = "https://www.otpbank.ru/";
        Configuration.browserSize ="1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void CloseCookiePopup()
    {
        open("https://www.otpbank.ru/"); //Открывает сайт
        $(withText(" обработку Ваших персональных данных в файлах cookie")).shouldBe(visible); //Проверяет наличие текста
        $(byTagAndText("strong","Закрыть")).click(); // Закрывает кнопку нажать на контестной рекламе
        $(withText(" обработку Ваших персональных данных в файлах cookie")).shouldBe(hidden); // Проверяет отсутствие текста
    }
}