package study.java_study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectConstructorExample {

    public static void main(String[] args) {
        try {
            Class<?> targetClass = Class.forName("study.java_study.reflect.ReflectConstructorExample$A");
            Constructor<?> targetConstructor = targetClass.getConstructor();

            Object instance = targetConstructor.newInstance();

            System.out.println("생성자를 통해 생성된 객체 타입 :: " + instance.getClass().getName());

        } catch (ClassNotFoundException e) {
            System.out.println("Class 예외 발생");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("Constructor 예외 발생");
        }
    }

    public static class A {
        public A() {
            System.out.println("A 클래스 생성자 호출");
        }
    }
}
