package org.example.parcial_a.App;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {

    private static  ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example.parcial_a");

    private AppContext(){}

    public static ApplicationContext getInstance(){
        return applicationContext;
    }
}
