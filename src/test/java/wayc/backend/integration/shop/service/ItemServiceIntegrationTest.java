package wayc.backend.integration.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.unit.application.UnitTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class ItemServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;


    /**
     * @Async는 달려도 동작한다.
     * EventListener도 동작한다.
     * TransactionalEventListener(AFTER COMMIT)은 통합테스트에서 동작하지 않았다.
     * TransactionalEventListener(BEFORE COMMIT)은 통합테스트에서 동작하지 않았다.
     *
     * TransactionalEventListener은 동작하지 않는 것 같다.
     *
     */
    @Test
    void createItem(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        shopRepository.save(new Shop(1L, "shop"));

        //when
        itemService.registerItem(1L, dto);

        //then
        Assertions.assertThat(itemRepository.findAll().size()).isEqualTo(1);
    }
}
