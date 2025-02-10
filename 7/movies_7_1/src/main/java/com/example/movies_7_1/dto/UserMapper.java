package com.example.movies_7_1.dto;

import com.example.movies_7_1.model.User;
import com.example.movies_7_1.model.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert List<User> to List<UserDTO>
    public static List<UserDTO> userListToDTO(List<User> users) {
        return users.stream()
                .map(UserMapper::userToDTO)
                .toList();
    }

    //convert to tdo
    public static UserDTO userToDTO(User user) {
        Set<Long> roleIds = user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), roleIds);
    }

    // convert saving to tdo
    public static User toUser(UserDTO userDTO, Set<Role> roles) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password()); //password encoded
        user.setRoles(roles); // roles fetching
        return user;
    }
}
