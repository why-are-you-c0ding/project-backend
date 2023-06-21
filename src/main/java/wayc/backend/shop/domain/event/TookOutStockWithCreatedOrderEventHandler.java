package wayc.backend.shop.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import wayc.backend.order.domain.event.TookOutStockEvent;
import wayc.backend.stock.application.service.StockService;


@Component
@RequiredArgsConstructor
public class TookOutStockWithCreatedOrderEventHandler {

    private final StockService stockService;

    @EventListener(TookOutStockEvent.class)
    public void handle(TookOutStockEvent event){
        stockService.takeOutStock();
    }
}
