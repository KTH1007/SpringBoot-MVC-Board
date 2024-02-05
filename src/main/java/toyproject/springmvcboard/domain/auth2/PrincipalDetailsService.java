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

    private UserRepository userRepository;

    // username을 받아서 UserDetails를 반환하는 메서드
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 생성
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        log.info("username: " + name);
        if (user != null) {
            return new PrincipalDetail(user); //User 타입을 인자로 하는 생성자
        }
        return null;
    }
}
