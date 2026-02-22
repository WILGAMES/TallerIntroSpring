package org.example.parcial_practica2.App;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {

    private static  ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example.parcial_practica2");

    private AppContext(){}

    public static ApplicationContext getInstance(){
        return applicationContext;
    }
}
