package wayc.backend.factory.order;

import wayc.backend.order.application.dto.request.CreateAddressRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionGroupRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderOptionRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {

    private OrderFactory(){}

    public static OrderLineItem create(Long itemId){
        return OrderLineItem.builder()
                .itemId(itemId)
                .orderStatus(OrderStatus.ONGOING)
                .count(3)
                .name("맥북")
                .payment(6000)
                .orderingMemberId(1L)
                .address(new Address("서울", "어딘가 디테일한 주소", "00000"))
                .orderOptionGroups(List.of(
                        new OrderOptionGroup(new OrderOption("16GB", 80000), "RAM"),
                        new OrderOptionGroup(new OrderOption("512GB", 80000), "SSD")
                ))
                .build();
    }

    public static List<CreateOrderRequestDto> createSuccessCaseMackBookDto(){
        return List.of(
                new CreateOrderRequestDto(
                        1L,
                        "맥북",
                        3,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                      new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        ),
                        new CreateAddressRequestDto("major", "detail", "111111"),
                        1000000
                )
        );
    }


    public static List<CreateOrderRequestDto> createFailCase1CaseMackBookDto(){
        return List.of(
                new CreateOrderRequestDto(
                        1L,
                        "맥북",
                        3,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSO",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        ),
                        new CreateAddressRequestDto("major", "detail", "111111"),
                        1000000
                )
        );
    }

    public static List<CreateOrderRequestDto> createFailCase2CaseMackBookDto(){
        return List.of(
                new CreateOrderRequestDto(
                        1L,
                        "맥북1",
                        3,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("64GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        ),
                        new CreateAddressRequestDto("major", "detail", "111111"),
                        1000000
                )
        );
    }

    public static List<CreateOrderRequestDto> createFailCase3CaseMackBookDto(){
        return List.of(
                new CreateOrderRequestDto(
                        1L,
                        "맥북",
                        3,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("64GB", 240000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        ),
                        new CreateAddressRequestDto("major", "detail", "111111"),
                        1000000
                )
        );
    }

    public static List<CreateOrderRequestDto> createServiceDto(){
        return CreateOrderRequestFactory.createSuccessCase()
                .stream()
                .map(dto -> dto.toServiceDto())
                .collect(Collectors.toList());
    }

    public static List<CreateOrderRequestDto> createSuccessCaseMackBookDtoWithId(Long itemId){
        return List.of(
                new CreateOrderRequestDto(
                        itemId,
                        "맥북",
                        3,
                        List.of(
                                new CreateOrderOptionGroupRequestDto(
                                        "RAM",
                                        new CreateOrderOptionRequestDto("16GB", 80000)
                                ),
                                new CreateOrderOptionGroupRequestDto(
                                        "SSD",
                                        new CreateOrderOptionRequestDto("512GB", 80000)
                                )
                        ),
                        new CreateAddressRequestDto("major", "detail", "111111"),
                        1000000
                )
        );
    }
}
