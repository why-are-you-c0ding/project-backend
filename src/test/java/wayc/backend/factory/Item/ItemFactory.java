package wayc.backend.factory.Item;

import wayc.backend.factory.Item.dto.RegisterItemRequestDtoFactory;
import wayc.backend.shop.application.ItemMapper;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

public class ItemFactory {

    private ItemFactory(){};

    public static Item createItem(Shop shop){
        RegisterItemRequestDto dto = RegisterItemRequestDtoFactory.createSuccessCase().toServiceDto();
        return new ItemMapper().mapOf(dto, shop);
    }
}
