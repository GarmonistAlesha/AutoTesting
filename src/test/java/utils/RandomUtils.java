package utils;

import com.github.javafaker.Faker;
import test.TestData;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static test.TestData.*;


public class RandomUtils {
    TestData testData = new TestData();
    Faker fakerRu = new Faker(new Locale("ru"));
    Faker faker = new Faker();
    public static void main(String[] args)
    {
        System.out.println(getRandonString(10));
        System.out.println(getRandonStringDate(4));
        System.out.println(getRandomInt(10 , 100));
        System.out.println(getRandomEmail());

        String[] names = {"a","b","c","d","f"};
        System.out.println(getRandomIthemFromArray(names));
    }
    //Метод генерации random строчки( tGNgcUOSZk формата)
    public static String getRandonString(int leght)
    {
        //String AB  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        String AB  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(leght);
        for (int i=0; i < leght; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String getRandonStringDate(int leght)
    {
        String AB  = "1234567890";
        //String AB  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(leght);
        for (int i=0; i < leght; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    //Генерация random числа (между минимальным и максимальным значением)
    public static int getRandomInt(int min, int max)
    {

        return ThreadLocalRandom.current().nextInt(min, max +1 );
    }
    // Рандомный выбор по индексу с массива(пример с https://github.com/grafkalugin/demoLes3/blob/faker/src/test/java/com/example/demoles3/tests/MainPageTest.java)
    public static String getRandomValue(String[] arr)
    {
        int index = new Random().nextInt(arr.length);
        return arr[index];
    }
    // Генерация random Email(пример YxsUWiivzU@gmail.com)
    public static String getRandomEmail()
    {
        return getRandonString(10) + "@gmail.com";
    }
    //Рандом из массива (на примере массива имен выше)
    public static String getRandomIthemFromArray(String[] values)
    {
        int index = getRandomInt(0, values.length - 1); //(Длина массива например равна 5,т.к массив начинается с 0,
        //то длинна обращаться он может только к длине массива -1

        return values[index];

    }
    //Создание рандомного имени и фамилии библиотекой Faker
    public static  String getFakerName()
    {
        return new Faker().name().firstName();
    }
    public static  String getFakerLastName()
    {
        return new Faker().name().lastName();
    }
    //Создание рандомного почты и номера телефона библиотекой Faker
    public static  String getFakerEmail()
    {
        return new Faker().internet().emailAddress();
    }
    public static  String getFakerPhoneNumber()
    {

        return new Faker(new Locale("ru")).phoneNumber().phoneNumber();

    }
    public static  String getFakerAdressRegistration()
    {

        return new Faker().address().fullAddress();

    }
    //Зависимость выбора города от выбора штата
    public static String getRandomCity(String state) {
        String[] cities;
        switch (state) {
            case "NCR" : cities = ncr;
                break;
            case "Uttar Pradesh" : cities = uttarpradesh;
                break;
            case "Haryana" : cities = haryana;
                break;
            case "Rajasthan" : cities = rajasthan;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);

        };
        return cities[new Random().nextInt(cities.length)];
    }

}

