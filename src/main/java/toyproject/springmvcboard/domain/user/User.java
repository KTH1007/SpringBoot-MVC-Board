package toyproject.springmvcboard.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private int enabled;
    private String role;
    private Timestamp createDate;
    // 구글, 네이버, 자체 로그인 정보 저장
    private String provider;
    private String providerId;

    @Builder
    public User(String name, String email, String password, int enabled, String role,
                String provider, String providerId, Timestamp createDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }

    @Mapper(componentModel = "spring")
    public static interface UserMapper {
        UserDTO UserToUserDTO(User user);

        User UserDTOToUser(UserDTO userDTO);
    }
}
