package wayc.backend.unit.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.order.PostOrderRequestDtoFactory;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.unit.ControllerTest;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class OrderControllerTest extends ControllerTest {

    @Test
    @DisplayName("주문 생성 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_order() throws Exception {
        //given
        List<PostOrderRequestDto> req = PostOrderRequestDtoFactory.createSuccessCase();
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("create_order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("[].itemId").type(NUMBER).description("구매하는 상품 id"),
                                        fieldWithPath("[].name").type(STRING).description("구매하는 상품 이름"),
                                        fieldWithPath("[].count").type(NUMBER).description("구매하는 상품 개수"),
                                        fieldWithPath("[].address").type(OBJECT).description("주문하는 주소"),
                                        fieldWithPath("[].address.major").type(STRING).description("주문하는 대주소"),
                                        fieldWithPath("[].address.detail").type(STRING).description("주문하는 상세 주소"),
                                        subsectionWithPath("[].address.zipcode").type(STRING).description("도로명 번호"),
                                        subsectionWithPath("[].orderOptionGroups").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                        subsectionWithPath("[].orderOptionGroups[].name").type(STRING).description("구매하는 상품 옵션 그룹의 이름"),
                                        subsectionWithPath("[].orderOptionGroups[].orderOption").type(OBJECT).description("구매하는 상품 옵션 그룹의 옵션들"),
                                        subsectionWithPath("[].orderOptionGroups[].orderOption.name").type(STRING).description("구매하는 상품 옵션 그룹의 옵션 이름"),
                                        subsectionWithPath("[].orderOptionGroups[].orderOption.price").type(NUMBER).description("구매하는 상품 옵션 그룹의 옵션 가격")
                                ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("주문 생성 성공 메시지")
                                )
                        ));
    }
}
