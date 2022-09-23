package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.shop.NotExistsItemException;
import wayc.backend.exception.shop.NotExistsShopException;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.item.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.item.ShowItemResponseDto;
import wayc.backend.shop.dataaccess.ItemRepository;
import wayc.backend.shop.dataaccess.ShopRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

@Service
@RequiredArgsConstructor
public class ItemService {

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

    public ShowItemResponseDto get(Long itemId) {
        Item item = itemRepository.findByIdAndStatus(itemId).orElseThrow(NotExistsItemException::new);
        return new ShowItemResponseDto(item);
    }
}
