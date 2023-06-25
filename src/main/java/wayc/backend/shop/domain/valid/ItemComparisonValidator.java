package wayc.backend.shop.domain.valid;

import org.springframework.stereotype.Component;

import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.exception.NotExistsItemException;

import java.util.List;

@Component
public class ItemComparisonValidator <T extends ItemComparator> {

    private final ItemRepository itemRepository;

    public ItemComparisonValidator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void validate(T compared) {
        Item item = itemRepository.findItemByItemId(compared.getItemId()).orElseThrow(NotExistsItemException::new);

        /**
         * 상품 검증 로직
         */

        if(!item.getName().equals(compared.getName())){
            throw new IllegalArgumentException("상품 이름이 변경되었습니다.");
        }

        for (OptionGroup optionGroup : item.getOptionGroups()) {
            validateOptionGroup(optionGroup, compared.getComparisonOrderOptionGroups());
        }

        /**
         * TODO 재고 검증 로직 및 감소
         */
    }


    private void validateOptionGroup(OptionGroup optionGroup, List<OptionGroupComparator> OptionGroupComparators) {
        for (OptionGroupComparator optionGroupComparator : OptionGroupComparators) {
            if(optionGroup.isSatisfiedBy(optionGroupComparator.convertToOptionGroupValidator())){
                return;
            }
        }
        throw new IllegalArgumentException("상품 옵션이 변경됐습니다.");
    }
}
