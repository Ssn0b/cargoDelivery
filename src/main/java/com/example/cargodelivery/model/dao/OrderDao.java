package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.Order;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {
    public void insert(Order order) throws SQLException {
        String query = "insert into orders(cargoId,userId,description, senderCityId, receiverCityId,orderStatusId,dateOfRegister,daysToDeliver, price) values (?,?,?,?,?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, order.getCargoId());
        pst.setInt(2,order.getUserId());
        pst.setString(3,order.getDescription());
        pst.setInt(4, order.getSenderCityId());
        pst.setInt(5, order.getReceiverCityId());
        pst.setInt(6, 1);
        pst.setTimestamp(7, order.getDateOfRegister());
        pst.setInt(8,order.getDaysToDeliver());
        pst.setDouble(9,order.getPrice());
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public Order findOrderById(int orderId) throws SQLException{
        String query = "select * from orders where id = ?";
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,orderId);
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .cargoId(rs.getInt("cargoId"))
                    .userId(rs.getInt("userId"))
                    .senderCityId(rs.getInt("senderCityId"))
                    .receiverCityId(rs.getInt("receiverCityId"))
                    .orderStatusId(rs.getInt("orderStatusId"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .daysToDeliver(rs.getInt("daysToDeliver"))
                    .price(rs.getDouble("price"))
                    .build();
        }
        rs.close();
        con.close();
        return newOrder;
    }

    public List<Order> listSelect(int userId) throws SQLException {
        String query = "SELECT o.id,o.dateOfRegister,o.dateOfArrival,o.description,o.price,o.orderStatusId, c.name,c1.name as name1, os.name as orderName\n" +
                "FROM orders o, city c, city c1, order_status os\n" +
                "where userId = ? and (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id ORDER BY o.id DESC;";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,userId);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .description(rs.getString("description"))
                    .senderCityName(rs.getString("name"))
                    .receiverCityName(rs.getString("name1"))
                    .orderStatusName(rs.getString("orderName"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                    .price(rs.getDouble("price"))
                    .build();
            list.add(newOrder);
        }
        rs.close();
        con.close();
        return list;
    }

    public void updateToPaid(int orderId) throws SQLException {
        String query = "update orders set orderStatusId = 3 where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,orderId);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void updateToWaitForPaid(int orderId) throws SQLException {
        String query = "update orders set orderStatusId = 2 where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,orderId);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void updateToDecline(int orderId) throws SQLException {
        String query = "update orders set orderStatusId = 4 where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,orderId);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public List<Order> selectByDateAndCities(Order order) throws SQLException {
        String sQuery = "";
        if (!order.getSenderCityName().isEmpty()) {
            sQuery += " and c.name = '" + order.getSenderCityName() + "'";
        }
        if (!order.getReceiverCityName().isEmpty()) {
            sQuery += " and c1.name = '" + order.getReceiverCityName() + "'";
        }
        if (order.getDateOfRegister() != null) {
            Date date = new Date(order.getDateOfRegister().getTime());
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = f.format(date);
            String startDate = ""; startDate += strDate + " 00:00:00.0";
            String endDate = ""; endDate += strDate + " 23:59:59.0";

            sQuery += " and (o.dateOfRegister > '" + startDate +"' and  '"+ endDate + "' > o.dateOfRegister)";

        }
        String query = "SELECT o.id,o.dateOfRegister,o.dateOfArrival,o.description,o.price,o.orderStatusId, c.name,c1.name as name1, os.name as orderName,u.name as userName,u.lastname " +
                "FROM orders o, city c, city c1, order_status os,user u" +
                " where (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id and u.id = o.userId" + sQuery +" ORDER BY o.id DESC;\n;";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);

        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .userName(rs.getString("userName"))
                    .userLastName(rs.getString("lastname"))
                    .description(rs.getString("description"))
                    .senderCityName(rs.getString("name"))
                    .receiverCityName(rs.getString("name1"))
                    .orderStatusName(rs.getString("orderName"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                    .price(rs.getDouble("price"))
                    .build();
            list.add(newOrder);
        }
        rs.close();
        con.close();
        return list;
    }

    public List<Order> listAll() throws SQLException {
        String query = "SELECT o.id,o.dateOfRegister,o.dateOfArrival,o.description,o.price,o.orderStatusId, c.name,c1.name as name1, os.name as orderName,u.name as userName,u.lastname\n" +
                "FROM orders o, city c, city c1, order_status os,user u \n" +
                "where (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id and u.id = o.userId ORDER BY o.id DESC;";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newOrder = Order.builder()
                    .id(rs.getInt("id"))
                    .userName(rs.getString("userName"))
                    .userLastName(rs.getString("lastname"))
                    .description(rs.getString("description"))
                    .senderCityName(rs.getString("name"))
                    .receiverCityName(rs.getString("name1"))
                    .orderStatusName(rs.getString("orderName"))
                    .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                    .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                    .price(rs.getDouble("price"))
                    .build();
            list.add(newOrder);
        }
        rs.close();
        con.close();
        return list;
    }


    public void checkStatusDeliver() throws SQLException{
        String query = "update orders set orderStatusId = 6 where dateOfArrival = CURDATE()";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void updateDateOfArrival(Order newOrder, Timestamp ts1) throws SQLException {
        String query = "update orders set dateOfArrival = DATE(?) where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setTimestamp(1, ts1);
        pst.setInt(2,newOrder.getId());
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
