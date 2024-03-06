package toyproject.springmvcboard.global.config.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        // 테스트를 위한 원본 비밀번호
        String rawPassword = "qwe";

        // 비밀번호를 인코딩
        String encodedPassword = "$2a$10$vXRVJbj6tPnrnRcczgSWXObjz3J7p8qUm6OpyT8VX6nH0gfw60sDC";

        // 인코딩된 비밀번호와 원본 비밀번호가 일치하는지 확인
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}
