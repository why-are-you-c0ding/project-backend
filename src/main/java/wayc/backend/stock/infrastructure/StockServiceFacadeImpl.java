package wayc.backend.stock.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import wayc.backend.stock.domain.service.DecreaseStockService;
import wayc.backend.stock.domain.service.StockServiceFacade;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockServiceFacadeImpl implements StockServiceFacade {

    private final RedissonClient redissonClient;
    private final DecreaseStockService decreaseStockService;

    @Override
    public void decreaseStock(Integer quantity, List<Long> optionIds) {
        RLock lock = redissonClient.getLock(optionIds.toString());

        try {
            //몇초를 기다리고, 몇초를 점유할건지 정한다. lock 획득을 시도
            boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);

            //lock 실패
            if (!available) {
                log.error("락을 획득활 수 없습니다.");
                return;
            }

            decreaseStockService.decreaseStock(quantity, optionIds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); //인터럽트 예외면 서버 오류라고 생각해 그냥 런타임으로 감싸서 던졌다.
        } finally {
            lock.unlock();
        }
    }
}
