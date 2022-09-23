package wayc.backend.unit.item.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.PostItemRequestDtoFactory;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.response.item.CreateItemResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.unit.ControllerTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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

}
