package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.shop.domain.OptionGroup;

import java.util.Optional;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

    @EntityGraph(attributePaths = "options")
    @Query("select ogs from OptionGroup ogs where ogs.id =:orgId and ogs.status = 'ACTIVE'")
    Optional<OptionGroup> findByIdAndStatus(Long orgId);
}
