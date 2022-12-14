package wayc.backend.unit.shop.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.*;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowItemsResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowTotalItemResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
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

public class ItemControllerTest extends ControllerTest {

    @Test
    @DisplayName("아이템 생성 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_item() throws Exception {
        //given
        PostItemRequestDto req = PostItemRequestDtoFactory.createSuccessCase();
        CreateItemResponseDto res = new CreateItemResponseDto(1L);

        /**
         * given(itemService.create(Mockito.any(Long.class), Mockito.any(CreateItemRequestDto.class) )) 이 코드는 null이 나온다.
         * 아마 컨트롤러에서 principal이 null로 들어가서 그런듯. 이거는 추후에 꼭 수정하자.
         */
        given(itemService.create(Mockito.any(Long.class), Mockito.any(CreateItemRequestDto.class))).willReturn(res);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("create_item",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("itemName").type(STRING).description("상품 이름"),
                                        fieldWithPath("information").type(STRING).description("상품 설명"),
                                        fieldWithPath("imageUrl").type(STRING).description("상품 이미지 URL"),
                                        fieldWithPath("optionGroups").type(ARRAY).description("옵션 그룹"),
                                        fieldWithPath("category").type(STRING).description("상품 카테고리"),
                                        subsectionWithPath("optionGroups[].optionGroupName").type(STRING).description("옵션 그룹의 이름"),
                                        subsectionWithPath("optionGroups[].basic").type(BOOLEAN).description("기본 옵션 그룹인지"),
                                        subsectionWithPath("optionGroups[].options").type(ARRAY).description("옵션"),
                                        subsectionWithPath("optionGroups[].options[].optionName").type(STRING).description("옵션 이름"),
                                        subsectionWithPath("optionGroups[].options[].price").type(NUMBER).description("상품 가격")
                                        ),
                                responseFields(
                                        fieldWithPath("itemId").type(NUMBER).description("아이템 id")
                                )
                        ));
    }


    @Test
    @DisplayName("아이템 조회 성공 컨트롤러 단위 테스트")
    void show_item() throws Exception {
        //given
        ShowItemResponseDto dto1 = ShowItemResponseDtoFactory.createSuccessCaseDto();
        List<ShowOptionGroupResponseDto> dto2 = ShowOptionGroupResponseDtoFactory.createSuccessCaseDto();

        given(itemService.showItem(Mockito.any(Long.class))).willReturn(dto1);
        given(optionGroupSpecificationService.get(Mockito.any(List.class))).willReturn(dto2);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{itemId}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_item",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("itemId").description("조회할 상품의 id")
                        ),
                        responseFields(
                                fieldWithPath("shopId").type(NUMBER).description("가게 id"),
                                fieldWithPath("shopName").type(STRING).description("가게 이름"),
                                fieldWithPath("itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("optionGroups").type(ARRAY).description("옵션 그룹"),
                                fieldWithPath("imageUrl").type(STRING).description("상품 이미지"),
                                fieldWithPath("information").type(STRING).description("상품 정보"),
                                fieldWithPath("category").type(STRING).description("상품 카테고리"),
                                subsectionWithPath("optionGroups[].optionGroupName").type(STRING).description("옵션 그룹의 이름"),
                                subsectionWithPath("optionGroups[].optionGroupId").type(NUMBER).description("옵션 그룹의 아이디"),
                                subsectionWithPath("optionGroups[].basic").type(BOOLEAN).description("기본 옵션 그룹인지"),
                                subsectionWithPath("optionGroups[].options[]").type(ARRAY).description("옵션 그룹의 옵션"),
                                subsectionWithPath("optionGroups[].options[].optionId").type(NUMBER).description("옵션 그룹의 옵션 아이디"),
                                subsectionWithPath("optionGroups[].options[].optionName").type(STRING).description("옵션 그룹의 옵션 이름"),
                                subsectionWithPath("optionGroups[].options[].price").type(NUMBER).description("옵션 그룹의 옵션 가격")
                        )
                ));
    }


    @Test
    @DisplayName("전체 아이템 조회 성공 컨트롤러 단위 테스트")
    void show_items() throws Exception {
        //given
        List<ShowItemsResponseDto> dto = ShowItemsResponseDtoFactory.createSuccessCaseDto();
        ShowTotalItemResponseDto res = new ShowTotalItemResponseDto(true, dto);
        given(itemService.showItems(Mockito.any(Integer.class), Mockito.any(String.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/items?page=0&blockCategory=Food")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_items",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("가져올 페이지 리스트의 인덱스"),
                                parameterWithName("blockCategory").description("차단한 상품의 카테고리")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("items").type(ARRAY).description("판매자가 등록한 상품 배열"),
                                fieldWithPath("items[].itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("items[].shopName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].basicPrice").type(NUMBER).description("기본 가격"),
                                fieldWithPath("items[].imageUrl").type(STRING).description("상품의 이미지"),
                                fieldWithPath("items[].category").type(STRING).description("상품 카테고리")
                        )
                ));
    }




    @Test
    @WithMockSeller
    @DisplayName("판매자가 등록한 아이템 전체 조회 성공 컨트롤러 단위 테스트")
    void show_seller_items() throws Exception {
        //given
        ShowTotalItemResponseDto res = ShowTotalItemResponseDtoFactory.createSuccessCaseDto();

        given(itemService.showSellerItems(Mockito.any(Long.class), Mockito.any(Integer.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/items/sellers?page=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("show_seller_items",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("가져올 페이지 리스트의 인덱스")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("items").type(ARRAY).description("판매자가 등록한 상품 배열"),
                                fieldWithPath("items[].itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("items[].shopName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].basicPrice").type(NUMBER).description("기본 가격"),
                                fieldWithPath("items[].imageUrl").type(STRING).description("상품의 이미지"),
                                fieldWithPath("items[].category").type(STRING).description("상품 카테고리")
                        )
                ));
    }




    @Test
    @DisplayName("추천 아이템 조회 성공 컨트롤러 단위 테스트")
    void show_recommended_items() throws Exception {
        //given
        List<ShowItemsResponseDto> res = ShowItemsResponseDtoFactory.createSuccessCaseDto();
        given(itemService.showRecommendedItem(Mockito.any(List.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/items/recommend?r1=McCain Smiles&r2=Biofinest Maqui Berry Juice Powder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                //.andExpect(jsonPath("$.itemId").value(1))
                .andDo(print())
                .andDo(document("show_recommended_items",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("r1").description("추천 아이템의 이름"),
                                parameterWithName("r2").description("추천 아이템의 이름")
                        ),
                        responseFields(
                                fieldWithPath("[].itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("[].shopName").type(STRING).description("상품 이름"),
                                fieldWithPath("[].itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("[].basicPrice").type(NUMBER).description("기본 가격"),
                                fieldWithPath("[].imageUrl").type(STRING).description("상품의 이미지"),
                                fieldWithPath("[].category").type(STRING).description("상품 카테고리")

                                )
                ));
    }


    @Test
    @DisplayName("검색 아이템 전체 조회 성공 컨트롤러 단위 테스트")
    void search_items() throws Exception {
        //given
        ShowTotalItemResponseDto res = ShowTotalItemResponseDtoFactory.createSuccessCaseDto();

        given(itemService.search(Mockito.any(Integer.class), Mockito.any(String.class))).willReturn(res);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/items/search?keyword=computer&page=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("search_items",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("가져올 페이지 리스트의 인덱스"),
                                parameterWithName("keyword").description("검색할 단어")
                        ),
                        responseFields(
                                fieldWithPath("finalPage").type(BOOLEAN).description("마지막 페이지 리스트인지"),
                                fieldWithPath("items").type(ARRAY).description("판매자가 등록한 상품 배열"),
                                fieldWithPath("items[].itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("items[].shopName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("items[].basicPrice").type(NUMBER).description("기본 가격"),
                                fieldWithPath("items[].imageUrl").type(STRING).description("상품의 이미지"),
                                fieldWithPath("items[].category").type(STRING).description("상품 카테고리")
                        )
                ));
    }
}
