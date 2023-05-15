package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.shop.domain.Option;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query("select o from Option o where o.id = :optionId and o.status = 'ACTIVE'")
    Optional<Option> findByIdAndStatus(Long optionId);
}
