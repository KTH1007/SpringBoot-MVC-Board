package toyproject.springmvcboard.global.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import toyproject.springmvcboard.domain.auth2.CustomAuthenticationFailureHandler;
import toyproject.springmvcboard.domain.auth2.CustomAuthenticationSuccessHandler;
import toyproject.springmvcboard.domain.auth2.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(PrincipalOauth2UserService principalOauth2UserService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                          CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.principalOauth2UserService = principalOauth2UserService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // csrf 보안 설정 사용 X
                .authorizeHttpRequests((authorize) -> authorize  // 사용자가 보내는 요청에 인증 절차 수행 필요
                        .requestMatchers("/css/**", "/js/**", "/account/**", "/", "/images/**").permitAll() // 해당 URL은 인증 절차 수행 생략 가능
                        .anyRequest().authenticated())  // 나머지 요청들은 모두 인증 절차 수행해야함
//                .httpBasic(httpBasic -> httpBasic.disable()) // Basic 인증 비활성화

                //자체 로그인
                .formLogin(form -> form
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login") // login 주소가 호출이 되면 security 호출
                        .successHandler(customAuthenticationSuccessHandler) // 로그인 성공시 custom success 핸들러를 호출
                        .failureUrl("/account/login?error") // 로그인 실패시 이동할 URL
                        .failureHandler(customAuthenticationFailureHandler) // 로그인 실패시 custom failure 핸들러 호출
                        .defaultSuccessUrl("/")) // 로그인 성공시 이동할 URL

                //소셜 로그인
                .oauth2Login(oauth2 -> oauth2 // OAuth2를 통한 로그인 사용
                        .defaultSuccessUrl("/") // 로그인 성공시 이동할 URL
                        .userInfoEndpoint(userInfo -> userInfo // 사용자가 로그인에 성공하였을 경우,
                                .userService(principalOauth2UserService))) // 해당 서비스 로직을 타도록 설정
                .logout(logout -> logout.logoutSuccessUrl("/")); // 로그아웃 성공시 해당 url로 이동
        return http.build();
    }


    // password 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
