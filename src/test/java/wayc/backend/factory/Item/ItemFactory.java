package wayc.backend.factory.Item;

import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

public class ItemFactory {

    private ItemFactory(){};

    public static Item createItem(Shop shop){
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        return new ItemMapper().mapOf(dto, shop);
    }
}
