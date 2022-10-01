package wayc.backend.cart.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.cart.domain.Cart;
import wayc.backend.shop.domain.Item;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long> {

    @Query("select c from Cart c where c.memberId =:memberId and c.status = 'ACTIVE'")
    Optional<Cart> findByIdAndStatus(Long memberId);
}
