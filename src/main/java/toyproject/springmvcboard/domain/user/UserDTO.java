package toyproject.springmvcboard.domain.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UserDTO {
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private String password;
    @NotNull
    private int enabled;
    @NotNull
    private String role;
    @CreationTimestamp
    private Timestamp createDate;
    @NotNull
    private String provider;
    private String providerId;

    @Builder
    UserDTO(String username, String email, String password, int enabled, String role,
            String provider, String providerId, Timestamp createDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }
}
