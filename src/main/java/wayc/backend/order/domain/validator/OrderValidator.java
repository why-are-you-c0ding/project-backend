package wayc.backend.order.domain.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderOptionGroup;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.exception.NotExistsItemException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ItemRepository itemRepository;

    public void validate(Order order) {
        Item item = itemRepository.findItemByItemId(order.getItemId()).orElseThrow(NotExistsItemException::new);

        /**
         * 상품 검증 로직
         */

        if(!item.getName().equals(order.getName())){
            throw new IllegalArgumentException("상품 이름이 변경되었습니다.");
        }

        for (OptionGroup optionGroup : item.getOptionGroups()) {
            validateOrderOptionGroup(optionGroup, order.getOrderOptionGroups());
        }

        /**
         * TODO 재고 검증 로직 및 감소
         */
    }

    private void validateOrderOptionGroup(OptionGroup optionGroup, List<OrderOptionGroup> orderOptionGroups) {
        for (OrderOptionGroup orderOptionGroup : orderOptionGroups) {
            if(optionGroup.isSatisfiedBy(orderOptionGroup.convertToOptionGroupValidator())){
               return;
            }
        }
        throw new IllegalArgumentException("상품 옵션이 변경됐습니다.");
    }
}
