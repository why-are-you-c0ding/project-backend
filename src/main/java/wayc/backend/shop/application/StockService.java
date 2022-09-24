package wayc.backend.shop.application;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.exception.shop.NotExistsOptionSpecificationException;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.CreateStockResponseDto;
import wayc.backend.shop.dataaccess.OptionSpecificationRepository;
import wayc.backend.shop.dataaccess.StockRepository;
import wayc.backend.shop.domain.OptionSpecification;
import wayc.backend.shop.domain.Stock;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final OptionSpecificationRepository optionSpecificationRepository;
    private final StockRepository stockRepository;

    public CreateStockResponseDto create(CreateStockRequestDto dto) {
        List<OptionSpecification> options = makeOptions(dto.getOptionIdList());
        Stock stock = new Stock(options, dto.getStock());
        stockRepository.save(stock);
        return new CreateStockResponseDto();
    }

    private List<OptionSpecification> makeOptions(List<Long> optionIdList) {
        List<OptionSpecification> options = new ArrayList<>();
        for (Long id : optionIdList) {
            OptionSpecification option = optionSpecificationRepository.findByIdAndStatus(id)
                    .orElseThrow(NotExistsOptionSpecificationException::new);
            options.add(option);
        }
        return options;
    }
}
