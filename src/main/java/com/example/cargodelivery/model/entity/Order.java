package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Data
@Builder
public class Order {
    private int id;
    private int cargoId;
    private int userId;
    private int senderCityId;
    private int receiverCityId;
    private int orderStatusId;
    private Timestamp dateOfRegister;
    private double price;

}
