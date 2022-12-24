package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private int roleId;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String number;
    private double balance;
}
