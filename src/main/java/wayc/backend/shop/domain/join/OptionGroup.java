package wayc.backend.shop.domain.join;

import lombok.Builder;

import java.util.List;

public class OptionGroup {

    private String name;
    private List<Option> options;

    @Builder
    public OptionGroup(String name, List<Option> options) {
        this.name = name;
        this.options = options;
    }
}
