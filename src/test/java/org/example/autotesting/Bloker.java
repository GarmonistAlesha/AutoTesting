package org.example.autotesting;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Аннотация, что метод может запускаться и Junit го прочитает
@Target(ElementType.METHOD) //ставиться аннотиация над методом
@Tag("Bloker")

public @interface Bloker {

}
