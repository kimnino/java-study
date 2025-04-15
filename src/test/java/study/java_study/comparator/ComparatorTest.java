package study.java_study.comparator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    @Test
    void test_basic() {
        List<Integer> list = Arrays.asList(1, 22, 13, 64, 25, 116, 67, 248, 19, 210);

        // 내림차순으로 정렬한다.
        // Comparator<T> 인터페이스에 정의된 메서드를 사용
        // 로 정의되어있음. Integer 클래스는 Comparable을 구현하고 있어서 가능하다.
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);

        List<BasicGoods> basicGoodsList = Arrays.asList(
                BasicGoods.builder().goodsId(1).goodsName("입장권").goodsPrice(100.0).build(),
                BasicGoods.builder().goodsId(2).goodsName("투어").goodsPrice(500.0).build(),
                BasicGoods.builder().goodsId(3).goodsName("마사지").goodsPrice(100.0).build(),
                BasicGoods.builder().goodsId(4).goodsName("레스토랑").goodsPrice(1000.0).build(),
                BasicGoods.builder().goodsId(5).goodsName("숙소").goodsPrice(50.0).build());

        //basicGoodsList.sort(Comparator.reverseOrder()); //해당 코드는 오류
        /*
        BasicGoods는 Comparable을 구현하지 않아서
        Comparator의 reverseOrder() 메서드를 실행할 수 없다.
        그럼 방법이 없을까???
        Comparator.comparing 메서드를 활용해서 정렬에 기준이 될 필드를 정해서 사용 가능
        물론 사용하려는 필드의 타입이 Comparable을 구현 해야 합니다.
        */
        basicGoodsList.sort(Comparator.comparing(BasicGoods::getGoodsPrice));
        System.out.println(basicGoodsList);
        basicGoodsList.sort(Comparator.comparing(BasicGoods::getGoodsPrice).reversed());
        System.out.println(basicGoodsList);


        List<ComparableGoods> comparableGoodsList = Arrays.asList(
                ComparableGoods.builder().goodsId(1).goodsName("입장권").goodsPrice(100.0).build(),
                ComparableGoods.builder().goodsId(2).goodsName("투어").goodsPrice(500.0).build(),
                ComparableGoods.builder().goodsId(3).goodsName("마사지").goodsPrice(100.0).build(),
                ComparableGoods.builder().goodsId(4).goodsName("레스토랑").goodsPrice(1000.0).build(),
                ComparableGoods.builder().goodsId(5).goodsName("숙소").goodsPrice(50.0).build());

        comparableGoodsList.sort(Comparator.naturalOrder());
        System.out.println(comparableGoodsList);
    }

    @Test
    void test_comparing() {
        List<BasicGoods> basicGoodsList = Arrays.asList(
                BasicGoods.builder().goodsId(1).goodsName("입장권").goodsPrice(100.0).build(),
                BasicGoods.builder().goodsId(2).goodsName("투어").goodsPrice(500.0).build(),
                BasicGoods.builder().goodsId(3).goodsName("마사지").goodsPrice(100.0).build(),
                BasicGoods.builder().goodsId(4).goodsName("레스토랑").goodsPrice(1000.0).build(),
                BasicGoods.builder().goodsId(5).goodsName("숙소").goodsPrice(50.0).build());

        // 정렬할 키를 추출(첫 번쩨 인자), 정렬 키(두 번째 인자)를 설정
        basicGoodsList.sort(Comparator.comparing(BasicGoods::getGoodsName, String.CASE_INSENSITIVE_ORDER));
        System.out.println(basicGoodsList);


        List<Person> persons = Arrays.asList(
                Person.builder().age(20).name("김민혁").build(),
                Person.builder().age(26).name("아마드").build(),
                Person.builder().age(24).name("브루노").build(),
                Person.builder().age(33).name("요로").build(),
                Person.builder().age(30).name("마이누").build(),
                Person.builder().age(34).name("김민혁3").build(),
                Person.builder().age(34).name("김민혁2").build());

        persons.sort(Comparator.comparing(person -> person, Person.PERSON_CUSTOM_ORDER));
        System.out.println(persons);
    }


    @Test
    void test_comparingThen() {

    }


}
