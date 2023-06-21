package wayc.backend.shop.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.event.Event;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;


@Getter
@NoArgsConstructor
public class ItemRegisteredEvent extends Event {

    private FillStockRequestDto dto;

    public ItemRegisteredEvent(FillStockRequestDto dto) {
        this.dto = dto;
    }
}
