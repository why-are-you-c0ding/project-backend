package wayc.backend.unit.member.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import wayc.backend.docs.SpringRestDocsUtils;
import wayc.backend.factory.member.dto.CreateMemberResponseDtoFactory;
import wayc.backend.factory.member.dto.PostMemberRequestDtoFactory;

import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;

import wayc.backend.member.presentation.dto.request.PostMemberRequestDto;
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
    @DisplayName("멤버 생성 성공 컨트롤러 단위 테스트")
    void create_member() throws Exception {
        //given
        PostMemberRequestDto req = PostMemberRequestDtoFactory.createSuccessCaseDto();
        CreateMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.createMember(Mockito.any(CreateMemberRequestDto.class))).willReturn(res);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.nickName").value("nickName"))
                .andDo(print())
                .andDo(document("create_member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("nickName").type(STRING).description("닉네임"),
                                        fieldWithPath("email").type(STRING).description("이메일"),
                                        fieldWithPath("loginId").type(STRING).description("로그인아이디"),
                                        fieldWithPath("password").type(STRING).description("비밀번호"),
                                        fieldWithPath("checkPassword").type(STRING).description("비밀번호 확인"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(NUMBER).description("멤버 id"),
                                        fieldWithPath("nickName").type(STRING).description("닉네임"),
                                        fieldWithPath("email").type(STRING).description("이메일"),
                                        fieldWithPath("loginId").type(STRING).description("로그인아이디"),
                                        fieldWithPath("age").type(NUMBER).description("나이")
                                )
                        ));
    }
}
