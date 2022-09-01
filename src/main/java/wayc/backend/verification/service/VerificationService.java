package wayc.backend.verification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.exception.verification.DuplicatedLoginIdException;
import wayc.backend.exception.verification.DuplicatedNickNameException;
import wayc.backend.exception.verification.NotSamePasswordException;
import wayc.backend.member.dataaccess.MemberRepository;

@Service
@RequiredArgsConstructor
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
}
