package wayc.backend.shop.domain.valid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.Money;

@NoArgsConstructor
@Getter
public class OptionValidator {

    private String name;
    private Money price;

    public OptionValidator(String name, Money pricer) {
        this.name = name;
        this.price = pricer;
    }
}