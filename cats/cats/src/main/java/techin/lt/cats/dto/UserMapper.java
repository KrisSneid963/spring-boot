package techin.lt.cats.dto;

import techin.lt.cats.dto.UserDTO;
import techin.lt.cats.model.User;

public class UserMapper {

    // Convert User to UserDTO
    public static UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    // Convert UserDTO to User
    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        return user;
    }
}
