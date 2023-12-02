package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.Money;
import wayc.backend.shop.domain.valid.OptionValidator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class CartOption {

    private String optionName;

    private Money price;

    @Builder
    public CartOption(String name, Integer price) {
        this.optionName= name;
        this.price = Money.from(price);
    }

    public OptionValidator convertToOptionValidator() {
        return new OptionValidator(optionName, price);
    }
}