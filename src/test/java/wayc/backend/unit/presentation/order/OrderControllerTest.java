package wayc.backend.unit.presentation.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.order.CreateOrderLineItemRequestFactory;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.factory.order.query.FindOrderResponseDtoFactory;
import wayc.backend.factory.order.query.FindPagingOrderResponseDtoFactory;
import wayc.backend.order.domain.OrderLineItemStatus;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;
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
        CreateOrderRequest req = CreateOrderRequestFactory.createSuccessCase();
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("create_order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("orderLineItems[].itemId").type(NUMBER).description("구매하는 상품 id"),
                                        fieldWithPath("orderLineItems[].name").type(STRING).description("구매하는 상품 이름"),
                                        fieldWithPath("orderLineItems[].count").type(NUMBER).description("구매하는 상품 개수"),
                                        fieldWithPath("orderLineItems[].price").type(NUMBER).description("주문 상품의 결제 금액"),
                                        fieldWithPath("address").type(OBJECT).description("주문하는 주소"),
                                        fieldWithPath("address.major").type(STRING).description("주문하는 대주소"),
                                        fieldWithPath("address.detail").type(STRING).description("주문하는 상세 주소"),
                                        subsectionWithPath("address.zipcode").type(STRING).description("도로명 번호"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].name").type(STRING).description("구매하는 상품 옵션 그룹의 이름"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOption").type(OBJECT).description("구매하는 상품 옵션 그룹의 옵션들"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOption.name").type(STRING).description("구매하는 상품 옵션 그룹의 옵션 이름"),
                                        subsectionWithPath("orderLineItems[].orderOptionGroups[].orderOption.price").type(NUMBER).description("구매하는 상품 옵션 그룹의 옵션 가격"),
                                        subsectionWithPath("totalPayment").type(NUMBER).description("총 결제 가격")
                                ),
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
        given(orderProvider.findCustomerOrderLineItems(Mockito.any(Long.class), Mockito.any(Long.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/order-line-items/customers?lastLookUpOrderLineItemId=0")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("show_customer_orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("lastLookUpOrderLineItemId").description("이전에 조회한 주문 상품의 마지막 id")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("orderLineItems").type(ARRAY).description("소비자의 주문 배열"),
                                subsectionWithPath("orderLineItems[].itemImageUrl").type(STRING).description("주문한 상품의 이미지"),
                                subsectionWithPath("orderLineItems[].itemName").type(STRING).description("주문한 상품의 이름"),
                                subsectionWithPath("orderLineItems[].shopName").type(STRING).description("주문한 상품의 상점 이름"),
                                subsectionWithPath("orderLineItems[].shopId").type(NUMBER).description("주문한 상품의 상점 id"),
                                subsectionWithPath("orderLineItems[].count").type(NUMBER).description("주문한 상품의 개수"),
                                subsectionWithPath("orderLineItems[].itemId").type(NUMBER).description("주문한 상품의 id"),
                                subsectionWithPath("orderLineItems[].orderLineItemId").type(NUMBER).description("주문 상품 id"),
                                subsectionWithPath("orderLineItems[].orderStatus").type(STRING).description("주문 진행 상태"),
                                subsectionWithPath("orderLineItems[].price").type(NUMBER).description("주문 결제 금액"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[]").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[].optionGroupName").type(STRING).description("구매하는 상품 옵션 그룹 이름"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[].optionName").type(STRING).description("구매하는 상품 옵션 그룹에서 선택한 옵션 이름")
                                )
                ));
    }


    @Test
    @DisplayName("판매자에게 들어온 모든 주문 조회 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_seller_order() throws Exception {
        //given
        FindPagingOrderResponseDto res = FindPagingOrderResponseDtoFactory.createSuccessCaseForSeller();
        given(orderProvider.findSellerOrderLineItems(Mockito.any(Long.class), Mockito.any(Long.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/order-line-items/sellers?lastLookUpOrderLineItemId=0")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("show_seller_orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("lastLookUpOrderLineItemId").description("이전에 조회한 주문 상품의 마지막 id")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("orderLineItems").type(ARRAY).description("판매자의 주문 배열"),
                                subsectionWithPath("orderLineItems[].itemImageUrl").type(STRING).description("주문한 상품의 이미지"),
                                subsectionWithPath("orderLineItems[].itemName").type(STRING).description("주문한 상품의 이름"),
                                subsectionWithPath("orderLineItems[].shopName").type(STRING).description("주문한 상품의 상점 이름"),
                                subsectionWithPath("orderLineItems[].shopId").type(NUMBER).description("주문한 상품의 상점 id"),
                                subsectionWithPath("orderLineItems[].count").type(NUMBER).description("주문한 상품의 개수"),
                                subsectionWithPath("orderLineItems[].itemId").type(NUMBER).description("주문한 상품의 id"),
                                subsectionWithPath("orderLineItems[].orderLineItemId").type(NUMBER).description("주문 상품 id"),
                                subsectionWithPath("orderLineItems[].orderStatus").type(STRING).description("주문 진행 상태"),
                                subsectionWithPath("orderLineItems[].price").type(NUMBER).description("주문 결제 금액"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[]").type(ARRAY).description("구매하는 상품 옵션 그룹"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[].optionGroupName").type(STRING).description("구매하는 상품 옵션 그룹 이름"),
                                subsectionWithPath("orderLineItems[].orderOptionGroups[].optionName").type(STRING).description("구매하는 상품 옵션 그룹에서 선택한 옵션 이름")
                        )
                ));
    }


    @Test
    @DisplayName("주문 조회 단건 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void find_order() throws Exception {
        //given
        FindOrderResponseDto res = FindOrderResponseDtoFactory.createSuccessCase();
        given(orderProvider.findDetailOrderLineItem(Mockito.any(Long.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/order-line-items/{orderId}",3)
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
                                fieldWithPath("orderLineItemId").type(NUMBER).description("주문 상품의 id"),
                                fieldWithPath("orderStatus").type(STRING).description("주문 진행 상태"),
                                fieldWithPath("majorAddress").type(STRING).description("주문 배송지 대주소"),
                                fieldWithPath("detailAddress").type(STRING).description("주문 배송지 자세한 주소"),
                                fieldWithPath("zipcode").type(STRING).description("주문 배송지 도로 번호"),
                                fieldWithPath("orderOptionGroups").type(ARRAY).description("주문한 상품의 옵션 그룹"),
                                fieldWithPath("price").type(NUMBER).description("주문 결제 금액"),
                                subsectionWithPath("orderOptionGroups[].optionGroupName").type(STRING).description("주문한 상품의 옵션 그룹 옵션"),
                                subsectionWithPath("orderOptionGroups[].optionName").type(STRING).description("주문한 상품의 옵션 그룹에서 선택한 옵션 이름")
                        )
                ));
    }



    @Test
    @DisplayName("주문 상태 수정 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void update_order() throws Exception {
        //given
        UpdateOrderRequest req = new UpdateOrderRequest(2L, 3L, OrderLineItemStatus.PREPARING_FOR_DELIVERY);
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
