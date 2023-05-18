package wayc.backend.shop.application.service;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import wayc.backend.shop.application.provider.OptionProvider;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.command.OptionRepository;
import wayc.backend.shop.exception.NotExistsOptionSpecificationException;
import wayc.backend.shop.application.dto.request.RegisterStockRequestDto;
import wayc.backend.shop.application.dto.response.find.FindStockResponseDto;
import wayc.backend.shop.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.shop.infrastructure.StockQueryRepositoryImpl;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.domain.Stock;
import wayc.backend.shop.presentation.dto.request.FindOptionIdRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final OptionRepository optionRepository;


    @Transactional(readOnly = false)
    public void createStock(RegisterStockRequestDto dto) {
        dto
                .getStockInfos()
                .forEach(stockInfoDto -> {
                    List<Option> options = makeOptions(stockInfoDto.getOptionIdList());
                    Stock stock = new Stock(options, stockInfoDto.getQuantity());
                    stockRepository.save(stock);
                });
    }

    private List<Option> makeOptions(List<Long> optionIdList) {
        return optionIdList
                .stream()
                .map(id ->
                        optionRepository
                        .findByIdAndStatus(id)
                        .orElseThrow(NotExistsOptionSpecificationException::new)
                )
                .collect(Collectors.toList());
    }
}
