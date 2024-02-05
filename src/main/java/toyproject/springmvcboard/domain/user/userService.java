package toyproject.springmvcboard.domain.user;

import org.springframework.stereotype.Service;

@Service
public class userService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public userService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void save(UserDTO userDTO) {
        User user = userMapper.UserDTOToUser(userDTO);
        userRepository.save(user);
    }
}
