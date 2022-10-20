package wayc.backend.order.application.dto.response;


import lombok.Getter;

@Getter
public class UpdateOrderResponseDto {

    private String message;


    public UpdateOrderResponseDto() {
        this.message = "주문 업데이트를 성공하셨습니다.";
    }
}
