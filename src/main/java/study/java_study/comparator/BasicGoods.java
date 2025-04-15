package study.java_study.comparator;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasicGoods {

    private long goodsId;
    private String goodsName;
    private double goodsPrice;

    @Override
    public String toString() {
        return this.goodsName;
    }
}
