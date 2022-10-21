package wayc.backend.unit.member.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.factory.member.dto.CreateMemberResponseDtoFactory;
import wayc.backend.factory.member.dto.PostMemberRequestDtoFactory;

import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;

import wayc.backend.member.presentation.dto.request.PostConsumerRequestDto;
import wayc.backend.member.presentation.dto.request.PostSellerRequestDto;
import wayc.backend.unit.ControllerTest;

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
        PostConsumerRequestDto req = PostMemberRequestDtoFactory.createConsumerSuccessCaseDto();
        CreateMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.createConsumer(Mockito.any(CreateConsumerRequestDto.class))).willReturn(res);

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
        PostSellerRequestDto req = PostMemberRequestDtoFactory.createSellerSuccessCaseDto();
        CreateMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.createSeller(Mockito.any(CreateSellerRequestDto.class))).willReturn(res);

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
}
