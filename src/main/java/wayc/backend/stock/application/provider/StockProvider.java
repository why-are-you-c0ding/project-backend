package wayc.backend.stock.application.provider;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.stock.application.dto.response.find.FindStockResponseDto;
import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.domain.query.StockQueryRepository;
import wayc.backend.stock.presentation.dto.request.FindOptionIdRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockProvider {

    private final StockQueryRepository stockQueryRepository;

    public FindStocksResponseDto findStock(List<FindOptionIdRequest> optionIdList) {
        return new FindStocksResponseDto(
                optionIdList
                        .stream()
                        .map(ids ->
                                new FindStockResponseDto(
                                        stockQueryRepository.findStocks(ids.getIdList())
                                ))
                        .collect(Collectors.toList())
        );
    }
}
