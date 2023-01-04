package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

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
        pst.setString(2, user.getLastname());
        pst.setString(3, user.getEmail());
        pst.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        pst.setInt(5, 1);
        pst.setString(6, user.getNumber());
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public User findUser(User user) throws SQLException {
        String query = "select * from user where email = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, user.getEmail());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
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
        if (newUser != null && BCrypt.checkpw(user.getPassword(), newUser.getPassword()))
            return newUser;

        rs.close();
        con.close();
        return null;
    }

    public User findUserById(int userId) throws SQLException {
        String query = "select * from user where id = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
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

    public User findUserByEmail(String email) throws SQLException {
        String query = "select * from user where email = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
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
        pst.setInt(2, currentUser.getId());
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public void updateInfo(User user) throws SQLException {
        String sQuery = "";
        String idUser = String.valueOf(user.getId());
        if (!user.getName().isEmpty()) {
            sQuery += " , name = '" + user.getName() + "'";
        }
        if (!user.getLastname().isEmpty()) {
            sQuery += " , lastname = '" + user.getLastname() + "'";
        }
        if (!user.getEmail().isEmpty()) {
            sQuery += " , email = '" + user.getEmail() + "'";
        }
        if (!user.getNumber().isEmpty()) {
            sQuery += " , telNumber = '" + user.getNumber() + "'";
        }
        if (!user.getPassword().isEmpty()) {
            sQuery += " , password = '" + BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) + "'";
        }
        String query = "update user set id = " + idUser + sQuery + " where id = " + idUser + ";";
        System.out.println(query);
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public User findUserByPhoneNumber(String number) throws SQLException {
        String query = "select * from user where telNumber = ?";
        User newUser = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, number);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
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
}
