package toyproject.springmvcboard.domain.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper Instance = Mappers.getMapper(UserMapper.class);

    UserDTO UserToUserDTO(User user);

    User UserDTOToUser(UserDTO userDTO);
}
