package wayc.backend.stock.application.service;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.command.StockOptionRepository;
import wayc.backend.stock.domain.command.StockRepository;
import wayc.backend.stock.domain.query.StockQueryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockQueryRepository stockQueryRepository;
    private final StockOptionRepository stockOptionRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void fillStockByEvent(FillStockRequestDto dto) {
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
            stockOptionRepository.save(new StockOption(stock.getId(), optionId));
        }
    }

    @Transactional(readOnly = false)
    public void fillStock(FillStockRequestDto dto) {
        for (FillStockInfoRequestDto stockInfo : dto.getStockInfos()) {
            Stock stock = saveStock(stockInfo);
            saveStockOption(stockInfo, stock);
        }
    }

    @Transactional(readOnly = false)
    public void decreaseStock(Integer quantity, List<Long> optionIds){
        Stock stock = stockQueryRepository.findStock(optionIds);
        stock.decrease(quantity);
    }
}
