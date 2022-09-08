package wayc.backend.unit.verification.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import wayc.backend.unit.ControllerTest;
import wayc.backend.verification.presentation.dto.request.PostSendEmailRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyEmailRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyNickNameRequestDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class EmailControllerTest extends ControllerTest {

    @Test
    @DisplayName("이메일 검증 송신 테스트")
    void send_email() throws Exception {

        //given
        PostSendEmailRequestDto req = new PostSendEmailRequestDto("123@gmail.com");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("send_verification_email",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("receiveEmail").type(STRING).description("인증을 진행할 유저의 이메일")
                        ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("이메일 발송에 성공했습니다.")
                                )
                        ));
    }

    @Test
    @DisplayName("이메일 키 검증  테스트")
    void verify_email() throws Exception {

        //given
        PostVerifyEmailRequestDto req = new PostVerifyEmailRequestDto("123@gmail.com", "authkey");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/verification/email/auth-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("verify_email_key",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("email").type(STRING).description("인증을 진행할 유저의 이메일"),
                                fieldWithPath("authKey").type(STRING).description("이메일로 받은 인증 키")

                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("검증에 성공했습니다.")
                        )
                ));
    }
}
