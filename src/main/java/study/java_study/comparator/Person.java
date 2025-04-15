package study.java_study.comparator;

import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;

@Getter
@Builder
public class Person {
    public static final Comparator<Person> PERSON_CUSTOM_ORDER = new PersonCustomComparator();
    private int age;
    private String name;

    @Override
    public String toString() {
        return age + "세 " + name;
    }

    private static class PersonCustomComparator
            implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) { // 나이 내림차순, 이름 오름차순
            if (o1.age == o2.age) {
                return o1.name.compareTo(o2.name);
            }
            return o2.age - o1.age;
        }
    }
}
