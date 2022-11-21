package wayc.backend.shop.application;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.exception.shop.NotExistsOptionSpecificationException;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.show.ShowStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStocksResponseDto;
import wayc.backend.shop.domain.command.OptionSpecificationRepository;
import wayc.backend.shop.infrastructure.StockQueryRepositoryImpl;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.domain.OptionSpecification;
import wayc.backend.shop.domain.Stock;
import wayc.backend.shop.presentation.dto.request.GetOptionIdRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final OptionSpecificationService optionSpecificationService;

    private final OptionSpecificationRepository optionSpecificationRepository;
    private final StockQueryRepositoryImpl stockQueryRepository;
    private final StockRepository stockRepository;

    public void create(CreateStockRequestDto dto) {
        dto
                .getStockInfos()
                .stream()
                .forEach(stockInfoDto -> {
                    List<OptionSpecification> options = makeOptions(stockInfoDto.getOptionIdList());
                    Stock stock = new Stock(options, stockInfoDto.getQuantity());
                    stockRepository.save(stock);
                });
    }

    private List<OptionSpecification> makeOptions(List<Long> optionIdList) {
        return optionIdList
                .stream()
                .map(id ->
                        optionSpecificationRepository
                        .findByIdAndStatus(id)
                        .orElseThrow(NotExistsOptionSpecificationException::new)
                )
                .collect(Collectors.toList());
    }

    public ShowStocksResponseDto get(List<GetOptionIdRequestDto> optionIdList) {
        return new ShowStocksResponseDto(
                optionIdList
                        .stream()
                        .map(ids ->
                                new ShowStockResponseDto(
                                stockQueryRepository.findStocks(optionSpecificationService.getList(ids.getIdList()))
                        ))
                        .collect(Collectors.toList())
        );
    }
}
