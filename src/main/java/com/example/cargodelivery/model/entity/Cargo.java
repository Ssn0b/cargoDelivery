package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cargo {
    private int id;
    private double length;
    private double width;
    private double heigth;
    private double weigth;

    public static final double MAX_LENGTH = 500;
    public static final double MAX_HEIGTH = 500;
    public static final double MAX_WIDTH = 500;
}
