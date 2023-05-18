package wayc.backend.integration.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.request.RegisterStockRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.application.service.StockService;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.Stock;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.OptionRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.presentation.dto.request.RegisterStockInfoRequest;
import wayc.backend.shop.presentation.dto.request.RegisterStockRequest;
import wayc.backend.unit.application.UnitTest;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

public class StockServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void createStock(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop findShop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, findShop));


        //when


        //then
        //stub(조회)는 구현을 깊이 테스트하는 것이므로 검증하지 않았다.

    }
}
