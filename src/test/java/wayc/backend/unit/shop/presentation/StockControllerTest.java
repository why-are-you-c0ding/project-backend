package wayc.backend.unit.shop.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.PostItemRequestDtoFactory;
import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.CreateItemResponseDto;
import wayc.backend.shop.application.dto.response.CreateStockResponseDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.shop.presentation.dto.request.PostStockRequestDto;
import wayc.backend.unit.ControllerTest;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class StockControllerTest extends ControllerTest {

    @Test
    @DisplayName("옵션에 따른 재고 생성 컨트롤러 단위 테스트")
    @WithMockSeller
    void create_stock() throws Exception {
        //given
        PostStockRequestDto req = new PostStockRequestDto(Arrays.asList(21L, 39L), 1000);

        given(stockService.create(Mockito.any(CreateStockRequestDto.class))).willReturn(new CreateStockResponseDto());

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(value)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("create_stock",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("stock").type(NUMBER).description("재고 개수"),
                                fieldWithPath("optionIdList").type(ARRAY).description("옵션 Id 리스트")

                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("성공 메시지")
                        )
                ));
    }
}
