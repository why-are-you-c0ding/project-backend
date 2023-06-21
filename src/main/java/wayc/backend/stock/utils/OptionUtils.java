package wayc.backend.stock.utils;

import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;

import java.util.ArrayList;
import java.util.List;

public class OptionUtils {

    public static FillStockRequestDto createNumberOfAllOptionsToFillStock(List<OptionGroup> optionGroups) {
        return selectOptions(optionGroups);
    }

    private static FillStockRequestDto selectOptions(List<OptionGroup> optionGroups) {
        FillStockRequestDto request = new FillStockRequestDto();
        selectOptionsRecursive(optionGroups, new ArrayList<>(), 0, request);
        return request;
    }

    private static void selectOptionsRecursive(List<OptionGroup> optionGroups,
                                              List<Long> selected,
                                              int currentIndex,
                                              FillStockRequestDto selectedOptions) {
        if (currentIndex == optionGroups.size()) {
            selectedOptions.addDto(new FillStockInfoRequestDto(new ArrayList<>(selected), 0));
            return;
        }

        OptionGroup optionGroup = optionGroups.get(currentIndex);
        for (Option option : optionGroup.getOptions()) {
            selected.add(option.getId());
            selectOptionsRecursive(optionGroups, selected, currentIndex + 1, selectedOptions);
            selected.remove(selected.size() - 1);
        }
    }
}
