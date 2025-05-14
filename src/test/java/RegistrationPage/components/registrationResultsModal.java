package RegistrationPage.components;

import RegistrationPage.RegistrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class registrationResultsModal {
    public void RegistrationModal()
    {
        $(".modal-dialog").should(appear); //Проверка открытия модального окна
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Проверка соответствия выводимого текста
        //$(".table-responsive").shouldHave(text(nameValue), text(lastNameValue), text(emailValue),text(phonevalue));
        //Закоментирован, т.к проверка реализована в методе verifyResult
    }
    public void verifyResult(String key, String value)//метод, который проверяет,
    // что в правильной колонке правильная информакция
    {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));

    }

}
