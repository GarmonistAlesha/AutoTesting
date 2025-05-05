package org.example.autotesting;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
class RegSiteTest {
    private String Site = "https://demoqa.com/automation-practice-form";
    private String Name = "Jonh";
    private String Email = "test@gmail.com";
    private String LastName = "Smith";
    private String PhoneNumb = "8256485221";

    @BeforeAll
    public static void beforeAll()
    {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "Maximum";
    }
    @Test

    void Registration()
        {
            open(Site);
            $(By.id("firstName")).setValue(Name);
            $(By.id("lastName")).setValue(LastName);
            $(By.id("userEmail")).setValue(Email);
            $(By.id("gender-radio-1")).click();

        }

}
