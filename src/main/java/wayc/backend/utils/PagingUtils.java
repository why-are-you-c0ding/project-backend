package wayc.backend.utils;

import java.util.List;
import java.util.stream.Collectors;

public class PagingUtils {
    public static <T> List<T> applyPaging(List<T> result) {
        return result.stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
