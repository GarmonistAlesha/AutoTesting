package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import test.TestBase;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class VskIpoteka {

     @BeforeAll
    static void VskBefore()
     {
        Configuration.baseUrl = "https://www.vsk.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
     }
    // @Disabled // аннотация к тому, что-бы тест не проходился (например минорный баг,
    // который нашел тест, а исправлять его будут потом, или надо будет исправлять тест)
    //@ParameterizedTest // аннотация параметризированных тестов(ведут себя как 2 разных теста, могут выполнятся параллельно)
    // нужен DataProvider(ArgumentSofts) @ValueSourst используем
    // для выставления параметра( только 1 параметр)
    // если надо 2 и более, то используем CsvSource или CsvFileSource(CSV файл должен содержаться в папке resources)
    //@MethodSource(имя метода) - аннотация, которая пишет статический метод, который может вернуть любые Java объекты используемые в программе
    // static Stream<Arguments> имя класса и return Stream.of() Arguments.of(объект, который хочу вернуть) Arguments.of()
    // Argument.of указывается столько раз, сколько хотим запустить тесты
    @Test
    @DisplayName("Проверка заполнения полей 'Ипотека' ВСК") //Аннотация названия теста
    @Tag("Bloker") //в эту аннотацию можно вставлять Приоритет и Серьезность
    //@Bloker // самостоятельно созданная аннотация, заменяет Tag
    @Tags({ //аннотация, принимающая несколько тегов (удобно для сортировки тестов,
            // что-бы потом запускать только те, которые нужны
            @Tag("Bloker"),
            @Tag("WEB")
    })
    void IpotekaVsk()
    {
    open("/klientam");
    $(By.id("main_cards_mortgage_title")).$(byText("Ипотека")).click();
    //Выбор банка на странице ипотека
    $(".tui-autofill").click();

    $(".t-scroll").$(byText("Сбербанк")).click();
    $(".calculate-button").click();
    //проверка открытия модального окна "Расчет стоймости"
    $(".ng-trigger-tuiSlideInTop").should(appear);
    $(".modal-window").shouldHave(text("Расчёт стоимости"));
    //Выбор параметров заполнения формы
    $(".vsk-custom-input-date").click();
    //$(".vsk-custom-input-date").setValue("20.08.2018");

    $(By.id("year-btn")).click();
    $(".ng-trigger-tuiDropdownAnimation").$(byText("2020")).click();
    $(By.id("date-rows")).$(byText("10")).click();
    $(By.id("append-async-validation")).click();
    }
}
