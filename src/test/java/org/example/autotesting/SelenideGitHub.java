package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class SelenideGitHub
{
    @BeforeAll
    static void beforeAll()
    {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize ="1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void SelenideGitHubTest()
    {
    open("/selenide/selenide");
    $("#wiki-tab").click();
    $("#wiki-pages-filter").setValue("SoftAssertions");
    $(".filterable-active").shouldHave(text("SoftAssertions"));
    //Поиск элемента текста в и нажатие на него (в случае когда текст является ссылкой на страницу)
    $(byText("SoftAssertions")).click();
    $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
    sleep(5000);
    }
    @BeforeEach
    void beforeEach()
    {
        System.out.println("Тест выполнен успешно");
    }

}
