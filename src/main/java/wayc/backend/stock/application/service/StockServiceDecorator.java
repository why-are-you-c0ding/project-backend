package wayc.backend.stock.application.service;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StockServiceDecorator implements StockService {

    private final StockServiceImpl stockService;
    private final RedissonClient redissonClient;

    public StockServiceDecorator(StockServiceImpl stockService, RedissonClient redissonClient) {
        this.stockService = stockService;
        this.redissonClient = redissonClient;
    }

    @Override
    public void fillStockByEvent(FillStockRequestDto dto) {
        stockService.fillStockByEvent(dto);
    }

    @Override
    public void fillStock(FillStockRequestDto dto) {
        stockService.fillStock(dto);
    }

    @Override
    public void decreaseStock(Integer quantity, List<Long> optionIds) {
        RLock lock = redissonClient.getLock(optionIds.toString());

        try {
            //몇초를 기다리고, 몇초를 점유할건지 정한다. lock 획득을 시도
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            //lock 실패
            if (!available) {
                log.error("락을 획득활 수 없습니다.");
                return;
            }

            stockService.decreaseStock(quantity, optionIds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //인터럽트 예외면 서버 오류라고 생각해 그냥 런타임으로 감싸서 던졌다.
        } finally {
            lock.unlock();
        }
    }
}
