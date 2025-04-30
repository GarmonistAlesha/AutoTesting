package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class googleTest {
    @Test
    void successfulSearchTest(){
        Configuration.holdBrowserOpen = true;
        //Открыть google
        open("https://www.google.com/");
        //Ввести Selenide в поиск
        $("[name=q]").setValue("selenide").pressEnter();
        //Проверить, что Selenide появился в результатах поиска
        $("[id=search]").shouldHave(text("https://selenide.org"));
    }
}
