package wayc.backend.stock.application.service;


import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.repository.StockOptionRepository;
import wayc.backend.stock.domain.repository.StockRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockOptionRepository stockOptionRepository;

    @Transactional(readOnly = false)
    public void fillStock(FillStockRequestDto dto) {
        for (FillStockInfoRequestDto stockInfo : dto.getStockInfos()) {
            Stock stock = saveStock(stockInfo);
            saveStockOption(stockInfo, stock);
        }
    }

    private Stock saveStock(FillStockInfoRequestDto stockInfo) {
        return stockRepository.save(new Stock(stockInfo.getQuantity()));
    }

    private void saveStockOption(FillStockInfoRequestDto stockInfo, Stock stock) {
        for (Long optionId : stockInfo.getOptionIdList()) {
            stockOptionRepository.save(new StockOption(stock, optionId));
        }
    }
}
