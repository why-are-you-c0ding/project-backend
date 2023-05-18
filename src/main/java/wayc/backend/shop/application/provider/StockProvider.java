package wayc.backend.shop.application.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.shop.application.dto.response.find.FindStockResponseDto;
import wayc.backend.shop.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.shop.infrastructure.StockQueryRepositoryImpl;
import wayc.backend.shop.presentation.dto.request.FindOptionIdRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockProvider {

    private final OptionProvider optionProvider;
    private final StockQueryRepositoryImpl stockQueryRepository;

    public FindStocksResponseDto findStock(List<FindOptionIdRequest> optionIdList) {
        return new FindStocksResponseDto(
                optionIdList
                        .stream()
                        .map(ids ->
                                new FindStockResponseDto(
                                        stockQueryRepository.findStocks(optionProvider.getOptions(ids.getIdList()))
                                ))
                        .collect(Collectors.toList())
        );
    }
}
