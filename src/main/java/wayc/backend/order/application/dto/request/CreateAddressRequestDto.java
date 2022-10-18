package wayc.backend.order.application.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateAddressRequestDto {
    private String major;
    private String detail;
    private String zipcode;

    public CreateAddressRequestDto(String major, String detail, String zipcode) {
        this.major = major;
        this.detail = detail;
        this.zipcode = zipcode;
    }
}
