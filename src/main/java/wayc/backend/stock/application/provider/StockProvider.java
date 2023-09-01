package wayc.backend.stock.application.provider;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.stock.application.dto.request.FindOptionIdRequestDto;
import wayc.backend.stock.application.dto.response.find.FindStockResponseDto;
import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.domain.query.StockQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockProvider {

    private final StockQueryRepository stockQueryRepository;

    public FindStocksResponseDto findStock(List<FindOptionIdRequestDto> optionIdList) {
        return new FindStocksResponseDto(
                optionIdList
                        .stream()
                        .map(dto -> new FindStockResponseDto(stockQueryRepository.findStocks(dto.getIdList())))
                        .collect(Collectors.toList())
        );
    }
}
