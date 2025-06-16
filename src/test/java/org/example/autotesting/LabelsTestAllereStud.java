package org.example.autotesting;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTestAllereStud {
    @Test
    @Feature("Открытие сайта ВСК") //Разработка управляемого требованиями (BDD)
    @Story("Автотесты") //Пользовательская история(как пользователь ведет себя при работе с историей)
    @Owner("ponomarevmv") //Владелец теста (тот, кто создал тест)
    @Severity(SeverityLevel.NORMAL) //Серьезность теста
    @Link(value = "Testing", url = "https://www.vsk.ru") // добавление ссылок в тест
    @DisplayName("Проверка заполнения полей 'Ипотека' ВСК и получение отчета в Allure") //Название теста
    //Статическая аннотация

    //Тоже самое только в виде теста
    @Test
    public void testDynamicLabels()
    {
        Allure.getLifecycle().updateTestCase(t -> t.setName("Проверка заполнения полей 'Ипотека' ВСК и получение отчета в Allure")); //
        // == DisplayName
        Allure.feature("Открытие сайта ВСК"); //@Feature
        Allure.story("Автотесты"); //@Story
        Allure.label("owner","ponomarevmv"); //@Owner
        Allure.label("severity",SeverityLevel.NORMAL.value()); //@Severity
        Allure.link( "Testing", "https://www.vsk.ru"); // @Link
        //Динамическая аннотация (Можно расписсать расширения для вреймворка, для опытных автотестеров)
    }

    //Аннотации нужны всегда (Автотесты как документация) - нужна для простоты чтение теста
}
