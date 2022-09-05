package wayc.backend.verification.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.exception.verification.DuplicatedLoginIdException;
import wayc.backend.exception.verification.DuplicatedNickNameException;
import wayc.backend.exception.verification.NotExistsMemberException;
import wayc.backend.exception.verification.NotSamePasswordException;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationService {

    private final MemberRepository memberRepository;

    public void ExistSamLoginId(String loginId){
        if(memberRepository.findByLoginIdAndStatus(loginId).isPresent()){
            throw new DuplicatedLoginIdException();
        }
    }

    public void ExistSameNickName(String nickName){
        if(memberRepository.findByNickNameAndStatus(nickName).isPresent()){
            throw new DuplicatedNickNameException();
        }
    }

    public void isNotSamePasswords(String password, String checkPassword){
        if(!password.equals(checkPassword)){
            throw new NotSamePasswordException();
        }
    }

    public Member findByLoginId(String loginId){
        return memberRepository.findByLoginIdAndStatus(loginId).orElseThrow(NotExistsMemberException::new);
    }
}
