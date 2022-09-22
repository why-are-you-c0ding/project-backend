package wayc.backend.unit;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wayc.backend.common.config.WebMvcConfig;
import wayc.backend.common.image.ImageController;
import wayc.backend.common.image.ImageService;
import wayc.backend.exception.ExceptionExampleController;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.presentation.MemberController;
import wayc.backend.security.SecurityConfig;
import wayc.backend.security.jwt.JwtProvider;
import wayc.backend.shop.application.ItemService;
import wayc.backend.shop.application.ShopService;
import wayc.backend.shop.presentation.ItemController;
import wayc.backend.shop.presentation.ShopController;
import wayc.backend.verification.dataaccess.EmailRedisRepository;
import wayc.backend.verification.presentation.VerificationController;
import wayc.backend.verification.application.EmailService;
import wayc.backend.verification.application.VerificationService;

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
        ShopController.class
})
@MockBean(JpaMetamodelMappingContext.class) //JPA 설정을 못하므로 오류가 발생. 따라서 해당 애노테이션을 넣는다.
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "waycabvav.shop", uriPort = 443)
//@ExtendWith(RestDocumentationExtension.class)
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


//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
}
