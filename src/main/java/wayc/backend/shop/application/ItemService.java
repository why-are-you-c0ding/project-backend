package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.shop.NotExistsItemException;
import wayc.backend.exception.shop.NotExistsShopException;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemsResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowTotalItemResponseDto;
import wayc.backend.shop.infrastructure.ItemQueryRepository;
import wayc.backend.shop.infrastructure.ItemRepository;
import wayc.backend.shop.infrastructure.ShopRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = false)
    public CreateItemResponseDto create(Long ownerId, CreateItemRequestDto dto){
        Shop shop = shopRepository.findByOwnerIdAndStatus(ownerId)
                .orElseThrow(NotExistsShopException::new);
        Item item = itemMapper.mapFrom(dto, shop);
        itemRepository.save(item);
        return new CreateItemResponseDto(item.getId());
    }

    public ShowItemResponseDto showItem(Long itemId) {
        Item item = itemRepository.findItemByItemId(itemId).orElseThrow(NotExistsItemException::new);
        return ShowItemResponseDto.from(item);
    }

    public ShowTotalItemResponseDto showItems(Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Item> pagingResult = itemRepository.findItemsPagingByStatus(paging);
        List<ShowItemsResponseDto> result = pagingResult
                .stream()
                .map(item -> ShowItemsResponseDto.of(item))
                .collect(Collectors.toList());
        return new ShowTotalItemResponseDto(pagingResult.isLast(), result);
    }

    public ShowTotalItemResponseDto showSellerItems(Long ownerId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Item> pagingResult = itemRepository.findItemPagingByShopOwnerId(ownerId, paging);
        List<ShowItemsResponseDto> result = pagingResult.stream()
                .map(item -> ShowItemsResponseDto.of(item))
                .collect(Collectors.toList());
        return new ShowTotalItemResponseDto(pagingResult.isLast(), result);
    }

    public List<ShowItemsResponseDto> showRecommendedItem(List<String> names) {
        List<Item> items = itemQueryRepository.findRecommendedItem(names);
        return items.stream()
                .map(item -> ShowItemsResponseDto.of(item))
                .collect(Collectors.toList());
    }
}
