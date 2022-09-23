package wayc.backend.shop.dataaccess;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.shop.domain.OptionGroupSpecification;

import java.util.Optional;

@Repository
public interface OptionGroupSpecificationRepository extends JpaRepository<OptionGroupSpecification, Long> {

    @EntityGraph(attributePaths = "optionSpecifications")
    @Query("select ogs from OptionGroupSpecification ogs where ogs.id =:orgId and ogs.status = 'ACTIVE'")
    Optional<OptionGroupSpecification> findByIdAndStatus(Long orgId);
}
