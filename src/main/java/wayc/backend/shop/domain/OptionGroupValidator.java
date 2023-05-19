package wayc.backend.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
