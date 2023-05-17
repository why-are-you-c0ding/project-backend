package wayc.backend.factory.Item.dto;

import wayc.backend.shop.application.dto.response.find.FindStockResponseDto;
import wayc.backend.shop.application.dto.response.find.FindStocksResponseDto;

import java.util.Arrays;
import java.util.List;

public class FindStocksResponseDtoFactory {

    public static FindStocksResponseDto createSuccessCaseDto(){

        FindStockResponseDto stock1 = new FindStockResponseDto(2L, 11);
        FindStockResponseDto stock2 = new FindStockResponseDto(1L, 11);

        List<FindStockResponseDto> list = Arrays.asList(stock1, stock2);

        return new FindStocksResponseDto(list);
    }
}
