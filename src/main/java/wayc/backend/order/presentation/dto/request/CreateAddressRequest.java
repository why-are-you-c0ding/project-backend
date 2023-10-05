package wayc.backend.order.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateAddressRequestDto;

@NoArgsConstructor
@Getter
public class CreateAddressRequest {

    private String major;
    private String detail;
    private String zipcode;

    public CreateAddressRequest(String major, String detail, String zipcode) {
        this.major = major;
        this.detail = detail;
        this.zipcode = zipcode;
    }

    public CreateAddressRequestDto toServiceDto(){
        return new CreateAddressRequestDto(major, detail, zipcode);
    }
}
