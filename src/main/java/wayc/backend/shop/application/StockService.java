package wayc.backend.shop.application;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.exception.shop.NotExistsOptionSpecificationException;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.CreateStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStocksResponseDto;
import wayc.backend.shop.dataaccess.OptionSpecificationRepository;
import wayc.backend.shop.dataaccess.StockQueryRepository;
import wayc.backend.shop.dataaccess.StockRepository;
import wayc.backend.shop.domain.OptionSpecification;
import wayc.backend.shop.domain.Stock;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final OptionSpecificationService optionSpecificationService;

    private final OptionSpecificationRepository optionSpecificationRepository;
    private final StockQueryRepository stockQueryRepository;
    private final StockRepository stockRepository;

    public CreateStockResponseDto create(CreateStockRequestDto dto) {
        List<OptionSpecification> options = makeOptions(dto.getOptionIdList());
        Stock stock = new Stock(options, dto.getQuantity());
        stockRepository.save(stock);
        return new CreateStockResponseDto();
    }

    private List<OptionSpecification> makeOptions(List<Long> optionIdList) {
        return optionIdList.stream()
                .map(id ->
                        optionSpecificationRepository
                        .findByIdAndStatus(id)
                        .orElseThrow(NotExistsOptionSpecificationException::new)
                )
                .collect(Collectors.toList());

    }

    public ShowStocksResponseDto get(List<List<Long>> idList) {
        return new ShowStocksResponseDto(
                idList
                        .stream()
                        .map(ids -> new ShowStockResponseDto(
                                stockQueryRepository.findStocks(optionSpecificationService.getList(ids))
                        ))
                        .collect(Collectors.toList())
        );
    }
}
