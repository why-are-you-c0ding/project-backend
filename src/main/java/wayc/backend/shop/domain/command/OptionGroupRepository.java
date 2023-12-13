package wayc.backend.shop.domain.command;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wayc.backend.shop.domain.OptionGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

    @EntityGraph(attributePaths = "options")
    @Query("select distinct ogs from OptionGroup ogs where ogs.item.id = :itemId and ogs.status = 'ACTIVE'")
    List<OptionGroup> findByItemIdAndStatus(Long itemId);
}
