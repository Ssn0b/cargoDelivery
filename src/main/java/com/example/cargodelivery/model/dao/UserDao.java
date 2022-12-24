package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void insert(User user) throws SQLException {
        String query = "insert into user(name,lastname, email, password,roleId,telNumber) values (?,?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, user.getName());
        pst.setString(2,user.getLastname());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getPassword());
        pst.setInt(5, 1);
        pst.setString(6, user.getNumber());
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public User findUser(User user) throws SQLException{
        String query = "select * from user where email = ? and password = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,user.getEmail());
        pst.setString(2,user.getPassword());
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newUser = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .lastname(rs.getString("lastName"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .roleId(rs.getInt("roleId"))
                    .number(rs.getString("telNumber"))
                    .build();
        }
        rs.close();
        con.close();
        return newUser;
    }

    public User findUserById(int userId) throws SQLException{
        String query = "select * from user where id = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,userId);
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newUser = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .lastname(rs.getString("lastName"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .roleId(rs.getInt("roleId"))
                    .number(rs.getString("telNumber"))
                    .balance(rs.getDouble("balance"))
                    .build();
        }
        rs.close();
        con.close();
        return newUser;
    }

    public void updateBalance(User currentUser, double addSum) throws SQLException {
        String query = "update user set balance = (balance + ?) where id = ?";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setDouble(1, addSum);
        pst.setInt(2,currentUser.getId());
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
