package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.request.RegisterOptionGroupRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static java.util.stream.Collectors.*;

@NoArgsConstructor
@Getter
public class RegisterItemRequest {

    @Size(min = 4)
    private String itemName;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String information;

    @NotBlank
    private String category;

    @NotNull
    private Integer price;

    @Valid
    @NotEmpty
    private List<RegisterOptionGroupRequest> optionGroups;

    public RegisterItemRequestDto toServiceDto(){
        return new RegisterItemRequestDto(makeOptionGroups(), itemName, imageUrl, information, category, price);

    }

    private List<RegisterOptionGroupRequestDto> makeOptionGroups() {
        return optionGroups.stream()
                .map(optionGroup -> optionGroup.toServiceDto())
                .collect(toList());
    }

    public RegisterItemRequest(String itemName,
                               String imageUrl,
                               String information,
                               List<RegisterOptionGroupRequest> optionGroupRequests,
                               String category,
                               Integer price) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.optionGroups = optionGroupRequests;
        this.category = category;
        this.price = price;
    }
}
