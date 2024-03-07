package toyproject.springmvcboard.domain.auth2;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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
import java.util.Set;

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

    @GetMapping("/signup")
    public String singup(){
        return "/account/signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String name, @RequestParam String username,
                                @RequestParam String password, @RequestParam String confirmPassword,
                                RedirectAttributes redirectAttributes) {


        try {// 비밀번호 형식 검증
            String passwordPattern = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}";
            if (!password.matches(passwordPattern)) {
                redirectAttributes.addFlashAttribute("signupError", "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
                log.error("password error = {}", password);
                return "redirect:/account/signup";
            }

            if (!password.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("signupError", "Password and Confirm Password do not match");
                log.error("not match = {}", password);
                return "redirect:/account/signup";
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(password);

            // 현재 시간 정보
            Timestamp registrationTime = new Timestamp(System.currentTimeMillis());

            User user = User.builder()
                    .username(name)
                    .email(username)
                    .password(encodedPassword)
                    .enabled(1)
                    .role("ROLE_USER")
                    .provider("custom")
                    .providerId("custom")
                    .createDate(registrationTime)
                    .build();
            // 사용자 정보 저장
            userRepository.save(user);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                redirectAttributes.addFlashAttribute("signupError", message);
            }
            return "redirect:/account/signup";
        }
        log.debug("signup = {}", username);
        return "/account/login";
    }
}
