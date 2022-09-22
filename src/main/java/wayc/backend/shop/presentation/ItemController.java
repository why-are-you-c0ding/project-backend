package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.shop.presentation.dto.request.PostOptionGroupRequestDto;
import wayc.backend.shop.presentation.dto.request.PostOptionRequestDto;

import java.security.Provider;
import java.util.Arrays;


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

//    @GetMapping
//    public Object test(){
//        PostOptionRequestDto sss = new PostOptionRequestDto("sss", 1);
//        PostOptionRequestDto sss1 = new PostOptionRequestDto("sss", 1);
//        PostOptionRequestDto sss2 = new PostOptionRequestDto("sss", 1);
//        PostOptionRequestDto sss3 = new PostOptionRequestDto("sss", 1);
//
//        PostOptionGroupRequestDto dto = new PostOptionGroupRequestDto(Arrays.asList(sss, sss1));
//        PostOptionGroupRequestDto dto2 = new PostOptionGroupRequestDto(Arrays.asList(sss2, sss3));
//
//       return  new PostItemRequestDto("ss",Arrays.asList(dto, dto2));
//
//
//    }
}



//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//https://www.baeldung.com/get-user-in-spring-security
