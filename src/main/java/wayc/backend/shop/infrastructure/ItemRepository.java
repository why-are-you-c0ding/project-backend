package wayc.backend.shop.infrastructure;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {

    @EntityGraph(attributePaths = {"shop", "optionGroupSpecifications"})
    @Query("select i from Item i where i.id =:itemId and i.status = 'ACTIVE'")
    Optional<Item> findByIdAndStatus(Long itemId);

    @EntityGraph(attributePaths = {"shop", "optionGroupSpecifications"})
    @Query("select i from Item i where i.status = 'ACTIVE'")
    List<Item> findItemsByStatus();


}
