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
    private String senderCityName;

    private int receiverCityId;
    private String senderReceiverName;


    private int orderStatusId;
    private String orderStatusName;

    private Timestamp dateOfRegister;
    private double price;

}
