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

    public List<Order> listSelect(int userId) throws SQLException {
        String query = "SELECT o.id,o.dateOfRegister,o.price,o.orderStatusId, c.name,c1.name as name1, os.name as orderName\n" +
                "FROM orders o, city c, city c1, order_status os\n" +
                "where userId = ? and (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id;";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,userId);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .senderCityName(rs.getString("name"))
                    .senderReceiverName(rs.getString("name1"))
                    .orderStatusName(rs.getString("orderName"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .price(rs.getDouble("price"))
                    .build();
            list.add(newOrder);
        }
        return list;
    }

    public void update(int orderId) throws SQLException {
        String query = "update orders set orderStatusId = 3 where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,orderId);

        pst.executeUpdate();
    }
}
