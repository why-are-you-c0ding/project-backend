package wayc.backend.integration.shop.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.response.find.FindItemResponseDto;
import wayc.backend.shop.application.dto.response.find.FindPagingItemResponseDto;
import wayc.backend.shop.application.provider.ItemProvider;
import wayc.backend.shop.application.service.ItemMapper;

import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

import static org.assertj.core.api.Assertions.*;

public class  ItemProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private ItemProvider itemProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void findItem(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop shop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, shop));

        //then
        FindItemResponseDto res = itemProvider.findItem(item.getId());

        //then
        assertThat(res.getItemName()).isEqualTo("item1");
    }

    @Test
    void findItems(){
        RegisterItemRequestDto dto1 = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        RegisterItemRequestDto dto2 = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop shop = shopRepository.save(new Shop(1L, "shop"));
        itemRepository.save(new ItemMapper().mapOf(dto1, shop));
        itemRepository.save(new ItemMapper().mapOf(dto2, shop));

        FindPagingItemResponseDto res = itemProvider.findItems(0);

        assertThat(res.getItems().size()).isEqualTo(2);
        assertThat(res.isFinalPage()).isEqualTo(true);
    }

    @Test
    void findSellerItems(){
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop shop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, shop));

        FindPagingItemResponseDto res = itemProvider.findSellerItems(item.getShop().getOwner().getMemberId(), 0);

        assertThat(res.getItems().size()).isEqualTo(1);
        assertThat(res.isFinalPage()).isEqualTo(true);
    }

//    @Test
//    void searchItem(){
//        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
//        Shop shop = shopRepository.save(new Shop(1L, "shop"));
//        itemRepository.save(new ItemMapper().mapOf(dto, shop));
//
//        FindPagingItemResponseDto res = itemProvider.searchItem(0, "cloth");
//
//        assertThat(res.getItems().size()).isEqualTo(1);
//        assertThat(res.isFinalPage()).isEqualTo(true);
//    }
}
