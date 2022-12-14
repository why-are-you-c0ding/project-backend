package wayc.backend.unit;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import wayc.backend.cart.application.CartService;
import wayc.backend.cart.presentation.CartController;
import wayc.backend.common.config.WebMvcConfig;
import wayc.backend.common.image.ImageController;
import wayc.backend.common.image.ImageService;
import wayc.backend.exception.ExceptionExampleController;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.presentation.MemberController;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.presentation.OrderController;
import wayc.backend.pay.application.PayServiceImpl;
import wayc.backend.security.SecurityConfig;
import wayc.backend.security.jwt.JwtProvider;
import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.OptionGroupSpecificationService;
import wayc.backend.shop.application.ShopService;
import wayc.backend.shop.application.StockService;
import wayc.backend.shop.presentation.ItemController;
import wayc.backend.shop.presentation.ShopController;
import wayc.backend.shop.presentation.StockController;
import wayc.backend.verification.infrastructure.EmailRedisRepository;
import wayc.backend.verification.presentation.VerificationController;
import wayc.backend.verification.application.EmailService;
import wayc.backend.verification.application.VerificationService;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Import({
        SecurityConfig.class,
        WebMvcConfig.class
})
@WebMvcTest(controllers = {
        MemberController.class,
        ExceptionExampleController.class,
        VerificationController.class,
        ImageController.class,
        ItemController.class,
        ShopController.class,
        StockController.class,
        CartController.class,
        OrderController.class,
})
@MockBean(JpaMetamodelMappingContext.class) //JPA ????????? ???????????? ????????? ??????. ????????? ?????? ?????????????????? ?????????.
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "waycabvav.shop", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected VerificationService verificationService;

    @MockBean
    protected EmailService emailService;

    @MockBean
    protected EmailRedisRepository emailRedisRepository;

    @MockBean
    protected ImageService imageService;

    @MockBean
    protected JwtProvider jwtProvider;

    @MockBean
    protected ItemService itemService;

    @MockBean
    protected ShopService shopService;

    @MockBean
    protected OptionGroupSpecificationService optionGroupSpecificationService;

    @MockBean
    protected StockService stockService;

    @MockBean
    protected CartService cartService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected PayServiceImpl payService;

    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .build();
    }
}

//https://www.baeldung.com/spring-rest-docs