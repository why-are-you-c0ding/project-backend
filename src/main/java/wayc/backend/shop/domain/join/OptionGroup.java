package wayc.backend.shop.domain.join;

import lombok.Builder;

import java.util.List;

public class OptionGroup {

    private String name;
    private Option options;

    @Builder
    public OptionGroup(String name, Option options) {
        this.name = name;
        this.options = options;
    }
}
