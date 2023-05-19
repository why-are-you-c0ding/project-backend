package wayc.backend.cart.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.order.domain.OrderOptionGroup;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.exception.NotExistsItemException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartValidator {

    private final ItemRepository itemRepository;

    void validate(CartLineItem lineItem){
        Item item = itemRepository.findItemByItemId(lineItem.getItemId()).orElseThrow(NotExistsItemException::new);

        if(!item.getName().equals(lineItem.getName())){
            throw new IllegalArgumentException("상품 이름이 변경되었습니다.");
        }

        for (OptionGroup optionGroup : item.getOptionGroups()) {
            validateOrderOptionGroup(optionGroup, lineItem.getCartOptionGroups());
        }
    }

    private void validateOrderOptionGroup(OptionGroup optionGroup, List<CartOptionGroup> orderOptionGroups) {
        for (CartOptionGroup cartOptionGroup : orderOptionGroups) {
            if(optionGroup.isSatisfiedBy(cartOptionGroup.convertToOptionGroupValidator())){
                return;
            }
        }
        throw new IllegalArgumentException("상품 옵션이 변경됐습니다.");
    }

}
