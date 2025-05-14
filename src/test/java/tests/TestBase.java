package tests;

import RegistrationPage.RegistrationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
//Создался для сокращения кода в тестах, конфигурация прописывается 1 раз
public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage(); //Сделано для экономии памяти

    @BeforeAll
    static void beforeAll()
    {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }
}
