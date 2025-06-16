package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class WebStepsAllureStud {
    @Step("Открываем страницу ВСК")
    public void openVskSite()
    {
        open("/klientam");
        attachment("source",webdriver().driver().source());
    }
    @Step("Ищем элемент 'Ипотека'")
    public void IpotekaElement()
    {
        $(By.id("main_cards_mortgage_title")).$(byText("Ипотека")).click();
        $(".tui-autofill").click();
    }
    @Step("Выбор банка'")
    public void setBank(String bank)
    {
        $(".t-scroll").$(byText(bank)).click();
        $(".calculate-button").click();
    }
    @Step("Проверка открытия модального окна 'Расчет стоймости'")
    public void modalWindowOpenAppruve()
    {
        $(".ng-trigger-tuiSlideInTop").should(appear);
        $(".modal-window").shouldHave(text("Расчёт стоимости"));
    }
    @Step("Выбор даты заполнения формы")
    public void inputDate(String year, String day)
    {
        $(".vsk-custom-input-date").click();
        //$(".vsk-custom-input-date").setValue("20.08.2018");

        $(By.id("year-btn")).click();
        $(".ng-trigger-tuiDropdownAnimation").$(byText(year)).click();
        $(By.id("date-rows")).$(byText(day)).click();
    }

        // Так же можно создать тест с помощью аннотаций @Step, выведя шаги в отдельные классы
        // (подход WebSteps(лучше делать, т.к расчитан на переиспользование)
        // Разница в них в том, что за обработку @Step обрабатывает aspectjWeaver.set(true)
        //Урок 15 закончил на 55 минуте, дальше продолжить
    }

