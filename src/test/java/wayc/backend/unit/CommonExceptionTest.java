package wayc.backend.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class CommonExceptionTest extends ControllerTest {


//    @BeforeEach
//    void beforeEach(  //어떻게 이게 들어오는걸까??
//            WebApplicationContext webApplicationContext,
//            RestDocumentationContextProvider restDocumentationContextProvider
//    ){
//        super.setUp(webApplicationContext, restDocumentationContextProvider);
//    }

    @Test
    @DisplayName("예외 응답 확인 테스트")
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
}
