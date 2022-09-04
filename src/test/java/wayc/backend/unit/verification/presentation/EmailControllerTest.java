package wayc.backend.unit.verification.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.unit.ControllerTest;
import wayc.backend.verification.presentation.dto.request.PostSendEmailRequestDto;
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
    void verify_send_email() throws Exception {

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
                .andDo(document("verify_send_email",
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
}
