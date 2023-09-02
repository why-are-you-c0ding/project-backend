package wayc.backend.stock.domain;

import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockFactory {

    public static List<Stock> createNumberOfAllOptionsToFillStock(List<OptionGroup> optionGroups) {
        return selectOptions(optionGroups);
    }

    private static List<Stock> selectOptions(List<OptionGroup> optionGroups) {
        List<Stock> lists = new ArrayList<>();
        selectOptionsRecursive(optionGroups, new ArrayList<>(), 0, lists);
        return lists;
    }

    private static void selectOptionsRecursive(List<OptionGroup> optionGroups,
                                              List<Long> selected,
                                              int currentIndex,
                                              List<Stock> list) {
        if (currentIndex == optionGroups.size()) {
            Stock stock = new Stock(0);
            List<StockOption> stockOptions = selected.stream().map(id -> new StockOption(stock, id)).collect(Collectors.toList());
            stock.addOptions(stockOptions);
            list.add(stock);
            return;
        }

        OptionGroup optionGroup = optionGroups.get(currentIndex);
        for (Option option : optionGroup.getOptions()) {
            selected.add(option.getId());
            selectOptionsRecursive(optionGroups, selected, currentIndex + 1, list);
            selected.remove(selected.size() - 1);
        }
    }
}
