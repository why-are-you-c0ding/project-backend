package wayc.backend.unit.verification.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import wayc.backend.factory.member.dto.CreateMemberResponseDtoFactory;
import wayc.backend.factory.member.dto.PostMemberRequestDtoFactory;
import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.dto.request.PostMemberRequestDto;
import wayc.backend.unit.ControllerTest;
import wayc.backend.verification.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyNickNameRequestDto;

import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class VerificationControllerTest extends ControllerTest {

    @Test
    @DisplayName("로그인 아이디 중복 확인 테스트")
    @WithMockUser
    void verify_login_id() throws Exception {

        //given
        PostVerifyLoginIdRequestDto req = new PostVerifyLoginIdRequestDto("loginId");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/login-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) //SecurityConfig 에서도 http.csrf().disable()해도 이걸 넣어야 한다ㅠㅠㅠ 뭐지..
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
    @WithMockUser
    void verify_nickName() throws Exception {

        //given
        PostVerifyNickNameRequestDto req = new PostVerifyNickNameRequestDto("nickName");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/nick-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())

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
