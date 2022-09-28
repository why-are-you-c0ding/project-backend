package wayc.backend.shop.presentation;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StockConverter {

    public List<List<Long>> convert(Map<String, String> map) {
        return map.keySet()
                .stream()
                .map(key -> {
                    String[] split = map.get(key).replaceAll("\"", "").split(",");
                    return Arrays.asList(split)
                            .stream()
                            .map(id -> Long.valueOf(id))
                            .collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }
}
