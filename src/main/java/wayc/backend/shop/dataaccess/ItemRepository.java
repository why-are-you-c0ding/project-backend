package wayc.backend.shop.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {

//    @Query("select s from Shop s where s.id =:shopId and s.status = 'ACTIVE'")
//    Optional <Shop> findByIdAndStatus(Long shopId);
}
