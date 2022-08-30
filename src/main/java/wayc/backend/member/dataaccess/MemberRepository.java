package wayc.backend.member.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wayc.backend.member.domain.Member;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {

    @Query("select m from Member m where m.status = 'ACTIVE' and m.nickName =: nickName")
    Optional<Member> findByNickName(String nickName);

    @Query("select m from Member m where m.status = 'ACTIVE' and m.nickName =: loginId")
    Optional<Member> findByLoginId(String loginId);
}