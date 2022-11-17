package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String number;
}
