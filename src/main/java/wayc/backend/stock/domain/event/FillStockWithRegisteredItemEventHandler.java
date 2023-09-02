package wayc.backend.stock.domain.event;


import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import wayc.backend.shop.domain.event.ItemRegisteredEvent;

import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.repository.StockRepository;
import wayc.backend.stock.domain.StockFactory;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FillStockWithRegisteredItemEventHandler {

    private final StockRepository stockRepository;

    @EventListener(value = ItemRegisteredEvent.class)
    public void handle(ItemRegisteredEvent event){
        if(event != null){
            List<Stock> stocks = StockFactory.createNumberOfAllOptionsToFillStock(event.getGroups());
            stockRepository.saveAll(stocks);
        }
    }
}
