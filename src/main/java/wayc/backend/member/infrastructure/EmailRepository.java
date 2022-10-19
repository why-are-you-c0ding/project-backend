package wayc.backend.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wayc.backend.member.domain.Email;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("select e from Email e where e.authKey =:authKey and e.email =:email and e.status = 'ACTIVE'")
    Optional<Email> findByIdAndAuthKey(String email, String authKey);
}
