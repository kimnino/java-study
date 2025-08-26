package study.java_study.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethodExample {

    public static void main(String[] args) {
        try {
            Class<?> targetClass = Class.forName("study.java_study.reflect.ReflectMethodExample$A");
            Constructor<?> targetConstructor = targetClass.getConstructor();
            Object instance = targetConstructor.newInstance();

            Method setName = targetClass.getMethod("setName", String.class);
            Method getName = targetClass.getMethod("getName");

            setName.invoke(instance, "김민혁");
            getName.invoke(instance);
        } catch (ClassNotFoundException e) {
            System.out.println("Class 예외 발생");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("Constructor 예외 발생");
        }
    }

    public static class A {

        private String name;

        public void setName(String name) {
            System.out.println("A 클래스의 setName() 메서드를 호출했습니다.");
            this.name = name;
        }

        public void getName() {
            System.out.println("A 클래스의 name은 " + name + "입니다.");
        }
    }
}
