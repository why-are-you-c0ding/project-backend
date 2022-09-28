package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStocksResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowStocksResponseDtoFactory {

    public static ShowStocksResponseDto createSuccessCaseDto(){

        ShowStockResponseDto stock1 = new ShowStockResponseDto(2L, 11);
        ShowStockResponseDto stock2 = new ShowStockResponseDto(1L, 11);

        List<ShowStockResponseDto> list = Arrays.asList(stock1, stock2);

        return new ShowStocksResponseDto(list);
    }
}
