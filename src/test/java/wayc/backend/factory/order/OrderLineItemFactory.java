package wayc.backend.factory.order;

import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLineItemFactory {

    private OrderLineItemFactory(){}

    public static OrderLineItem create(Long itemId){
        return OrderLineItem.builder()
                .itemId(itemId)
                .orderLineItemStatus(OrderLineItemStatus.ORDER_ACCEPTED)
                .count(3)
                .name("맥북")
                .payment(6000)
                .orderOptionGroups(List.of(
                        new OrderOptionGroup(new OrderOption("16GB", 80000), "RAM"),
                        new OrderOptionGroup(new OrderOption("512GB", 80000), "SSD")
                ))
                .build();
    }

    public static List<CreateOrderLineItemRequestDto> createSuccessCaseMackBookDto(){
        return List.of(
                new CreateOrderLineItemRequestDto(
                        1L,
                        "맥북",
                        3,
                        1000000,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                      new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        )
                )
        );
    }


    public static List<CreateOrderLineItemRequestDto> createFailCase1CaseMackBookDto(){
        return List.of(
                new CreateOrderLineItemRequestDto(
                        1L,
                        "맥북",
                        3,
                        1000000,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSO",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        )
                )
        );
    }

    public static List<CreateOrderLineItemRequestDto> createFailCase2CaseMackBookDto(){
        return List.of(
                new CreateOrderLineItemRequestDto(
                        1L,
                        "맥북1",
                        3,
                        1000000,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("64GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        )
                )
        );
    }

    public static List<CreateOrderLineItemRequestDto> createFailCase3CaseMackBookDto(){
        return List.of(
                new CreateOrderLineItemRequestDto(
                        1L,
                        "맥북",
                        3,
                        1000000,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("64GB", 240000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        )
                )
        );
    }

    public static List<CreateOrderLineItemRequestDto> createServiceDto(){
        return CreateOrderLineItemRequestFactory.createSuccessCase()
                .stream()
                .map(dto -> dto.toServiceDto())
                .collect(Collectors.toList());
    }

    public static List<CreateOrderLineItemRequestDto> createSuccessCaseMackBookDtoWithId(Long itemId){
        return List.of(
                new CreateOrderLineItemRequestDto(
                        itemId,
                        "맥북",
                        3,
                        1000000,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        )
                )
        );
    }
}
