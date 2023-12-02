package wayc.backend.shop.domain.valid;

import wayc.backend.common.domain.Money;

import java.util.List;

public interface ItemComparator {
    Long getItemId();
    String getName();
    Money getPrice();
    List<OptionGroupComparator> getComparisonOrderOptionGroups();
}
