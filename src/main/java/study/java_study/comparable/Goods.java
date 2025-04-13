package study.java_study.comparable;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@Builder
public class Goods implements Comparable<Goods> {

    private Long goodsId;
    private String goodsName;
    private Double goodsPrice;

    @Override
    public int compareTo(Goods goods) {
        return customCompareTo(goods, "price");
    }

    public int customCompareTo(Goods goods, String type) {
        return switch (type) {
            case "name" -> this.goodsName.compareTo(goods.goodsName);
            case "price" -> this.goodsPrice.compareTo(goods.goodsPrice);
            default -> this.goodsId.compareTo(goods.goodsId);
        };
    }
}


