package wayc.backend.shop.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import wayc.backend.order.domain.event.TookOutStockEvent;
import wayc.backend.stock.application.service.StockServiceImpl;


@Component
@RequiredArgsConstructor
public class TookOutStockWithCreatedOrderEventHandler {

    private final StockServiceImpl stockService;

    @EventListener(TookOutStockEvent.class)
    public void handle(TookOutStockEvent event){
        stockService.decreaseStock(event.getOrderQuantity(), event.getOrderOptionIds());
    }
}
