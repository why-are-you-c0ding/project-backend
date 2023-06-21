package wayc.backend.shop.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.event.Event;
import wayc.backend.shop.domain.OptionGroup;

import java.util.List;


@Getter
@NoArgsConstructor
public class ItemRegisteredEvent extends Event {

    private List<OptionGroup> groups;

    public ItemRegisteredEvent(List<OptionGroup> groups) {
        this.groups = groups;
    }
}
