package wayc.backend.security.oauth2.user;

import wayc.backend.member.domain.AuthProvider;
import wayc.backend.security.oauth2.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    private OAuth2UserInfoFactory(){}

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        }

        throw new OAuth2AuthenticationProcessingException("죄송합니다. " + registrationId + " 로그인은 지원하지 않습니다.");
    }
}
