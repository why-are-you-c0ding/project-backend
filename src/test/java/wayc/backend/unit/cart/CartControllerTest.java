package wayc.backend.unit.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.cart.application.dto.request.CreateCartLineItemRequestDto;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;
import wayc.backend.cart.presentation.dto.request.DeleteCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.request.PatchCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.request.PostCartLineItemRequestDto;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.PostItemRequestDtoFactory;
import wayc.backend.factory.Item.ShowItemResponseDtoFactory;
import wayc.backend.factory.Item.ShowOptionGroupResponseDtoFactory;
import wayc.backend.factory.cart.PatchCartLineItemRequestDtoFactory;
import wayc.backend.factory.cart.PostCartLineItemRequestDtoFactory;
import wayc.backend.factory.cart.ShowCartResponseDtoFactory;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;
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

public class CartControllerTest extends ControllerTest {

    @Test
    @DisplayName("장바구니 LineItem 생성 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_cart_line_item() throws Exception {
        //given
        PostCartLineItemRequestDto req = PostCartLineItemRequestDtoFactory.createSuccessCase();
        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/carts/cart-line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("create_cart_line_item",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("itemId").type(NUMBER).description("장바구니에 넣은 상품의 아이디"),
                                        fieldWithPath("name").type(STRING).description("장바구니에 넣은 상품 이름"),
                                        fieldWithPath("count").type(NUMBER).description("장바구니에 넣은 상품 개수"),
                                        fieldWithPath("cartOptionGroups").type(ARRAY).description("장바구니에 넣은 상품의 옵션 그룹"),
                                        subsectionWithPath("cartOptionGroups[].cartOptions").type(ARRAY).description("장바구니에 넣은 상품의 옵션 그룹의 옵션"),
                                        subsectionWithPath("cartOptionGroups[].name").type(STRING).description("장바구니에 넣은 상품의 옵션 그룹의 이름"),
                                        subsectionWithPath("cartOptionGroups[].cartOptions[].name").type(STRING).description("장바구니에 넣은 상품의 옵션 그룹의 옵션 이름"),
                                        subsectionWithPath("cartOptionGroups[].cartOptions[].price").type(NUMBER).description("장바구니에 넣은 상품의 옵션 그룹의 옵션 가격")
                                ),
                                responseFields(
                                        fieldWithPath("message").type(STRING).description("요청 성공 메시지")
                                )
                        ));
    }


    @Test
    @DisplayName("장바구니 조회 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_cart() throws Exception {
        //given

        ShowCartResponseDto res = ShowCartResponseDtoFactory.createSuccessCase();
        given(cartService.show(Mockito.any(Long.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/carts")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("show_cart",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("cartLineItems").type(ARRAY).description("장바구니의 상품들"),
                                fieldWithPath("cartLineItems[].id").type(NUMBER).description("장바구니 상품 아이디"),
                                fieldWithPath("cartLineItems[].itemId").type(NUMBER).description("장바구니에 넣은 상품의 아이디"),
                                fieldWithPath("cartLineItems[].name").type(STRING).description("장바구니에 넣은 상품 이름"),
                                fieldWithPath("cartLineItems[].count").type(NUMBER).description("장바구니에 넣은 상품 개수"),
                                fieldWithPath("cartLineItems[].cartOptionGroups").type(ARRAY).description("장바구니에 넣은 상품의 옵션 그룹"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].name").type(STRING).description("장바구니에 넣은 상품의 옵션 그룹의 이름"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].id").type(NUMBER).description("장바구니에 넣은 상품의 옵션 그룹의 아이디"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].cartOptions").type(ARRAY).description("장바구니에 넣은 상품의 옵션 그룹의 옵션"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].cartOptions[].id").type(NUMBER).description("장바구니에 넣은 상품의 옵션 그룹의 옵션 아이디"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].cartOptions[].name").type(STRING).description("장바구니에 넣은 상품의 옵션 그룹의 옵션 이름"),
                                subsectionWithPath("cartLineItems[].cartOptionGroups[].cartOptions[].price").type(NUMBER).description("장바구니에 넣은 상품의 옵션 그룹의 옵션 가격")
                        )
                ));
    }


    @Test
    @DisplayName("장바구니 업데이트 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void update_cart() throws Exception {
        //given

        PatchCartLineItemRequestDto req = PatchCartLineItemRequestDtoFactory.createSuccessCase();

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/carts/cart-line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("update_cart_line_item",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("cartLineItemId").type(NUMBER).description("업데이트할 장바구니에 넣은 상품의 아이디"),
                                fieldWithPath("count").type(NUMBER).description("업데이트할 장바구니 상품의 수량")

                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("장바구니 상품 업데이트를 성공하셨습니다")
                        )
                ));
    }


    @Test
    @DisplayName("장바구니 상품 삭제 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void delete_cart() throws Exception {
        //given
        DeleteCartLineItemRequestDto req = new DeleteCartLineItemRequestDto(1L);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/carts/cart-line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("delete_cart_line_item",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("cartLineItemId").type(NUMBER).description("업데이트할 장바구니에 넣은 상품의 아이디")
                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("장바구니 삭제를 성공하셨습니다")
                        )
                ));
    }
}
