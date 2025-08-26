package study.java_study.reflect;

public class ReflectClassExample {

    public static void main(String[] args) {
        try {
            Class<?> targetClass = Class.forName("study.java_study.reflect.ReflectClassExample$A");
            System.out.println("Class 객체가 나타내는 클래스 이름 :: " + targetClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Class 객체를 찾을 수 없다.");
        }
    }

    public static class A {
        private String name;
    }
}
