package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.Money;
import wayc.backend.shop.domain.valid.OptionValidator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class OrderOption{

    private String name;
    private Money price;

    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public OptionValidator convertToOptionValidator() {
        return new OptionValidator(name, price);
    }
}

//수정할 사항이 없을것이므로 값 타입으로 구현.