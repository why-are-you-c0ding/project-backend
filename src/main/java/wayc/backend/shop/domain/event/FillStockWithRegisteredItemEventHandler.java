package wayc.backend.shop.domain.event;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import wayc.backend.shop.application.service.StockService;
import wayc.backend.shop.application.dto.request.RegisterStockRequestDto;

@Component
@RequiredArgsConstructor
public class FillStockWithRegisteredItemEventHandler{

    private final StockService stockService;


    @Async
    @EventListener
    public void handle(ItemRegisteredEvent event){
        if(event != null){
            stockService.createStock(new RegisterStockRequestDto());
        }
    }


}
