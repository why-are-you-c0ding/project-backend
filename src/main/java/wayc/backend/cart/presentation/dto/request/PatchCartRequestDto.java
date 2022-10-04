//package wayc.backend.cart.presentation.dto.request;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import wayc.backend.cart.application.dto.request.UpdateCartRequestDto;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotEmpty;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@NoArgsConstructor
//@Getter
//public class PatchCartRequestDto {
//
//    @Valid
//    @NotEmpty
//    private List<PostCartLineItemRequestDto> cartLineItems = new ArrayList<>();
//
//    public PatchCartRequestDto(List<PostCartLineItemRequestDto> cartLineItems) {
//        this.cartLineItems = cartLineItems;
//    }
//
//    public UpdateCartRequestDto toServiceDto() {
//        return new UpdateCartRequestDto(
//                cartLineItems
//                        .stream()
//                        .map(PostCartLineItemRequestDto::toServiceDto)
//                        .collect(Collectors.toList())
//        );
//    }
//}
