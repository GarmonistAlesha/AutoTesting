package PageObject.components;

import static com.codeborne.selenide.Selenide.$;
//Метод загрузки файлов
public class UploadComponent {


    public void UploadPicture(String location )
    {
        $("#uploadPicture").uploadFromClasspath(location);
    }
}
