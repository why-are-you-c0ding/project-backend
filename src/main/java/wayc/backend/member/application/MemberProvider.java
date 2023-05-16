package wayc.backend.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;


@Service
@RequiredArgsConstructor
public class MemberProvider {

    private final MemberRepository memberRepository;

    public void validateLoginId(String loginId){
        if(memberRepository.findByLoginIdAndStatus(loginId).isPresent()){
            throw new DuplicatedLoginIdException();
        }
    }

    public void validateNickName(String nickName){
        if(memberRepository.findByNickNameAndStatus(nickName).isPresent()){
            throw new DuplicatedNickNameException();
        }
    }
}
