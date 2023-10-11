package wayc.backend.member.domain;


import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.common.redis.RedisService;

import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.exception.email.NotExistsEmailException;
import wayc.backend.member.exception.email.WrongEmailAuthKeyException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    public void validate(Member member, String checkPwd, String authKey) {

        if(wrongAuthKey(member.getAuth().getEmail(), authKey)) {
            throw new WrongEmailAuthKeyException();
        }

        if(!passwordEncoder.matches(checkPwd, member.getAuth().getPassword())){
            throw new NotSamePasswordException();
        }

        if (memberRepository.findByNickNameAndStatus(member.getNickName()).isPresent()) {
            throw new DuplicatedNickNameException();
        }

        if (memberRepository.findByLoginIdAndStatus(member.getLoginId()).isPresent()) {
            throw new DuplicatedLoginIdException();
        }
    }

    private boolean wrongAuthKey(String receiveEmail, String certificationNumber) {
        try{
            String authKey = redisService.get(receiveEmail, String.class).get();
            return !authKey.equals(certificationNumber);
        }
        catch (RuntimeException e) {
            throw new NotExistsEmailException();
        }
    }
}
