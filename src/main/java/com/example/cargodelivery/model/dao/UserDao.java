package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public void insert(User user) throws SQLException {
        String query = "insert into user(username, email, password,roleId,telNumber) values (?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getEmail());
        pst.setString(3, user.getPassword());
        pst.setInt(4, 1);
        pst.setString(5, user.getNumber());
        pst.executeUpdate();
    }
}
