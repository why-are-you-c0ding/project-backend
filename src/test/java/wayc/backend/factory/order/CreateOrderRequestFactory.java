package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.*;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateOrderRequestFactory {

    private CreateOrderRequestFactory() {}

    public static CreateOrderRequest createSuccessCase(){
        List<CreateOrderLineItemRequest> orderLineItems = CreateOrderLineItemRequestFactory.createSuccessCase();
        CreateAddressRequest address = CreateAddressRequestFactory.create();
        return new CreateOrderRequest(orderLineItems, address, 45000);
    }

    public static CreateOrderRequest createOrderRequest(List<Item> items) {
        CreateAddressRequest address = CreateAddressRequestFactory.create();
        List<CreateOrderLineItemRequest> list = new ArrayList<>();
        for (Item item : items) {
            List<CreateOrderOptionGroupRequest> list2 = new ArrayList<>();
            for (OptionGroup optionGroup : item.getOptionGroups()) {
                Option option = optionGroup.getOptions().get(0);
                CreateOrderOptionRequest optionRequest = new CreateOrderOptionRequest(option.getName(), option.getPrice());
                list2.add(new CreateOrderOptionGroupRequest(optionGroup.getName(), optionRequest));
            }
            list.add(new CreateOrderLineItemRequest(item.getId(), item.getName(), 2, item.getPrice(), list2));
        }

        return new CreateOrderRequest(list, address, 100000);
    }
}



