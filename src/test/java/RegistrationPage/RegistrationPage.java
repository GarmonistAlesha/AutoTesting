package RegistrationPage;

import RegistrationPage.components.CalendarComponent;
import RegistrationPage.components.registrationResultsModal;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage
{
    private CalendarComponent calendarComponent = new CalendarComponent();
    private registrationResultsModal registrationResultsModal = new registrationResultsModal();
    private String Site = "https://demoqa.com/automation-practice-form";
    private String Name = "Jonh";
    private String Email = "test@gmail.com";
    private String LastName = "Smith";
    private String PhoneNumb = "8256485221";
    private SelenideElement lastNameInput = $("#lastName"), //- можно вынести селектор и переиспользовать его в методах класса
                            firstNameInput = $("#firstName"),
                            emailInput = $("#userEmail"),
                            phoneInput = $("#userNumber");
    public RegistrationPage OpenPage() {
        // Selenide.executeJavaScript("('fixexedban').remove()"); //Выполнение команды JQuery в селениде ( отключение банерной рекламы)
        open(Site);

        return this;
        // за место void мы поставили RegistrationPage(название класса), что бы в тестах убрать название класса
        // метод теперь возвращает сам себя
    }
    public RegistrationPage SetFirstName(String value)
    {
        //$(By.id("#firstName")).setValue(Name);
       firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage SetLastName(String value)
    {
        //$(By.id("#lastName")).setValue(LastName);
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage SetEmail(String value)
    {
        //$(By.id("userEmail")).setValue(Email);
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage SetPhoneNumber(String value)
    {
        //$("#userNumber").setValue(value);
        phoneInput.setValue(value);

        return this;
    }

    public RegistrationPage SetGender(String value)
    {
        //Пример выбора чек-бокса (точка выбора)
        // $(By.className("custom-control-inline")).click(); //Не очень хороший, но рабочий вариант(мой вариант)
        //$("#gender-radio-1").parent().click(); // хороший вариант
        $("#genterWrapper").$(byText(value)).click(); // Лучший вариант

        return this;
    }

    public RegistrationPage SetBirthDay(String day , String month, String year)
    {
        $(By.id("dateOfBirthInput")).click();//открытие календаря
        /*
         $(".react-datepicker__month-select").selectOption(month); //выбор в календаре месяца
        $(".react-datepicker__year-select").selectOptionByValue(year); //выбор в календаре года
        //$(By.className("react-datepicker__day--020")).click(); //выбор в календаре дня( не очень хорошо)
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click(); //выбор в календаре дня
        // (Хорошо,т.к исключает выбор повторяющийся даты)
         */
        calendarComponent.setDate(day , month, year);
        // (Хорошо,т.к исключает выбор повторяющийся даты)
        return this;
    }

    public RegistrationPage veryfyRegistrationModalWindow()
    {
        registrationResultsModal.RegistrationModal();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value)//метод, который проверяет,
    // что в правильной колонке правильная информакция
    {
        registrationResultsModal.verifyResult(key,value);

        return this;

    }
}