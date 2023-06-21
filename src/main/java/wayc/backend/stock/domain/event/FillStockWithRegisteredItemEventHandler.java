package wayc.backend.stock.domain.event;


import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import wayc.backend.shop.domain.event.ItemRegisteredEvent;
import wayc.backend.stock.application.service.StockService;

@Component
@RequiredArgsConstructor
public class FillStockWithRegisteredItemEventHandler{

    private final StockService stockService;

    @Async
    @TransactionalEventListener(ItemRegisteredEvent.class)
    public void handle(ItemRegisteredEvent event){
        if(event != null){
            stockService.fillStock(event.getDto());
        }
    }
}
