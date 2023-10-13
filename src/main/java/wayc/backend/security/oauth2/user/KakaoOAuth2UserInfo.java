package wayc.backend.security.oauth2.user;

import java.util.Map;

public class KakaoOAuth2UserInfo  extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        Map properties = (Map) attributes.get("properties");
        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        Map kakaoAccount = (Map) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

}