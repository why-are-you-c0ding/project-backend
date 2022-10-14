package wayc.backend.order.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderLineItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostOrderResponseDto {

    private String message;

    public PostOrderResponseDto() {
        this.message = "주문 생성에 성공하셨습니다.";
    }
}

