package wayc.backend.shop.domain.valid;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OptionGroupValidator {

    private String name;
    private OptionValidator option;

    public OptionGroupValidator(String name, OptionValidator options) {
        this.name = name;
        this.option = options;
    }
}
