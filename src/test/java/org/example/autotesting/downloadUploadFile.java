package org.example.autotesting;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.io.Zip;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class downloadUploadFile
{
    private ClassLoader cl = downloadUploadFile.class.getClassLoader();  // Необходим, что бы читать файлы из resources
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

    @Test
    void xlsTestdownload() throws Exception { //Метод загрузки Excel файла и проверки (необходимо подключить библиотеку
        // в buildgradle xlstest maven(найти)
        open("https://itsm365.com/documents_rus/web/Content/import/import_org_file.htm");
        File download = $("a[href *= 'import_ou_xls.xls']").download();
        XLS xls = new XLS(download);
        System.out.println();
        Assertions.assertTrue(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue().startsWith("Внешний идентификатор для импорта"));
        //xls.excel.getSheetAt(0)- страница таблицы.getRow(0)- столбец таблицы.getCell(0)-строка таблицы.getStringCellValue()
        //Возникает ошибка в большинстве случаев, надо сделать пару манипуляций:
//      в терминале вбить gradle dependencies
//      найти версию ошибки (тут была org.apache.poi:poi:3.9*(приходит из xlstest) -> 5.3.0(а ищет в pdftest))
//      Идем в gradle.build  и вставляем исключение в xlstest exclude group : "То, что надо исключить"
//      Но лучше посмотреть совместимость xlstest и pdftest
//      (  testImplementation("com.codeborne:pdf-test:1.5.0") и testImplementation("com.codeborne:xls-test:1.4.3")
    }

    @Test
    void workCsvFile() throws Exception
    {
       try (InputStream is = cl.getResourceAsStream("TestData/testCsv.csv");
            InputStreamReader isr = new InputStreamReader(is))
        // Необходимо подключить библиотеку OpenCsv
        {
            CSVReader csvReader = new CSVReader(isr);
            List<String[]> content = csvReader.readAll();//Каждая строчка в CSV это массив string List - это список строчек
            Assertions.assertArrayEquals(new String[]{"QuotaAmount","StartDate","OwnerName","Username"},content.get(0));
            // Сравнение данных в 1 строчке CSV файла
        }
    }

    @Test
    void workZipfile () throws Exception
    {
        try (InputStream is = cl.getResourceAsStream("TestData/testjpg.zip");
             ZipInputStream zs = new ZipInputStream(is))
        {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) //Цикл распоковки файлов с архива( повторяет операцию до тех пор,
                // пока есть файлы)
            {
            Assertions.assertTrue(entry.getName().contains("testjpg.JPG"));
            }
        }

    }
    @Test
    void workJsonFile () throws Exception //Метод работы Json файла и проверки (необходимо подключить библиотеку
    // в buildgradle com.google.code.gson:gson:2.13.1 maven(найти)
    {
        Gson gson = new Gson();
        try (InputStream is = cl.getResourceAsStream("TestData/testJson.json");
             InputStreamReader br = new InputStreamReader(is))
        {
            JsonObject jsonObject = gson.fromJson(br, JsonObject.class);
            Assertions.assertEquals("John",jsonObject.get("name").getAsString());
            Assertions.assertEquals(30,jsonObject.get("age").getAsInt());
            Assertions.assertEquals("New York",jsonObject.get("city").getAsString());
            //Лучше создать отдельный класс для проверки Json
        }
    }
}



