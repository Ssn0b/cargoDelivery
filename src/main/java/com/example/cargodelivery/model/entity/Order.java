package com.example.cargodelivery.model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
public class Order {
    private int id;
    private int cargoId;

    private int userId;
    private String userName;
    private String userLastName;

    private String description;

    private int senderCityId;
    private String senderCityName;

    private int receiverCityId;
    private String receiverCityName;


    private int orderStatusId;
    private String orderStatusName;

    private int daysToDeliver;
    private Timestamp dateOfRegister;
    private Timestamp dateOfArrival;
    private double price;

}
