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

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockOptionRepository stockOptionRepository;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void fillStock(FillStockRequestDto dto) {
        for (FillStockInfoRequestDto stockInfo : dto.getStockInfos()) {
            Stock stock = stockRepository.save(new Stock(stockInfo.getQuantity()));
            for (Long optionId : stockInfo.getOptionIdList()) {
                stockOptionRepository.save(new StockOption(stock.getId(), optionId));
            }
        }
    }

    /**
     * 테스트에서만 사용
     * @param dto
     */
    @Transactional(readOnly = false)
    public void fillStockUseOnlyTest(FillStockRequestDto dto) {
        for (FillStockInfoRequestDto stockInfo : dto.getStockInfos()) {
            Stock stock = stockRepository.save(new Stock(stockInfo.getQuantity()));
            for (Long optionId : stockInfo.getOptionIdList()) {
                stockOptionRepository.save(new StockOption(stock.getId(), optionId));
            }
        }
    }

    @Transactional(readOnly = false)
    public void takeOutStock(){
//        stockRepository.findStockByOptionsId();
    }
}
