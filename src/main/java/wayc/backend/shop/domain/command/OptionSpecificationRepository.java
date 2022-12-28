package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.OptionSpecification;

import java.util.Optional;

@Repository
public interface OptionSpecificationRepository extends JpaRepository<OptionSpecification, Long> {

    @Query("select o from OptionSpecification  o where o.id = :optionId and o.status = 'ACTIVE'")
    Optional<OptionSpecification> findByIdAndStatus(Long optionId);
}
