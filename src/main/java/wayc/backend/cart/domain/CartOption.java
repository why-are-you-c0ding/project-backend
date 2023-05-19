package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.OptionValidator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class CartOption{

    private String optionName;

    private Integer price;

    @Builder
    public CartOption(String name, Integer price) {
        this.optionName= name;
        this.price = price;
    }

    public OptionValidator convertToOptionValidator() {
        return new OptionValidator(optionName, price);
    }
}

