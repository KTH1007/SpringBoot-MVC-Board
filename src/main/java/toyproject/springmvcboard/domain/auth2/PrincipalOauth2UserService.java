package toyproject.springmvcboard.domain.auth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import toyproject.springmvcboard.domain.user.User;
import toyproject.springmvcboard.domain.user.UserRepository;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;

    public PrincipalOauth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료 시  @AuthenticationPrincipal 어노테이션이 생성
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> 코드 리턴 -> 액세스 토큰 요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아옴

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("Only Google");
        }

        String provider = Objects.requireNonNull(oAuth2UserInfo).getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = "비밀번호";
        String email = oAuth2UserInfo.getEmail();
        String role = "RULE_USER";
        Timestamp createDate = new Timestamp(System.currentTimeMillis());

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .enabled(1)
                    .createDate(createDate)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
