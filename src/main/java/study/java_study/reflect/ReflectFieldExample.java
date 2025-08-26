package study.java_study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectFieldExample {

    public static void main(String[] args) {
        try {
            Class<?> targetClass = Class.forName("study.java_study.reflect.ReflectFieldExample$A");
            Constructor<?> targetConstructor = targetClass.getConstructor();
            Object instance = targetConstructor.newInstance();

            Field nameField = targetClass.getDeclaredField("name");

            nameField.setAccessible(true);

            nameField.set(instance, "김민혁짱짱맨");

            String aName = (String) nameField.get(instance);

            System.out.println("설정된 name = " + aName);

        } catch (ClassNotFoundException e) {
            System.out.println("Class 예외 발생");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("Constructor 예외 발생");
        } catch (NoSuchFieldException e) {
            System.out.println("Field 예외 발생");
        }
    }

    public static class A {

        private String name;
    }
}
