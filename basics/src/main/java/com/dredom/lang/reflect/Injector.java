package com.dredom.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        InjectionTarget target = new InjectionTarget();
        Field field = target.getClass().getDeclaredField("datum");
        Datum newDatum = new Datum(1, "Joe");
        field.setAccessible(true);
        field.set(target, newDatum);
        field.setAccessible(false);

        Datum result = target.getDatum();
        System.out.println(result.toString());
    }

//    private static void doTheInjection(MyClass u, int value) throws IllegalAccessException {
//
//        Field[] camps = u.getClass().getDeclaredFields();
//
//        System.out.println("------- fields : --------");
//        for (Field f : camps) {
//            System.out.println(" -> " + f.toString());
//            Annotation an = f.getAnnotation(Inject.class);
//            if (an != null) {
//                System.out.println("       found annotation: " + an.toString());
//                System.out.println("       injecting !");
//                f.setAccessible(true);
//                f.set(u, value);
//                f.setAccessible(false);
//            }
//        }
//
//    }
}
