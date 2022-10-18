package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Address;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequestDto {

    private Long itemId;

    private String name;

    private Integer count;

    private List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto = new ArrayList<>();

    private CreateAddressRequestDto address;


    public CreateOrderRequestDto(Long itemId, String name, Integer count, List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto, CreateAddressRequestDto address) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroupsDto = orderOptionGroupsDto;
        this.address = address;
    }

    public Address toAddress(){
        return new Address(address.getMajor(), address.getDetail(), address.getZipcode());
    }
}
