package wayc.backend.shop.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemRegisteredEvent {

    private List<String> stockOption;

    public ItemRegisteredEvent(List<String> stockOption) {
        this.stockOption = stockOption;
    }
}
