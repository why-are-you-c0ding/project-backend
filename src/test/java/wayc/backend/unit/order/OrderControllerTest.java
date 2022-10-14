package wayc.backend.unit.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.PostItemRequestDtoFactory;
import wayc.backend.factory.Item.ShowItemResponseDtoFactory;
import wayc.backend.factory.Item.ShowItemsResponseDtoFactory;
import wayc.backend.factory.Item.ShowOptionGroupResponseDtoFactory;
import wayc.backend.factory.order.PostOrderRequestDtoFactory;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemsResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.unit.ControllerTest;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class OrderControllerTest extends ControllerTest {

    @Test
    @DisplayName("주문 생성 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_item() throws Exception {
        //given
        PostOrderRequestDto req = PostOrderRequestDtoFactory.createSuccessCase();
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
                                        fieldWithPath("shopId").type(NUMBER).description("상품을 판매하는 가게 id"),
                                        fieldWithPath("orderLineItems").type(ARRAY).description("구매하는 상품들"),
                                        subsectionWithPath("orderLineItems[].itemId").type(NUMBER).description("구매하는 상품 id"),
                                        subsectionWithPath("orderLineItems[].name").type(STRING).description("구매하는 상품 이름"),
                                        subsectionWithPath("orderLineItems[].count").type(NUMBER).description("구매하는 상품 개수"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].name").type(STRING).description("구매하는 상품 옵션 그룹의 이름"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOptions").type(ARRAY).description("구매하는 상품 옵션 그룹의 옵션들"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOptions[].name").type(STRING).description("구매하는 상품 옵션 그룹의 옵션 이름"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOptions[].price").type(NUMBER).description("구매하는 상품 옵션 그룹의 옵션 가격")
                                        ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("주문 생성 성공 메시지")
                                )
                        ));
    }
}
