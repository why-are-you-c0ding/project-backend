package wayc.backend.unit.presentation.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.member.presentation.dto.request.SendEmailRequest;
import wayc.backend.member.presentation.dto.request.ValidateEmailRequest;
import wayc.backend.unit.presentation.ControllerTest;

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
        SendEmailRequest req = new SendEmailRequest("123@gmail.com");

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/emails")
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
}
