package wayc.backend.shop.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Shop;

@Repository
public interface ShopRepository extends JpaRepository <Shop, Long> {
}
