package wayc.backend.shop.domain.port;

import java.util.List;

public interface ItemComparator {
    Long getItemId();
    String getName();
    List<OptionGroupComparator> getComparisonOrderOptionGroups();
}
