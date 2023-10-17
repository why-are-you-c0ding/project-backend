package wayc.backend.unit.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.test.web.servlet.MockMvc;

import wayc.backend.cart.application.CartProvider;
import wayc.backend.cart.application.CartService;
import wayc.backend.cart.presentation.CartController;
import wayc.backend.security.AppProperties;
import wayc.backend.config.SecurityConfig;
import wayc.backend.config.WebMvcConfig;
import wayc.backend.common.image.ImageController;
import wayc.backend.common.image.ImageService;
import wayc.backend.member.application.EmailService;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.presentation.EmailController;
import wayc.backend.member.presentation.MemberController;
import wayc.backend.order.application.OrderProvider;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.presentation.OrderController;
import wayc.backend.pay.application.PayService;
import wayc.backend.shop.application.provider.ItemProvider;
import wayc.backend.shop.application.provider.OptionGroupProvider;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.application.service.ShopService;
import wayc.backend.shop.presentation.ItemController;
import wayc.backend.member.infrastructure.SendEmailServiceImpl;
import wayc.backend.stock.application.provider.StockProvider;
import wayc.backend.stock.application.service.StockService;
import wayc.backend.stock.presentation.StockController;
//import wayc.backend.stock.presentation.StockController;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Import({
        SecurityConfig.class,
        WebMvcConfig.class,
        AppProperties.class
})
@WebMvcTest(controllers = {
        MemberController.class,
        ImageController.class,
        ItemController.class,
//        ShopController.class,
        StockController.class,
        CartController.class,
        OrderController.class,
        EmailController.class
})
@MockBean(JpaMetamodelMappingContext.class) //JPA 설정을 못하므로 오류가 발생. 따라서 해당 애노테이션을 넣는다.
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "wayc.store", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected MemberRepository memberRepository;

    @MockBean
    protected SendEmailServiceImpl sendEmailService;

    @MockBean
    protected ImageService imageService;

    @MockBean
    protected ItemService itemService;

    @MockBean
    protected ShopService shopService;

    @MockBean
    protected OptionGroupProvider optionGroupSpecificationService;

    @MockBean
    protected CartService cartService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected PayService payService;

    @MockBean
    protected EmailService emailService;

    @MockBean
    protected ItemProvider itemProvider;

    @MockBean
    protected CartProvider cartProvider;

    @MockBean
    protected OrderProvider orderProvider;

    @MockBean
    protected StockProvider stockProvider;

    @MockBean
    protected StockService stockService;

    @MockBean
    protected FindByIndexNameSessionRepository findByIndexNameSessionRepository;
}

//https://www.baeldung.com/spring-rest-docs