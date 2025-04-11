package study.java_study.comparable;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ComparableTest {

    @Test
    void bigdecimal_test() {

        BigDecimal a = new BigDecimal("4.0");
        BigDecimal b = new BigDecimal("4.00");

        System.out.println("a.compareTo(b)의 결과는 : " + a.compareTo(b));
        System.out.println("a.equals(b)의 결과는 : " + a.equals(b));

    }


}
