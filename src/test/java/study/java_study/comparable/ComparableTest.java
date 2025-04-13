package study.java_study.comparable;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;


public class ComparableTest {

    @Test
    void bigdecimal_test() {

        BigDecimal a = new BigDecimal("4.0");
        BigDecimal b = new BigDecimal("4.00");

        System.out.println("a.compareTo(b)의 결과는 : " + a.compareTo(b));
        System.out.println("a.equals(b)의 결과는 : " + a.equals(b));
    }

    @Test
    void int_test() {
        int[] numbers = {1, 4, 2, 8, 10, 6};

        Arrays.sort(numbers);

        System.out.println(Arrays.toString(numbers));

        // 숫자를 내림차순으로 정렬하고 싶다면...?
        // 기본적으로 Arrays.sort()는 오름차순만 제공
        // int[]처럼 **기본형 배열(primitive array)**은 Comparator를 쓸 수 없다.
    }


    @Test
    void integet_test() {
        Integer[] numbers = {1, 4, 2, 8, 10, 6};

        Arrays.sort(numbers, Collections.reverseOrder());

        System.out.println(Arrays.toString(numbers));
        // Integer 클래스는 Comparable을 구현한 클래스로 Collections.reverseOrder()를 사용할 수 있다.
        /*
            Integer 클래스 구현 내용
            public int compareTo(Integer anotherInteger) {
                return compare(this.value, anotherInteger.value);
            }

             public static int compare(int x, int y) {
                return (x < y) ? -1 : ((x == y) ? 0 : 1);
             }
         */
    }

    @Test
    void compareTo_String_test() {
        String str1 = "aaaaa";
        String str2 = "aaa";
        String str3 = "aaaaa";
        String str4 = "ffff";

        // String 클래스에는
        /*
        public int compareTo(String anotherString) {
            비교대상 문자열의 각 문자들을 첫 번째 자리부터 하나씩 비교하다가 가장 먼저 만나는 상이한
            문자들의 ASCII코드 값의 차이를 반환하고 끝낸다.

            동일한 문자로 구성되어 있지만 길이가 다르다면
            같은 길이까지는 문자를 비교하지만, 그 이후에는 서로의 길이의 차이를 반환하도록 메서드에 구현

         }
         */

        System.out.println(str1.compareTo(str2)); // 2
        System.out.println(str1.compareTo(str3)); // 0
        System.out.println(str1.compareTo(str4)); // -5
    }


    @Test
    void compareTo_Object_test() {
        Goods goods1 = Goods.builder().goodsId(1L).goodsName("투어").goodsPrice(1000.0).build();
        Goods goods2 = Goods.builder().goodsId(2L).goodsName("입장권").goodsPrice(6000.0).build();
        Goods goods3 = Goods.builder().goodsId(3L).goodsName("마사지").goodsPrice(3000.0).build();
        Goods goods4 = Goods.builder().goodsId(4L).goodsName("레스토랑").goodsPrice(2000.0).build();
        Goods goods5 = Goods.builder().goodsId(5L).goodsName("투어").goodsPrice(500.0).build();
        List<Goods> goodsList = new ArrayList<>(List.of(goods1, goods2, goods3, goods4, goods5));

        Collections.sort(goodsList);

        goodsList.forEach(System.out::println);

    }



}
