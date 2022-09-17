package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.base.BaseEntity;
import wayc.backend.shop.domain.join.Option;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class OrderOption extends BaseEntity {

    private String name;
    private Integer price;

    public OrderOption(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Option convertToOption() {
        return new Option(name, price);
    }
}

//수정할 사항이 없을것이므로 값 타입으로 구현.