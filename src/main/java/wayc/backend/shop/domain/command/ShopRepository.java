package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Shop;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository <Shop, Long> {

    @Query("select s from Shop s where s.id =:shopId and s.status = 'ACTIVE'")
    Optional<Shop> findByIdAndStatus(Long shopId);

    @Query("select s from Shop s where s.owner.memberId =:ownerId and s.status = 'ACTIVE'")
    Optional<Shop> findByOwnerIdAndStatus(Long ownerId);
}
