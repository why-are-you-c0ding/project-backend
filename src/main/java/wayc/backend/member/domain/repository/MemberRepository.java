package wayc.backend.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.member.domain.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {

    @Query("select m from Member m where m.status = 'ACTIVE' and m.nickName = :nickName")
    Optional<Member> findByNickNameAndStatus(String nickName);

    @Query("select m from Member m where m.status = 'ACTIVE' and m.loginId = :loginId")
    Optional<Member> findByLoginIdAndStatus(String loginId);

    @Query("select m from Member m where m.id = :id and m.status = 'ACTIVE'")
    Optional<Member> findMemberById(Long id);
}
