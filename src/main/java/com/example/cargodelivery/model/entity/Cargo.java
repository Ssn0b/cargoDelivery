package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cargo {
    public static final double MAX_LENGTH = 500;
    public static final double MAX_HEIGTH = 500;
    public static final double MAX_WIDTH = 500;
    private int id;
    private int type;
    private double length;
    private double width;
    private double height;
    private double weight;
}
