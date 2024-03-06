package toyproject.springmvcboard.domain.auth2;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.springmvcboard.domain.user.User;
import toyproject.springmvcboard.domain.user.UserRepository;
import toyproject.springmvcboard.domain.user.UserService;

import java.sql.Timestamp;

@Controller
@RequestMapping("/account")
@Slf4j
public class LoginController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/account/login";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String username, @RequestParam String email,
                                @RequestParam String password, @RequestParam String confirmPassword,
                                RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("signupError", "Password and Confirm Password do not match");
            log.debug("not match = {}", password);
            return "redirect:/account/signup?show=signup";
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 현재 시간 정보
        Timestamp registrationTime = new Timestamp(System.currentTimeMillis());

        User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .enabled(1)
                .role("ROLE_USER")
                .provider("custom")
                .providerId("custom")
                .createDate(registrationTime)
                .build();
        // 사용자 정보 저장
        userRepository.save(user);
        log.debug("signup = {}", username);
        return "redirect:/account/signin?show=signin"; // 회원가입 후 로그인 페이지로 리다이렉션
    }
}
