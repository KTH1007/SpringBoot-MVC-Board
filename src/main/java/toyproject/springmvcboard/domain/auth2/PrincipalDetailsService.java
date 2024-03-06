package toyproject.springmvcboard.domain.auth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toyproject.springmvcboard.domain.user.User;
import toyproject.springmvcboard.domain.user.UserRepository;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // username을 받아서 UserDetails를 반환하는 메서드
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 생성
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //loadUserByUsername의 매개변수 username은 signup 페이지 input의 name 속성에 설정한 이름과 동일해야함
//        User userEntity = userRepository.findByUsername(username);
        User userEntity = userRepository.findByEmail(email);
        log.debug("username = {}", email);
        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        throw new UsernameNotFoundException("User not found with username: " + email);
    }
}
