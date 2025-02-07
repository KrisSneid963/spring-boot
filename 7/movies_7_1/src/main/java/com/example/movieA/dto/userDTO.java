package com.example.movieA.dto;

import java.util.List;

public class userDTO {
    private Long id;
    private String username;
    private List<String> roles;

    public void UserDTO(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }


}
