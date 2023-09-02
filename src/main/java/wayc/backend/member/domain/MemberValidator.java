package wayc.backend.member.domain;


import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.domain.repository.EmailRepository;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.exception.email.NotExistsEmailException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final MemberRepository memberRepository;
    private final EmailRepository emailRepository;
    private final PasswordEncoder passwordEncoder;

    public void validate(Member member, String checkPwd, String authKey) {

        if(!passwordEncoder.matches(checkPwd, member.getPassword())){
            throw new NotSamePasswordException();
        }

        if (memberRepository.findByNickNameAndStatus(member.getNickName()).isPresent()) {
            throw new DuplicatedNickNameException();
        }

        if (memberRepository.findByLoginIdAndStatus(member.getLoginId()).isPresent()) {
            throw new DuplicatedLoginIdException();
        }

        if(emailRepository.findByIdAndAuthKey(member.getEmail().getEmail(), authKey).isEmpty()){
            throw new NotExistsEmailException();
        }
    }
}
