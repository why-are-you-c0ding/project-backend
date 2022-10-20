package wayc.backend.unit.pay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.pay.PostPayRequestDtoFactory;
import wayc.backend.pay.presentation.dto.request.PostPayRequestDto;
import wayc.backend.unit.ControllerTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class PayControllerTest extends ControllerTest {

    @Test
    @DisplayName("결제 생성 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_pay() throws Exception {
        //given
        List<PostPayRequestDto> req = PostPayRequestDtoFactory.createSuccessCase();
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/pays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("create_pay",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                subsectionWithPath("[].price").type(NUMBER).description("결제할 비용"),
                                subsectionWithPath("[].orderId").type(NUMBER).description("결제를 할 주문의 id")
                                ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("결제 생성 성공 메시지")
                        )
                ));
    }
}
