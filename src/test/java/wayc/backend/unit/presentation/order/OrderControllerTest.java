package wayc.backend.unit.presentation.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.factory.order.FindOrderResponseDtoFactory;
import wayc.backend.factory.order.FindPagingOrderResponseDtoFactory;
import wayc.backend.order.application.dto.response.FindOrderResponseDto;
import wayc.backend.order.application.dto.response.FindPagingOrderResponseDto;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.presentation.dto.request.UpdateOrderRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;
import wayc.backend.unit.presentation.ControllerTest;

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
        List<CreateOrderRequest> req = CreateOrderRequestFactory.createSuccessCase();
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
                                        fieldWithPath("[].price").type(NUMBER).description("주문의 결제 금액"),
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
        FindPagingOrderResponseDto res = FindPagingOrderResponseDtoFactory.createSuccessCaseForCustomer();
        given(orderProvider.findCustomerOrders(Mockito.any(Long.class), Mockito.any(Integer.class))).willReturn(res);

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
                                subsectionWithPath("orders[].price").type(NUMBER).description("주문 결제 금액"),
                                subsectionWithPath("orders[].shopId").type(NUMBER).description("주문한 상품의 가게 id"),
                                subsectionWithPath("orders[].itemId").type(NUMBER).description("주문한 상품의 id"),
                                subsectionWithPath("orders[].orderId").type(NUMBER).description("주문 id"),
                                subsectionWithPath("orders[].orderStatus").type(STRING).description("주문 진행 상태"),
                                subsectionWithPath("orders[].orderOptionGroups[].name").type(STRING).description("주문한 상품의 옵션 그룹 이름"),
                                subsectionWithPath("orders[].orderOptionGroups[].option").type(OBJECT).description("주문한 상품의 옵션 그룹의 옵션"),
                                subsectionWithPath("orders[].orderOptionGroups[].option.name").type(STRING).description("주문한 상품의 옵션 그룹의 옵션 이름")
                                )
                ));
    }


    @Test
    @DisplayName("판매자에게 들어온 모든 주문 조회 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_seller_order() throws Exception {
        //given
        FindPagingOrderResponseDto res = FindPagingOrderResponseDtoFactory.createSuccessCaseForSeller();
        given(orderProvider.findSellerOrders(Mockito.any(Long.class), Mockito.any(Integer.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/orders/sellers?page=0")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_seller_orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("가져올 페이지 리스트의 인덱스")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("orders").type(ARRAY).description("소비자의 주문 배열"),
                                subsectionWithPath("orders[].itemImageUrl").type(STRING).description("주문한 상품의 이미지"),
                                subsectionWithPath("orders[].itemName").type(STRING).description("주문한 상품의 이름"),
                                subsectionWithPath("orders[].count").type(NUMBER).description("주문한 상품의 개수"),
                                subsectionWithPath("orders[].itemId").type(NUMBER).description("주문한 상품의 id"),
                                subsectionWithPath("orders[].orderId").type(NUMBER).description("주문 id"),
                                subsectionWithPath("orders[].createdAt").type(STRING).description("주문 생성 날짜"),
                                subsectionWithPath("orders[].orderStatus").type(STRING).description("주문 진행 상태"),
                                subsectionWithPath("orders[].price").type(NUMBER).description("주문 결제 금액")
                        )
                ));
    }


    @Test
    @DisplayName("주문 조회 단건 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_order() throws Exception {
        //given
        FindOrderResponseDto res = FindOrderResponseDtoFactory.createSuccessCase();
        given(orderProvider.findOrder(Mockito.any(Long.class), Mockito.any(Long.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/orders/{orderId}",3)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                        parameterWithName("orderId").description("조회할 주문의 id")
                        ),

                        responseFields(
                                fieldWithPath("itemImageUrl").type(STRING).description("주문한 상품의 이미지"),
                                fieldWithPath("shopName").type(STRING).description("주문한 상품의 가게 이름"),
                                fieldWithPath("itemName").type(STRING).description("주문한 상품의 이름"),
                                fieldWithPath("count").type(NUMBER).description("주문한 상품의 개수"),
                                fieldWithPath("shopId").type(NUMBER).description("주문한 상품의 가게 id"),
                                fieldWithPath("itemId").type(NUMBER).description("주문한 상품의 id"),
                                fieldWithPath("orderId").type(NUMBER).description("주문 id"),
                                fieldWithPath("orderStatus").type(STRING).description("주문 진행 상태"),
                                fieldWithPath("address.major").type(STRING).description("주문 배송지 대주소"),
                                fieldWithPath("address.detail").type(STRING).description("주문 배송지 자세한 주소"),
                                fieldWithPath("address.zipcode").type(STRING).description("주문 배송지 도로 번호"),
                                fieldWithPath("orderOptionGroups").type(ARRAY).description("주문한 상품의 옵션 그룹"),
                                fieldWithPath("price").type(NUMBER).description("주문 결제 금액"),
                                subsectionWithPath("orderOptionGroups[].name").type(STRING).description("주문한 상품의 옵션 그룹 이름"),
                                subsectionWithPath("orderOptionGroups[].option").type(OBJECT).description("주문한 상품의 옵션 그룹 옵션"),
                                subsectionWithPath("orderOptionGroups[].option.name").type(STRING).description("주문한 상품의 옵션 그룹 옵션 이름")
                        )
                ));
    }



    @Test
    @DisplayName("주문 상태 수정 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void update_order() throws Exception {
        //given
        UpdateOrderRequest req = new UpdateOrderRequest(2L, 3L, OrderStatus.COMPLETED);
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("update_order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("itemId").type(NUMBER).description("주문 상품 id"),
                                fieldWithPath("orderId").type(NUMBER).description("수정할 주문 id"),
                                fieldWithPath("orderStatus").type(STRING).description("수정할 주문 상태 정보")
                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("주문 업데이트 성공 메시지")
                        )
                ));
    }
}
