package org.example.autotesting;

import org.junit.jupiter.api.Test;
import test.TestBase;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CloseCookiePopop extends TestBase {
    @Test
    void CloseCookiePopup()
    {
        open("https://www.otpbank.ru/"); //Открывает сайт
        $(withText(" обработку Ваших персональных данных в файлах cookie")).shouldBe(visible); //Проверяет наличие текста
        $(byTagAndText("strong","Закрыть")).click(); // Закрывает кнопку нажать на контестной рекламе
        $(withText(" обработку Ваших персональных данных в файлах cookie")).shouldBe(hidden); // Проверяет отсутствие текста
    }
}