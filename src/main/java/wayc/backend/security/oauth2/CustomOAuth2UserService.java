package wayc.backend.security.oauth2;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import wayc.backend.common.domain.BaseStatus;
import wayc.backend.member.domain.AuthProvider;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.Role;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.security.UserPrincipal;
import wayc.backend.security.oauth2.user.OAuth2UserInfo;
import wayc.backend.security.oauth2.user.OAuth2UserInfoFactory;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest){
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );
        if(!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("OAuth2 provider에 이메일이 없습니다.");
        }

        Optional<Member> userOptional = memberRepository.findMemberByEmail(oAuth2UserInfo.getEmail());
        Member user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getAuthProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("이미 등록된 멤버입니다.");
            }
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private Member registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Member member = new Member(
                oAuth2UserInfo.getName(),
                null,
                oAuth2UserInfo.getId(),
                0,
                oAuth2UserInfo.getEmail(),
                Role.ROLE_CONSUMER,
                AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId())
        );
        return memberRepository.save(member);
    }
}
