package wayc.backend.unit;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wayc.backend.exception.ExceptionExampleController;
import wayc.backend.member.business.MemberService;
import wayc.backend.member.presentation.MemberController;

@WebMvcTest({
        MemberController.class,
        ExceptionExampleController.class
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

}
