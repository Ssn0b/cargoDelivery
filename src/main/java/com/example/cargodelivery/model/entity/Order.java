package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Order {
    private int id;
    private int cargoId;

    private int userId;
    private String userName;
    private String userLastName;

    private String receiverNum;
    private String description;

    private int senderCityId;
    private String senderCityName;
    private String senderCityNameUa;

    private int receiverCityId;
    private String receiverCityName;
    private String receiverCityNameUa;

    private int orderStatusId;
    private String orderStatusName;

    private int daysToDeliver;
    private Timestamp dateOfRegister;
    private Timestamp dateOfArrival;
    private double price;

}
