package wayc.backend.member.domain;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.DuplicatedLoginIdException;
import wayc.backend.member.exception.DuplicatedNickNameException;
import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.member.application.dto.request.AbstractRegisterMemberRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateRegisterMember(AbstractRegisterMemberRequestDto dto) {

        if (!dto.getPassword().equals(dto.getPassword())) {
            throw new NotSamePasswordException();
        }

        if (memberRepository.findByNickNameAndStatus(dto.getNickName()).isPresent()) {
            throw new DuplicatedNickNameException();
        }

        if (memberRepository.findByLoginIdAndStatus(dto.getLoginId()).isPresent()) {
            throw new DuplicatedLoginIdException();
        }
    }

}
