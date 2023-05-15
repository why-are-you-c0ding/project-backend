package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.shop.exception.NotExistsShopException;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.response.RegisterItemResponseDto;
import wayc.backend.shop.infrastructure.ItemQueryRepositoryImpl;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemQueryRepositoryImpl itemQueryRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final OptionGroupService optionGroupService;

    @Transactional(readOnly = false)
    public RegisterItemResponseDto registerItem(Long ownerId, RegisterItemRequestDto dto){
        Shop shop = shopRepository.findByOwnerIdAndStatus(ownerId)
                .orElseThrow(NotExistsShopException::new);
        Item item = itemMapper.mapOf(dto, shop);
        itemRepository.save(item);
        return new RegisterItemResponseDto(item.getId());
    }


}
