package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private int id;
    private String name;
    private String name_ua;
    private int idRegion;
}
