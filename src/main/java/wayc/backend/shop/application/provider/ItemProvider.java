package wayc.backend.shop.application.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.shop.application.dto.response.find.*;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.exception.NotExistsItemException;
import wayc.backend.shop.infrastructure.ItemQueryRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemProvider {

    private final ItemQueryRepositoryImpl itemQueryRepository;
    private final ItemRepository itemRepository;
    private final OptionGroupProvider optionGroupService;

    public FindItemResponseDto findItem(Long itemId) {
        Item item = itemRepository.findItemByItemId(itemId).orElseThrow(NotExistsItemException::new);
        FindItemDto itemDto = FindItemDto.from(item);
        List<FindOptionGroupResponseDto> optionGroupDto = optionGroupService.findOptionGroups(itemDto.getOptionGroupIdList());
        return FindItemResponseDto.of(itemDto, optionGroupDto);
    }

    public FindPagingItemResponseDto findItems(Integer page) {
        PageRequest paging = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Item> pagingResult = itemRepository.findItemsPagingByStatus(paging);
        List<FindItemsResponseDto> result = pagingResult
                .stream()
                .map(item -> FindItemsResponseDto.of(item))
                .collect(Collectors.toList());
        return new FindPagingItemResponseDto(pagingResult.isLast(), result);
    }

    public FindPagingItemResponseDto findSellerItems(Long ownerId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Item> pagingResult = itemRepository.findItemPagingByShopOwnerId(ownerId, paging);
        List<FindItemsResponseDto> result = pagingResult.stream()
                .map(item -> FindItemsResponseDto.of(item))
                .collect(Collectors.toList());
        return new FindPagingItemResponseDto(pagingResult.isLast(), result);
    }

    public FindPagingItemResponseDto searchItem(Integer page, String searchKeyword) {

        List<FindItemsResponseDto> result = itemQueryRepository
                .findItemBySearchKeyword(page, searchKeyword)
                .stream()
                .map(item -> FindItemsResponseDto.of(item))
                .collect(Collectors.toList());

        if(result.size() > 20) {
            return new FindPagingItemResponseDto(false, result);
        }

        return new FindPagingItemResponseDto(true, result);
    }
}