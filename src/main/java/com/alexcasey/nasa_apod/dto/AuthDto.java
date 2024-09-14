package com.alexcasey.nasa_apod.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthDto {
    private Long id;
    private String username;
    private String token;
    private List<String> roles;
}
