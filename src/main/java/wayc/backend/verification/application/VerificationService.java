package wayc.backend.verification.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.verification.*;
import wayc.backend.member.dataaccess.MemberRepository;
import wayc.backend.member.domain.Member;
import wayc.backend.verification.application.dto.VerificationEmailInfoDto;
import wayc.backend.verification.dataaccess.EmailRedisRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VerificationService {

    private final MemberRepository memberRepository;
    private final EmailRedisRepository emailRedisRepository;

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

    @Transactional(readOnly = false)
    public void saveVerificationNumber(VerificationEmailInfoDto dto) {
        emailRedisRepository.createEmailCertification(dto.getEmail(), dto.getAuthKey());
    }

    public void verifyEmail(String receiveEmail, String authKey) {

        if(notExistsEmail(receiveEmail)){
            throw new NotExistsEmailException();
        }

        if(wrongAuthKey(receiveEmail, authKey)){
            throw new WrongEmailAuthKeyException();
        }
    }

    private boolean notExistsEmail(String receiveEmail) {
        return !emailRedisRepository.hasKey(receiveEmail);
    }

    private boolean wrongAuthKey(String receiveEmail, String certificationNumber) {
        return !emailRedisRepository.getEmailCertification(receiveEmail).equals(certificationNumber);
    }
}
