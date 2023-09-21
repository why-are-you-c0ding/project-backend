package wayc.backend.order.domain.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderOptionGroup;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.valid.ItemComparisonValidator;
import wayc.backend.shop.exception.NotExistsItemException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ItemComparisonValidator<OrderLineItem> itemComparisonValidator;

    public void validate(Order order) {

        /**
         * 상품 검증 로직
         */
        if(order.getOrderLineItems().isEmpty()) {
            throw new IllegalArgumentException("주문 항목이 비어있다.");
        }

        /**
         * 등록된 상품과 주문하는 상품의 옵션이 맞는지 전부 검사
         */
        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            itemComparisonValidator.validate(orderLineItem);
        }


        /**
         * TODO 재고 검증 로직 및 감소
         *
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
