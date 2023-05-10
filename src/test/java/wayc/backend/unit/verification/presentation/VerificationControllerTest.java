package wayc.backend.unit.verification.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.unit.ControllerTest;
import wayc.backend.member.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.member.presentation.dto.request.PostVerifyNickNameRequestDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class VerificationControllerTest extends ControllerTest {

    @Test
    @DisplayName("로그인 아이디 중복 확인 테스트")
    void verify_login_id() throws Exception {

        //given
        PostVerifyLoginIdRequestDto req = new PostVerifyLoginIdRequestDto("loginId");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/login-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("verify_login_id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("loginId").type(STRING).description("검증할 로그인 아이디")
                        ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("검증에 성공했습니다.")
                                )
                        ));
    }

    @Test
    @DisplayName("닉네임 중복 확인 테스트")
    void verify_nickName() throws Exception {

        //given
        PostVerifyNickNameRequestDto req = new PostVerifyNickNameRequestDto("nickName");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/nick-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("verify_nick_name",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("nickName").type(STRING).description("검증할 닉네임")
                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("검증에 성공했습니다.")
                        )
                ));
    }
}
