package RegistrationPage.components;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String day , String month, String year)
    {

        //$(By.id("dateOfBirthInput")).click();//открытие календаря(Закоментили,
        // т.к может в другой странице отличатся название селектора)
        $(".react-datepicker__month-select").selectOption(month); //выбор в календаре месяца
        $(".react-datepicker__year-select").selectOptionByValue(year); //выбор в календаре года
        //$(By.className("react-datepicker__day--020")).click(); //выбор в календаре дня( не очень хорошо)
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click(); //выбор в календаре дня
        // (Хорошо,т.к исключает выбор повторяющийся даты)
    }
}
