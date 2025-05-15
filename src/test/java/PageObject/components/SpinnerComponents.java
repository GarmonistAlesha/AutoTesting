package PageObject.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SpinnerComponents {
    public void SetStateRegistration(String value)
    {
        //Нажатие на выпадающий список и выбор значения
        //$(By.id("state")).click(); //нажатие на выпадающий список
        $("#state").click(); //нажатие на выпадающий список
        $("#stateCity-wrapper").$(byText(value)).click(); //Выбор в выпадающем списке
    }

    public void SetCityRegistration(String value)
    {
        //Нажатие на выпадающий список и выбор значения
        //$(By.id("state")).click(); //нажатие на выпадающий список
        $("#city").click();
        $("#stateCity-wrapper").$(byText(value)).click();
    }
}
