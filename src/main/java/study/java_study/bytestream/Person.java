package study.java_study.bytestream;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class Person implements Serializable {

    private String name;
    private int age;
    private String nickName;

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", nickName: " + nickName;
    }
}
