package wayc.backend.shop.domain.port;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OptionValidator {

    private String name;
    private Integer price;

    public OptionValidator(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}