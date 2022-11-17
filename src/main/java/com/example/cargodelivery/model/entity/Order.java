package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class Order {
    private int id;
    private int userId;
    private int cargoId;
    private int senderCityId;
    private int recepientCityId;
    private int orderStatusId;
    private Date dateOfRegister;
    private Date dateOfArrival;
    private double price;

}
