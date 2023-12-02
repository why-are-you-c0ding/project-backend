package wayc.backend.payment.application.dto.response;

import lombok.Getter;

@Getter
public class CreatePayResponseDto {

    private final String message;

    public CreatePayResponseDto() {
        this.message = "결제 요청을 성공하셨습니다.";
    }
}
