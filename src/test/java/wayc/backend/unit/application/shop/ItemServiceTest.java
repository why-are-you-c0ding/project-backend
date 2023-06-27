package wayc.backend.unit.application.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.presentation.dto.request.RegisterItemRequest;
import wayc.backend.unit.application.UnitTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class ItemServiceTest extends UnitTest {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Spy
    private ShopRepository shopRepository;

    @BeforeEach
    void beforeEach(){
        this.itemService = new ItemService(shopRepository, itemRepository, new ItemMapper());
    }

    @Test
    void createItem(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        given(shopRepository.findByOwnerIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(ShopFactory.create()));

        //when
        itemService.registerItem(1L, dto);

        //then
        BDDMockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any(Item.class));
    }
}
