package wayc.backend.stock.application.service;

import wayc.backend.stock.application.dto.request.FillStockRequestDto;

import java.util.List;

public interface StockService {
    void fillStockByEvent(FillStockRequestDto dto);
    void fillStock(FillStockRequestDto dto);
    void decreaseStock(Integer quantity, List<Long> optionIds);
}