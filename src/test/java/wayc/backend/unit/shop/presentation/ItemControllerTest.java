package wayc.backend.unit.shop.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.PostItemRequestDtoFactory;
import wayc.backend.factory.Item.ShowItemResponseDtoFactory;
import wayc.backend.factory.Item.ShowOptionGroupResponseDtoFactory;
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
                                        fieldWithPath("optionGroupRequests").type(ARRAY).description("옵션 그룹"),
                                        subsectionWithPath("optionGroupRequests[].optionGroupName").type(STRING).description("옵션 그룹의 이름"),
                                        subsectionWithPath("optionGroupRequests[].optionRequests").type(ARRAY).description("옵션"),
                                        subsectionWithPath("optionGroupRequests[].optionRequests[].optionName").type(STRING).description("옵션 이름"),
                                        subsectionWithPath("optionGroupRequests[].optionRequests[].price").type(NUMBER).description("상품 가격")

                                        ),
                                responseFields(
                                        fieldWithPath("itemId").type(NUMBER).description("아이템 id")
                                )
                        ));
    }


    @Test
    @DisplayName("아이템 조회 성공 컨트롤러 단위 테스트")
    @WithMockSeller
    void show_item() throws Exception {
        //given
        ShowItemResponseDto dto1 = ShowItemResponseDtoFactory.createSuccessCaseDto();
        List<ShowOptionGroupResponseDto> dto2 = ShowOptionGroupResponseDtoFactory.createSuccessCaseDto();
        given(itemService.get(Mockito.any(Long.class))).willReturn(dto1);
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
                                fieldWithPath("shopId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("shopName").type(STRING).description("상품 이름"),
                                fieldWithPath("itemId").type(NUMBER).description("아이템 id"),
                                fieldWithPath("itemName").type(STRING).description("상품 이름"),
                                fieldWithPath("optionGroups").type(ARRAY).description("옵션 그룹"),
                                subsectionWithPath("optionGroups[].optionGroupName").type(STRING).description("옵션 그룹의 이름"),
                                subsectionWithPath("optionGroups[].optionGroupId").type(NUMBER).description("옵션 그룹의 아이디"),
                                subsectionWithPath("optionGroups[].options[]").type(ARRAY).description("옵션 그룹의 옵션"),
                                subsectionWithPath("optionGroups[].options[].optionId").type(NUMBER).description("옵션 그룹의 옵션 아이디"),
                                subsectionWithPath("optionGroups[].options[].optionName").type(STRING).description("옵션 그룹의 옵션 이름"),
                                subsectionWithPath("optionGroups[].options[].price").type(NUMBER).description("옵션 그룹의 옵션 가격")
                        )
                ));
    }
}
