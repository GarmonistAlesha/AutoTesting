package org.example.autotesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import test.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

 class AutorizationDromSite extends TestBase {
    private String numberPhone = "79234066654";
    private String password = "423197200893Mvp!";
    private String site = "https://www.drom.ru/";

    @Test

    void FindDromChrome()
    {
        open(site);
        $(".oco7hz0").click();
        $(By.id("sign")).setValue(numberPhone); //Нахождение элемента по id в CSS
        $(By.id("password")).setValue(password);//Нахождение элемента по id в CSS
        $(By.id("signbutton")).click();//Нахождение элемента по id в CSS
    }
}
