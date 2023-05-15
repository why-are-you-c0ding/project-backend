package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.shop.application.ItemProvider;
import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.dto.response.find.FindItemDto;
import wayc.backend.shop.application.dto.response.find.FindItemResponseDto;
import wayc.backend.shop.application.dto.response.find.FindPagingItemResponseDto;

import wayc.backend.shop.presentation.dto.request.RegisterItemRequest;

import static org.springframework.http.HttpStatus.*;


@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final ItemService itemService;
    private final ItemProvider itemProvider;

    @PostMapping
    public ResponseEntity registerItem(@AuthenticationPrincipal Long id,
                                       @Validated @RequestBody RegisterItemRequest request){
        return new ResponseEntity(itemService.registerItem(id, request.toServiceDto()), CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<FindItemResponseDto> findItem(@PathVariable Long itemId){
        FindItemResponseDto res = itemProvider.findItem(itemId);
        return new ResponseEntity(res, OK);
    }

    @GetMapping
    public ResponseEntity<FindPagingItemResponseDto> findItems(@RequestParam Integer page){
        return new ResponseEntity(itemProvider.findItems(page), OK);
    }

    @GetMapping("/sellers")
    public ResponseEntity<FindPagingItemResponseDto> findSellerItems(@AuthenticationPrincipal Long id,
                                                                     @RequestParam Integer page){
        FindPagingItemResponseDto res = itemProvider.findSellerItems(id, page);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    public ResponseEntity<FindPagingItemResponseDto> search(@RequestParam Integer page,
                                                            @RequestParam String keyword){
        FindPagingItemResponseDto res = itemProvider.searchItem(page, keyword);
        return ResponseEntity.ok(res);
    }
}
