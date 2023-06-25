package wayc.backend.stock.domain.event;


import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import wayc.backend.shop.domain.event.ItemRegisteredEvent;
import wayc.backend.stock.application.service.StockServiceImpl;

import wayc.backend.stock.utils.OptionUtils;

@Component
@RequiredArgsConstructor
public class FillStockWithRegisteredItemEventHandler{

    private final StockServiceImpl stockService;

    @Async
    @TransactionalEventListener(value = ItemRegisteredEvent.class, phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(ItemRegisteredEvent event){
        if(event != null){
            stockService.fillStockByEvent(OptionUtils.createNumberOfAllOptionsToFillStock(event.getGroups()));
        }
    }
}
