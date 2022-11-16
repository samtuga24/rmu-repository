package com.samtuga.rmufilemanagement.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long userId;
    private String firstName;
    private String email;
    private String password;
    private List<String> roles;

    public JwtResponse(String token, long userId,
                       String firstName, String email, List<String> roles) {
        this.token = token;
        this.userId = userId;
        this.firstName = firstName;
        this.email = email;
        this.roles = roles;
    }
}
