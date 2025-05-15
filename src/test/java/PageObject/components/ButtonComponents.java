package PageObject.components;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ButtonComponents {
    public void ClickButtonSubmit()
    {
        $(By.id("submit")).click();
    }
}
