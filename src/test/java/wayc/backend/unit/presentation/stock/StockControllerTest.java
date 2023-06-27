package wayc.backend.unit.presentation.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import wayc.backend.common.WithMockSeller;
import wayc.backend.factory.Item.dto.FindStocksResponseDtoFactory;

import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.presentation.dto.request.FillStockInfoRequest;
import wayc.backend.stock.presentation.dto.request.FillStockRequest;
import wayc.backend.unit.presentation.ControllerTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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

        FillStockInfoRequest req_1 = new FillStockInfoRequest(Arrays.asList(29L, 31L), 1000);
        FillStockInfoRequest req_2 = new FillStockInfoRequest(Arrays.asList(32L, 34L), 500);

        FillStockRequest req = new FillStockRequest(Arrays.asList(req_1, req_2));

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(post("/stocks")
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
                                fieldWithPath("stockInfos").type(ARRAY).description("재고 개수"),
                                subsectionWithPath("stockInfos[].optionIdList").type(ARRAY).description("재고 개수"),
                                subsectionWithPath("stockInfos[].quantity").type(NUMBER).description("옵션 Id 리스트")

                        ),
                        responseFields(
                                fieldWithPath("message").type(STRING).description("성공 메시지")
                        )
                ));
    }


    @Test
    @DisplayName("옵션에 따른 재고 조회 컨트롤러 단위 테스트")
    void show_stocks() throws Exception {
        //given
        FindStocksResponseDto res = FindStocksResponseDtoFactory.createSuccessCaseDto();

        given(stockProvider.findStock(Mockito.any(List.class))).willReturn(res);

        //when
        mockMvc.perform(get("/stocks?optionGroup1=21,32&optionGroup2=29,32")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("show_stocks",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("optionGroup1").description("옵션 그룹의 id를 넣거나 예시 처럼 넣는다"),
                                parameterWithName("optionGroup2").description("옵션 그룹의 id를 넣거나 예시 처럼 넣는다")
                                )
                        ,
                        responseFields(
                                fieldWithPath("stockList").type(ARRAY).description("재고 정보 리스트"),
                                fieldWithPath("stockList[].quantity").type(NUMBER).description("재고 수량")
                        )
                ));
    }
}
