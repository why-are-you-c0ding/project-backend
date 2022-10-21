package wayc.backend.pay.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreatePayRequestDto {

    @NotNull
    private Integer price;

    @NotNull
    private Long orderId;

    public CreatePayRequestDto(Integer price, Long orderId) {
        this.price = price;
        this.orderId = orderId;
    }

    public static List<CreatePayRequestDto> from(List<Long> idList, List<Integer> priceList) {
        List<CreatePayRequestDto> result = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            result.add(new CreatePayRequestDto(priceList.get(i), idList.get(i) ));
        }

        return result;
    }

}
