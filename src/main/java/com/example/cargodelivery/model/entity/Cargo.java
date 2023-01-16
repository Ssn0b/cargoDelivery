package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cargo {
    private int id;
    private int type;
    private double length;
    private double width;
    private double height;
    private double weight;
}
