package wayc.backend.unit.presentation.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.factory.member.dto.CreateMemberResponseDtoFactory;
import wayc.backend.factory.member.dto.RegisterMemberRequestFactory;

import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;

import wayc.backend.member.presentation.dto.request.RegisterConsumerRequest;
import wayc.backend.member.presentation.dto.request.RegisterSellerRequest;
import wayc.backend.member.presentation.dto.request.ValidateLoginIdRequest;
import wayc.backend.member.presentation.dto.request.ValidateNickNameRequest;
import wayc.backend.unit.presentation.ControllerTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.*;

public class MemberControllerTest extends ControllerTest {

    @Test
    @DisplayName("소비자 멤버 생성 성공 컨트롤러 단위 테스트")
    void create_consumer() throws Exception {
        //given
        RegisterConsumerRequest req = RegisterMemberRequestFactory.createConsumerSuccessCaseDto();
        RegisterMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.registerMember(Mockito.any(RegisterConsumerRequestDto.class))).willReturn(res);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/members/consumers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("create_consumer",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("nickName").type(STRING).description("닉네임"),
                                        fieldWithPath("email").type(STRING).description("이메일"),
                                        fieldWithPath("loginId").type(STRING).description("로그인아이디"),
                                        fieldWithPath("password").type(STRING).description("비밀번호"),
                                        fieldWithPath("checkPassword").type(STRING).description("비밀번호 확인"),
                                        fieldWithPath("age").type(NUMBER).description("나이"),
                                        fieldWithPath("authKey").type(STRING).description("이메일 인증 키")
                                ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("성공 메시지")
                                )
                        ));
    }

    @Test
    @DisplayName("판매자 멤버 생성 성공 컨트롤러 단위 테스트")
    void create_seller() throws Exception {
        //given
        RegisterSellerRequest req = RegisterMemberRequestFactory.createSellerSuccessCaseDto();
        RegisterMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.registerMember(Mockito.any(RegisterSellerRequestDto.class))).willReturn(res);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/members/sellers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("create_seller",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("nickName").type(STRING).description("닉네임"),
                                fieldWithPath("email").type(STRING).description("이메일"),
                                fieldWithPath("loginId").type(STRING).description("로그인아이디"),
                                fieldWithPath("password").type(STRING).description("비밀번호"),
                                fieldWithPath("checkPassword").type(STRING).description("비밀번호 확인"),
                                fieldWithPath("age").type(NUMBER).description("나이"),
                                fieldWithPath("authKey").type(STRING).description("이메일 인증 키")
                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("성공 메시지")
                        )
                ));
    }

    @Test
    @DisplayName("로그인 아이디 중복 확인 테스트")
    void verify_login_id() throws Exception {

        //given
        ValidateLoginIdRequest req = new ValidateLoginIdRequest("loginId");

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
        ValidateNickNameRequest req = new ValidateNickNameRequest("nickName");

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
