package study.java_study.comparator;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ComparableGoods implements Comparable<ComparableGoods> {

    private long goodsId;
    private String goodsName;
    private double goodsPrice;


    // 가격의 내림차순, 이름의 오름차순
    @Override
    public int compareTo(ComparableGoods o) {
        if (this.goodsPrice == o.goodsPrice) {
            return this.goodsName.compareTo(o.goodsName);
        }

        return Double.compare(this.goodsPrice, o.goodsPrice) * -1;
    }

    @Override
    public String toString() {
        return this.goodsName;
    }
}
