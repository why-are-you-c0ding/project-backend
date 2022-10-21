package wayc.backend.order.presentation.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostOrderResponseDto {

    private String message;

    public PostOrderResponseDto() {
        this.message = "주문 생성에 성공하셨습니다.";
    }
}

