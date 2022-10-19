package wayc.backend.unit.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.order.PostOrderRequestDtoFactory;
import wayc.backend.factory.order.ShowTotalOrderResponseDtoFactory;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.unit.ControllerTest;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

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
                                        subsectionWithPath("[].orderOptionGroups[].orderOption.price").type(NUMBER).description("구매하는 상품 옵션 그룹의 옵션 가격")                             ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("주문 생성 성공 메시지")
                                )
                        ));
    }


    @Test
    @DisplayName("소비자 주문 조회 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_customer_order() throws Exception {
        //given
        ShowTotalOrderResponseDto res = ShowTotalOrderResponseDtoFactory.createSuccessCase();
        given(orderService.showCustomerOrders(Mockito.any(Long.class), Mockito.any(Integer.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/orders/customers?page=0")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_customer_orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("가져올 페이지 리스트의 인덱스")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("orders").type(ARRAY).description("소비자의 주문 배열"),
                                subsectionWithPath("orders[].itemImageUrl").type(STRING).description("주문한 상품의 이미지"),
                                subsectionWithPath("orders[].shopName").type(STRING).description("주문한 상품의 가게 이름"),
                                subsectionWithPath("orders[].itemName").type(STRING).description("주문한 상품의 이름"),
                                subsectionWithPath("orders[].count").type(NUMBER).description("주문한 상품의 개수"),
                                subsectionWithPath("orders[].shopId").type(NUMBER).description("주문한 상품의 가게 id"),
                                subsectionWithPath("orders[].itemId").type(NUMBER).description("주문한 상품의 id"),
                                subsectionWithPath("orders[].orderId").type(NUMBER).description("주문 id"),
                                subsectionWithPath("orders[].orderOptionGroups[].name").type(STRING).description("주문한 상품의 옵션 그룹 이름"),
                                subsectionWithPath("orders[].orderOptionGroups[].option").type(OBJECT).description("주문한 상품의 옵션 그룹의 옵션"),
                                subsectionWithPath("orders[].orderOptionGroups[].option.name").type(STRING).description("주문한 상품의 옵션 그룹의 옵션 이름")
                                )
                ));
    }
}
