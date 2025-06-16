package PageObject.components;

import static com.codeborne.selenide.Selenide.$;

public class CurrentFormComponents {
    public void SetCurrentAdress(String value)
    {
        $("#currentAddress").setValue(value);
    }

}
