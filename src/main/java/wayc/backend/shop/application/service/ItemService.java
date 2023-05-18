package wayc.backend.shop.application.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.shop.exception.NotExistsShopException;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.response.RegisterItemResponseDto;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = false)
    public void registerItem(Long ownerId, RegisterItemRequestDto dto){
        Shop shop = shopRepository.findByOwnerIdAndStatus(ownerId)
                .orElseThrow(NotExistsShopException::new);
        Item item = itemMapper.mapOf(dto, shop);
        itemRepository.save(item);
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();
        System.out.println("현재 실행 중인 스레드 이름: " + threadName);
        item.registered();
    }
}
