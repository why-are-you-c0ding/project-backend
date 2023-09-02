package wayc.backend.unit.presentation.common;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import wayc.backend.common.exception.GlobalExceptionHandler;
import wayc.backend.member.exception.NotExistsMemberException;
import wayc.backend.unit.presentation.ControllerTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;


@ContextConfiguration(classes = {GlobalExceptionHandlerTest.TestController.class, GlobalExceptionHandler.class})
@WebMvcTest(controllers = GlobalExceptionHandlerTest.TestController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "wayc.store", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("예외 응답 확인 테스트")
    @WithMockUser
    void exception_test() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.get("/exception/test")
                        .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andDo(document("exception_example",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("예외 메세지"),
                                fieldWithPath("errorCode").type(STRING).description("에러 코드"),
                                fieldWithPath("httpStatus").type(STRING).description("Http 상태 코드")
                        )
                ));
    }

    @RestController
    static class TestController {
        @GetMapping("/exception/test")
        public String executeBusinessException() {
            throw new NotExistsMemberException();
        }
    }
}
