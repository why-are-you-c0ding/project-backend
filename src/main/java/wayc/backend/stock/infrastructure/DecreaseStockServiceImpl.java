package wayc.backend.stock.infrastructure;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.repository.StockOptionRepository;
import wayc.backend.stock.domain.repository.StockRepository;
import wayc.backend.stock.domain.repository.query.StockQueryRepository;
import wayc.backend.stock.domain.service.DecreaseStockService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DecreaseStockServiceImpl implements DecreaseStockService {

    private final StockQueryRepository stockQueryRepository;

    @Override
    @Transactional(readOnly = false)
    public void decreaseStock(Integer quantity, List<Long> optionIds) {
        Stock stock = stockQueryRepository.findStock(optionIds);
        stock.decrease(quantity);
    }
}
