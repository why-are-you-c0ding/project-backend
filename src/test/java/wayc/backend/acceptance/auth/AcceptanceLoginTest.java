package wayc.backend.acceptance.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.common.redis.RedisService;
import wayc.backend.factory.member.dto.RegisterMemberRequestDtoFactory;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.security.local.LocalLoginRequest;

import java.time.Duration;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "waycabvav.shop", uriPort = 443)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AcceptanceLoginTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MemberService memberService;

    @Autowired
    private RedisService redisService;

    @BeforeEach
    void beforeEach(){
        redisService.set("123@gmail.com", "999999", Duration.ofSeconds(30));

        RegisterConsumerRequestDto dto = RegisterMemberRequestDtoFactory.createSuccessConsumerDto();
        memberService.registerMember(dto);
    }

    @Test
    void success_login_test() throws Exception {
        //given
        LocalLoginRequest req = new LocalLoginRequest("loginId", "1q2w3e4r!");
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/local/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("login",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("loginId").type(STRING).description("로그인아이디"),
                                fieldWithPath("password").type(STRING).description("비밀번호")
                        )
                ));
    }
}
