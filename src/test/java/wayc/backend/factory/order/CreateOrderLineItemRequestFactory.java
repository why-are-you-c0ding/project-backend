package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.*;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateOrderLineItemRequestFactory {

    public static List<CreateOrderLineItemRequest> createSuccessCase(){

        CreateOrderOptionRequest option1 = new CreateOrderOptionRequest("white", 1000);
        CreateOrderOptionRequest option4 = new CreateOrderOptionRequest("large", 4000);

        CreateOrderOptionGroupRequest color = new CreateOrderOptionGroupRequest("color", option1);
        CreateOrderOptionGroupRequest sizee = new CreateOrderOptionGroupRequest("sizee", option4);

        return Arrays.asList(new CreateOrderLineItemRequest(1L, "멋쟁이 옷", 3, 40000, Arrays.asList(color, sizee)));
    }

    public static List<CreateOrderLineItemRequest> createSuccessCase(List<OptionGroup> optionGroups){

        List<CreateOrderLineItemRequest> list  = new ArrayList<>();

        for (OptionGroup optionGroup : optionGroups) {
            Collections.shuffle(optionGroup.getOptions());
            Option option = optionGroup.getOptions().get(0);
            CreateOrderOptionRequest optionRequest = new CreateOrderOptionRequest(option.getName(), option.getPrice());
            list.add(
                    new CreateOrderLineItemRequest(

                    )
            );
        }

        return list;
    }

}



