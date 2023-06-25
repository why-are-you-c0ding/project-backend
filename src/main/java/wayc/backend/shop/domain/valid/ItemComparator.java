package wayc.backend.shop.domain.valid;

import java.util.List;

public interface ItemComparator {
    Long getItemId();
    String getName();
    List<OptionGroupComparator> getComparisonOrderOptionGroups();
}
