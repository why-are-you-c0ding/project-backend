package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.CreateAddressRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderOptionGroupRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderOptionRequest;


import java.util.Arrays;
import java.util.List;

public class CreateOrderRequestFactory {

    public static List<CreateOrderRequest> createSuccessCase(){

        CreateOrderOptionRequest option1 = new CreateOrderOptionRequest("white", 1000);
        CreateOrderOptionRequest option4 = new CreateOrderOptionRequest("large", 4000);

        CreateOrderOptionGroupRequest color = new CreateOrderOptionGroupRequest("color", option1);
        CreateOrderOptionGroupRequest sizee = new CreateOrderOptionGroupRequest("sizee", option4);

        CreateAddressRequest address = new CreateAddressRequest("서울특별시 멋진구 멋진동", "멋진아파트 111동 111호", "01099");

        return Arrays.asList(new CreateOrderRequest(1L, "멋쟁이 옷", 3, 40000,Arrays.asList(color, sizee), address));
    }
}



