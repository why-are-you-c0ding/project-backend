package wayc.backend.stock.application.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StockServiceConfig {

    private final RedissonClient redissonClient;
    private final StockServiceImpl stockServiceImpl;

    @Bean
    public StockService stockService(){
        return new StockServiceDecorator(stockServiceImpl, redissonClient);
    }
}
