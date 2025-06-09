package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import test.TestBase;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class allureReportStud {

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
    // для выставления параметра(только 1 параметр)
    // если надо 2 и более, то используем CsvSource или CsvFileSource(CSV файл должен содержаться в папке resources)
    //@MethodSource(имя метода) - аннотация, которая пишет статический метод, который может вернуть любые Java объекты используемые в программе
    // static Stream<Arguments> имя класса и return Stream.of() Arguments.of(объект, который хочу вернуть) Arguments.of()
    // Argument.of указывается столько раз, сколько хотим запустить тесты
    @Test
    @DisplayName("Проверка заполнения полей 'Ипотека' ВСК и получение отчета в Allure") //Аннотация названия теста
    @Tag("Bloker") //в эту аннотацию можно вставлять Приоритет и Серьезность
    //@Bloker // самостоятельно созданная аннотация, заменяет Tag
    @Tags({ //аннотация, принимающая несколько тегов (удобно для сортировки тестов,
            // что-бы потом запускать только те, которые нужны
            @Tag("Bloker"),
            @Tag("WEB")
    })
    void ipotekaVskAllure()
    {
        SelenideLogger.addListener("allure",new AllureSelenide()); // Строчка, при добавлении которой появляется
        //сценарии теста, артефакты теста,PageSourse( страница кликабельная, где можно посмотреть элементы итп)ъ
        step("Открываем страницу ВСК" , () -> {  //команда для формирования шагов в отчетах
            open("/klientam");
            attachment("source",webdriver().driver().source()); //Аннотация, которая делает скриншот
        }); //Делает шаги, как в простом тест-кейсе
        step("Ищем элемент 'Ипотека'", () -> { //лямбда(сокращение метода) () - аргументы метода
            $(By.id("main_cards_mortgage_title")).$(byText("Ипотека")).click();
            //Выбор банка на странице ипотека
            $(".tui-autofill").click();

        });
        step("Выбор банка'", () -> { //лямбда(сокращение метода) () - аргументы метода
        $(".t-scroll").$(byText("Сбербанк")).click();
        $(".calculate-button").click();

        });
        step("Проверка открытия модального окна 'Расчет стоймости'", () -> { //лямбда(сокращение метода) () - аргументы метода
        $(".ng-trigger-tuiSlideInTop").should(appear);
        $(".modal-window").shouldHave(text("Расчёт стоимости"));

        });
        step("Выбор даты заполнения формы", () -> { //лямбда(сокращение метода) () - аргументы метода
            $(".vsk-custom-input-date").click();
            //$(".vsk-custom-input-date").setValue("20.08.2018");

            $(By.id("year-btn")).click();
            $(".ng-trigger-tuiDropdownAnimation").$(byText("2020")).click();
            $(By.id("date-rows")).$(byText("10")).click();

        });

        //Для генерации отчета в allure надо перейти в gradle/Tasks/verification/allureServe  и начнется генерация отчетов
        //с папкеи allure-results

        // Так же можно создать тест с помощью аннотаций @Step, выведя шаги в отдельные классы
        // (подход WebSteps(лучше делать, т.к расчитан на переиспользование)
        // Разница в них в том, что за обработку @Step обрабатывает aspectjWeaver.set(true)
        //Урок 15 закончил на 55 минуте, дальше продолжить
    }
}
