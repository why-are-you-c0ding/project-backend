package wayc.backend.utils;

import java.util.List;
import java.util.stream.Collectors;

public class PagingUtils {
    public static <T> List<T> applyPaging(List<T> result) {
        return result.stream()
                .limit(result.size() - 1)
                .collect(Collectors.toList());
    }
}
