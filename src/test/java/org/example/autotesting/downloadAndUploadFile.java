package org.example.autotesting;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class downloadAndUploadFile
{
    @BeforeAll
    public static void Before()
    {

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
       // Configuration.fileDownload = FileDownloadMode.PROXY; // Используется, когда нет href( но лучше не использовать)
    }

    @Test
    void downloadFile  () throws Exception  { //Это означает, что в процессе работы метод может "выбросить" исключение,
        // которое указано после слова throws(обязательно делать)
    open("https://github.com/GarmonistAlesha/RubiusHomeWork/blob/main/README.md");
    File download = $("a[href*= '/raw/refs/heads/main/README.md']").download();//
        // Загрузка файла нажатием на клавишу Raw в GitHub
        // download работает только с атрибутами, у которых есть a[href(иногда нет такого)
        // Объект типа File - это абстракция над путями к файлу (загруженый файл находится в build/downloads/sessionid запуска)
       try (InputStream is = new FileInputStream(download))
       {
          byte[] bytes = is.readAllBytes();
          String fileAsString = new String(bytes, StandardCharsets.UTF_8); // Конструкция проверки скаченного файла на совпадение текста в нем с assertTrue
           Assertions.assertTrue(fileAsString.contains("RubiusHomeWork"));
       }

    }
    //Все файлы, с которыми работаем, необходимо помещать в папку resources в проекте
    @Test
    void uploadFile ()
    {
        open("https://tus.io/demo.html");
        $("input[type = 'file']").uploadFromClasspath(("img/1.png")); //Загрузка файла с папки resources в
        // директории test java(сокращенно) (selenid метод), uploadFile лучше не использовать
        // ("img/1.png") img - папка, 1.png - название файла
        //селектор input[type = 'file'] работает всегда(загрузка файлов работает через один и тот же элемент)
        $("._heading_gq6c0_21").shouldHave(Condition.text("The upload is complete!"));
    }

    @Test
    void pdfTestdownload() throws Exception { //Метод загрузки PDF файла и проверки авторов (необходимо подключить библиотеку
        // в buildgradle pdftest maven(найти)
        open("https://junit.org/junit5/docs/current/user-guide/");
        File download = $("a[href *= 'junit-user-guide-5.12.2.pdf']").download();
        PDF pdf = new PDF(download);
        System.out.println();
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein",pdf.author);

    }
}



