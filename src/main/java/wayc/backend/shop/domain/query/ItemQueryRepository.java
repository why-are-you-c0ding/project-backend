package wayc.backend.shop.domain.query;

import wayc.backend.shop.domain.Item;

import java.util.List;

public interface ItemQueryRepository {
    List<Item> findItemBySearchKeyword(Integer page, String searchKeyword);
}
