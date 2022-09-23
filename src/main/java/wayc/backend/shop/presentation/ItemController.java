package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.OptionGroupSpecificationService;
import wayc.backend.shop.application.dto.response.item.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.item.ShowItemResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.shop.presentation.dto.response.GetItemResponseDto;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final ItemService itemService;
    private final OptionGroupSpecificationService optionGroupSpecificationService;

    @PostMapping
    public ResponseEntity<CreateItemResponseDto> createItem(
            @AuthenticationPrincipal Long id,
            @Validated @RequestBody PostItemRequestDto request
    ){
        CreateItemResponseDto res = itemService.create(id, request.toServiceDto());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<GetItemResponseDto> showItem(
            @PathVariable Long itemId
    ){
        ShowItemResponseDto itemServiceRes = itemService.get(itemId);
        List<Object> objects = optionGroupSpecificationService.get(itemServiceRes.getOptionGroupSpecificationIdList());
        return ResponseEntity.ok(new GetItemResponseDto(itemServiceRes, objects));

    }
}



//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//https://www.baeldung.com/get-user-in-spring-security
