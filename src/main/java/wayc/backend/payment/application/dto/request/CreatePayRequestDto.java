package wayc.backend.payment.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreatePayRequestDto {


    @NotNull
    private Long orderId;

    @NotNull
    private Integer pay;

    public CreatePayRequestDto(Long orderId, Integer pay) {
        this.orderId = orderId;
        this.pay = pay;
    }

    public static List<CreatePayRequestDto> from(List<Long> idList, List<Integer> priceList) {
        List<CreatePayRequestDto> result = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            result.add(new CreatePayRequestDto(idList.get(i), priceList.get(i)));
        }
        return result;
    }

}
