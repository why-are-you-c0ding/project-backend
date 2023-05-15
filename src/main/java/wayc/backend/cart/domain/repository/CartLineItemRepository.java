package wayc.backend.cart.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wayc.backend.cart.domain.CartLineItem;

import java.util.Optional;

public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {

    @Query("select cat from CartLineItem cat where cat.id =:id and cat.status='ACTIVE'")
    Optional<CartLineItem> findByIdAndStatus(Long id);
}
