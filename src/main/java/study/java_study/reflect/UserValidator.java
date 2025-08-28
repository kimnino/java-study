package study.java_study.reflect;

import java.lang.reflect.Field;

public class UserValidator {

    public static void main(String[] args) throws IllegalAccessException {
        User user1 = new User("김민혁", 34);
        UserValidator.validate(user1);

        User user2 = new User("아모림", 40);
        UserValidator.validate(user2);

        User user3 = new User("콘버지", 55);
        UserValidator.validate(user3);
    }

    public static <T> boolean validate(T object) throws IllegalAccessException {
        Class<?> targetClass = object.getClass();

        for (Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Minhyuk.class)) {
                Minhyuk minhyuk = field.getAnnotation(Minhyuk.class);
                field.setAccessible(true);
                Object value = field.get(object);

                if (value instanceof Integer) {
                    int targetAge = (Integer) value;
                    if (targetAge != minhyuk.value()) {
                        System.out.println("유효성 검증 실패!!!!");
                        return false;
                    }
                }
            }
        }
        System.out.println("유효성 검증 성공!");
        return true;
    }
}
