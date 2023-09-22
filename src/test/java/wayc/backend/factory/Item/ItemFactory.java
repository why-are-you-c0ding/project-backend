package wayc.backend.factory.Item;

import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.Shop;

import java.util.List;

public class ItemFactory {

    private ItemFactory(){};

    public static Item createItem(Shop shop){
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        return new ItemMapper().mapOf(dto, shop);
    }

    public static Item createMacBook(){
        return Item.builder()
                .name("맥북")
                .imageUrl("imageurl")
                .category("IT")
                .optionGroups(
                        List.of(
                                new OptionGroup(
                                        List.of(
                                                new Option("256GB", 0),
                                                new Option("512GB", 80000),
                                                new Option("1024GB", 160000)
                                        ) ,
                                        "SSD"
                                ),
                                new OptionGroup(
                                        List.of(
                                                new Option("8GB", 0),
                                                new Option("16GB", 80000),
                                                new Option("32GB", 160000)
                                        ) ,
                                        "RAM"
                                )
                        )
                )
                .build();
    }

    public static Item createWithShop(Shop shop) {
        return Item.builder()
                .name("맥북")
                .price(1200000)
                .information("info")
                .shop(shop)
                .imageUrl("imageurl")
                .category("IT")
                .optionGroups(
                        List.of(
                                new OptionGroup(
                                        List.of(
                                                new Option("256GB", 0),
                                                new Option("512GB", 80000),
                                                new Option("1024GB", 160000)
                                        ) ,
                                        "SSD"
                                ),
                                new OptionGroup(
                                        List.of(
                                                new Option("8GB", 0),
                                                new Option("16GB", 80000),
                                                new Option("32GB", 160000)
                                        ) ,
                                        "RAM"
                                )
                        )
                )
                .build();
    }
}
