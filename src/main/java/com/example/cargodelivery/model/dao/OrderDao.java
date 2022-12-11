package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void insert(Order order) throws SQLException {
        String query = "insert into orders(cargoId,userId, senderCityId, receiverCityId,orderStatusId,dateOfRegister, price) values (?,?,?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, order.getCargoId());
        pst.setInt(2,order.getUserId());
        pst.setInt(3, order.getSenderCityId());
        pst.setInt(4, order.getReceiverCityId());
        pst.setInt(5, 1);
        pst.setTimestamp(6, order.getDateOfRegister());
        pst.setDouble(7,order.getPrice());
        pst.executeUpdate();
    }

    public List<Order> listSelect() throws SQLException {
        String query = "SELECT * FROM orders";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .cargoId(rs.getInt("cargoId"))
                    .userId(rs.getInt("userId"))
                    .senderCityId(rs.getInt("senderCityId"))
                    .receiverCityId(rs.getInt("receiverCityId"))
                    .orderStatusId(rs.getInt("orderStatusId"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .build();
            list.add(newOrder);
        }

        return list;
    }
}
