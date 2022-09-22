package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;



@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<CreateItemResponseDto> createItem(
            @AuthenticationPrincipal Long id,
            @Validated @RequestBody PostItemRequestDto request
    ){
        CreateItemResponseDto res = itemService.create(id, request.toServiceDto());
        return ResponseEntity.ok(res);
    }
}



//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//https://www.baeldung.com/get-user-in-spring-security
