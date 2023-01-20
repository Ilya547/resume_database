package webapp.main;

import webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume r = new Resume("Name");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);
        field.setAccessible(false);

        Method method = r.getClass().getDeclaredMethods()[0];
        method.setAccessible(true);
        System.out.println(method.getName());
        System.out.println(method.invoke(r));
        method.setAccessible(false);
    }
}


