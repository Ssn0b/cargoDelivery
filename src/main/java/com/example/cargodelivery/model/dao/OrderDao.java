package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.Order;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Log4j
public class OrderDao {
    private int noOfRecords;

    public void insert(Order order) {
        String query = "insert into orders(cargoId,userId,receiverNum,description, senderCityId, " +
                "receiverCityId,orderStatusId,dateOfRegister,daysToDeliver, price) values (?,?,?,?,?,?,?,?,?,?)";
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, order.getCargoId());
            pst.setInt(2, order.getUserId());
            pst.setString(3,order.getReceiverNum());
            pst.setString(4, order.getDescription());
            pst.setInt(5, order.getSenderCityId());
            pst.setInt(6, order.getReceiverCityId());
            pst.setInt(7, 1);
            pst.setTimestamp(8, order.getDateOfRegister());
            pst.setInt(9, order.getDaysToDeliver());
            pst.setDouble(10, order.getPrice());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order insert error");
            e.printStackTrace();
        }
    }

    public Order findOrderById(int orderId) {
        String query = "select * from orders where id = ?";
        Order newOrder = null;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newOrder = Order.builder()
                        .id(rs.getInt("id"))
                        .cargoId(rs.getInt("cargoId"))
                        .userId(rs.getInt("userId"))
                        .receiverNum(rs.getString("receiverNum"))
                        .description(rs.getString("description"))
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
        } catch (SQLException e) {
            log.error("order find by id error");
            e.printStackTrace();
        }
        return newOrder;
    }

    public List<Order> selectIncomings(String number, int offset, int noOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS o.id,o.dateOfRegister,o.dateOfArrival,o.receiverNum,o.description,o.price," +
                "o.orderStatusId, c.name,c.name_ua,c1.name as name1,c1.name_ua as name1_ua, os.name as orderName\n" +
                "FROM orders o, city c, city c1, order_status os\n" +
                "where o.receiverNum = ? and (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id " +
                "ORDER BY o.id DESC limit " + offset + ", " + noOfRecords + ";";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, number);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrder = Order.builder()
                        .id(rs.getInt("id"))
                        .receiverNum(rs.getString("receiverNum"))
                        .description(rs.getString("description"))
                        .senderCityName(rs.getString("name"))
                        .senderCityNameUa(rs.getString("name_ua"))
                        .receiverCityName(rs.getString("name1"))
                        .receiverCityNameUa(rs.getString("name1_ua"))
                        .orderStatusName(rs.getString("orderName"))
                        .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                        .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                        .price(rs.getDouble("price"))
                        .build();
                list.add(newOrder);
            }
            rs.close();
            rs = pst.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next())
                this.noOfRecords = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            log.error("order select incomings error");
            e.printStackTrace();
        }
        return list;
    }


    public List<Order> orderSelect(int userId, int offset, int noOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS o.id,o.dateOfRegister,o.dateOfArrival,o.receiverNum,o.description," +
                "o.price,o.orderStatusId, c.name,c.name_ua,c1.name as name1,c1.name_ua as name1_ua, os.name as orderName\n" +
                "FROM orders o, city c, city c1, order_status os\n" +
                "where userId = ? and (c.id = o.senderCityId and c1.id = o.receiverCityId) and orderStatusId = os.id " +
                "ORDER BY o.id DESC limit " + offset + ", " + noOfRecords + ";";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrder = Order.builder()
                        .id(rs.getInt("id"))
                        .receiverNum(rs.getString("receiverNum"))
                        .description(rs.getString("description"))
                        .senderCityName(rs.getString("name"))
                        .senderCityNameUa(rs.getString("name_ua"))
                        .receiverCityName(rs.getString("name1"))
                        .receiverCityNameUa(rs.getString("name1_ua"))
                        .orderStatusName(rs.getString("orderName"))
                        .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                        .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                        .price(rs.getDouble("price"))
                        .build();
                list.add(newOrder);
            }
            rs.close();

            rs = pst.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next())
                this.noOfRecords = rs.getInt(1);

            con.close();
        } catch (SQLException e) {
            log.error("order orderSelect error");
            e.printStackTrace();
        }
        return list;
    }

    public void updateToPaid(int orderId) {
        String query = "update orders set orderStatusId = 3 where id = ?";
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order update to paid error");
            e.printStackTrace();
        }
    }

    public void updateToWaitForPaid(int orderId) {
        String query = "update orders set orderStatusId = 2 where id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order update to waiting for paid error");
            e.printStackTrace();
        }
    }

    public void updateToDecline(int orderId) {
        String query = "update orders set orderStatusId = 4 where id = ?";
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order update to decline error");
            e.printStackTrace();
        }
    }

    public List<Order> selectByDateAndCities(Order order, int offset, int noOfRecords) {
        String sQuery = "";
        if (!order.getSenderCityName().isEmpty()) {
            sQuery += " and (c.name = '" + order.getSenderCityName() + "' or c.name_ua = '"+ order.getSenderCityName() + "')";
        }
        if (!order.getReceiverCityName().isEmpty()) {
            sQuery += " and (c1.name = '" + order.getReceiverCityName() + "' or c1.name_ua = '"+ order.getReceiverCityName() + "')";
        }
        if (order.getDateOfRegister() != null) {
            Date date = new Date(order.getDateOfRegister().getTime());
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = f.format(date);
            String startDate = "";
            startDate += strDate + " 00:00:00.0";
            String endDate = "";
            endDate += strDate + " 23:59:59.0";

            sQuery += " and (o.dateOfRegister > '" + startDate + "' and  '" + endDate + "' > o.dateOfRegister)";

        }
        String query = "SELECT SQL_CALC_FOUND_ROWS o.id,o.dateOfRegister,o.dateOfArrival,o.receiverNum,o.description,o.price," +
                "o.orderStatusId, c.name,c.name_ua,c1.name as name1,c1.name_ua as name1_ua, os.name as orderName,u.name as userName," +
                "u.lastname FROM orders o, city c, city c1, order_status os,user u where (c.id = o.senderCityId and " +
                "c1.id = o.receiverCityId) and orderStatusId = os.id and u.id = o.userId" + sQuery + " " +
                "ORDER BY o.id DESC limit " + offset + ", " + noOfRecords + ";";
        ArrayList<Order> list = new ArrayList<>();
        Order newOrder = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newOrder = Order.builder()
                        .id(rs.getInt("id"))
                        .userName(rs.getString("userName"))
                        .userLastName(rs.getString("lastname"))
                        .receiverNum(rs.getString("receiverNum"))
                        .description(rs.getString("description"))
                        .senderCityName(rs.getString("name"))
                        .senderCityNameUa(rs.getString("name_ua"))
                        .receiverCityName(rs.getString("name1"))
                        .receiverCityNameUa(rs.getString("name1_ua"))
                        .orderStatusName(rs.getString("orderName"))
                        .dateOfRegister(rs.getTimestamp("dateOfRegister"))
                        .dateOfArrival(rs.getTimestamp("dateOfArrival"))
                        .price(rs.getDouble("price"))
                        .build();
                list.add(newOrder);
            }
            rs.close();

            rs = pst.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next())
                this.noOfRecords = rs.getInt(1);
            con.close();
        } catch (SQLException e) {
            log.error("order select by date and cities error");
            e.printStackTrace();
        }
        return list;
    }

    public void checkStatusDeliver() {
        String query = "update orders set orderStatusId = 6 where dateOfArrival = CURDATE()";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("order check status of deliver error");
            e.printStackTrace();
        }
    }

    public void updateDateOfArrival(Order newOrder, Timestamp ts1) {
        String query = "update orders set dateOfArrival = DATE(?) where id = ?";
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setTimestamp(1, ts1);
            pst.setInt(2, newOrder.getId());
            pst.executeUpdate();
            pst.close();
            con.close();
        }catch (SQLException e){
            log.error("order update date of arrival error");
            e.printStackTrace();
        }

    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
