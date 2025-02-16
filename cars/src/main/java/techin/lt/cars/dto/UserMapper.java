package techin.lt.cars.dto;

import techin.lt.cars.model.Role;
import techin.lt.cars.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {


    public static UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getId).collect(Collectors.toSet())
        );
    }

    public static User toUser(UserDTO userDTO, Set<Role> roles) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRoles((List<Role>) roles);
        return user;
    }
}
