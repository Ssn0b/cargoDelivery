package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public void insert(User user) throws SQLException {
        Connection connection = DBUtil.getDataSource().getConnection();
        String query = "insert into user(username, email, password,roleId,telNumber) values (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
