package wayc.backend.shop.domain.command;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {

    @EntityGraph(attributePaths = {"shop", "optionGroupSpecifications"})
    @Query("select i from Item i where i.id =:itemId and i.status = 'ACTIVE'")
    Optional<Item> findItemByItemId(Long itemId);

    //@EntityGraph(attributePaths = {"shop", "optionGroupSpecifications"})
    @Query("select distinct i from Item i where i.status = 'ACTIVE' and i.category <> :blockKeyword")
    Slice<Item> findItemsPagingByStatus(PageRequest paging, String blockKeyword);

    @EntityGraph(attributePaths = {"shop"})
    @Query("select i from Item i where i.shop.ownerId =:ownerId and i.status = 'ACTIVE'")
    Slice<Item> findItemPagingByShopOwnerId(Long ownerId, PageRequest paging);

    //TODO 추후에 이런 방식과 shop에서 list로 한번에 가져오는 것 성능 비교해보기.

    @EntityGraph(attributePaths = {"shop"})
    @Query("select i from Item i where i.shop.ownerId = :ownerId and i.id = :itemId and i.status = 'ACTIVE'")
    Optional<Item> findItemByShopOwnerIdAndItemId(Long ownerId, Long itemId);
}