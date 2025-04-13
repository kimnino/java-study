package study.java_study.comparator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    @Test
    void compareTo_Integer_test() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        integers.sort(Comparator.naturalOrder());

        for (Integer integer : integers) {
            System.out.print(integer);
        }
        System.out.println();

        integers.sort(Comparator.reverseOrder());
        for (Integer integer : integers) {
            System.out.print(integer);
        }
        System.out.println();

        integers.sort(Comparator.comparing(Integer::byteValue));
        for (Integer integer : integers) {
            System.out.print(integer);
        }
        System.out.println();

    }
}
