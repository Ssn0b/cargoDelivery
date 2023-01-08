package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.User;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class UserDao {
    public void insert(User user) {
        String query = "insert into user(name,lastname, email, password,roleId,telNumber) values (?,?,?,?,?,?)";
        try
        {
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
        } catch (SQLException e) {
            log.error("user insert error");
            e.printStackTrace();
        }
    }

    public User findUser(User user){
        String query = "select * from user where email = ?";
        User newUser = null;
        try {
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
                rs.close();
                con.close();
        }

        } catch (SQLException e) {
            log.error("user find user error");
            e.printStackTrace();
        }

        if (newUser != null && BCrypt.checkpw(user.getPassword(), newUser.getPassword()))
            return newUser;
        return null;

    }

    public User findUserById(int userId) {
        String query = "select * from user where id = ?";
        User newUser = null;
        try{
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
        } catch (SQLException e) {
            log.error("user find user by id error");
            e.printStackTrace();
        }
        return newUser;
    }

    public User findUserByEmail(String email) {
        String query = "select * from user where email = ?";
        User newUser = null;
        try {
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
        } catch (SQLException e) {
            log.error("user find user by email error");
            e.printStackTrace();
        }
        return newUser;
    }

    public void updateBalance(User currentUser, double addSum) {
        String query = "update user set balance = (balance + ?) where id = ?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDouble(1, addSum);
            pst.setInt(2, currentUser.getId());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            log.error("user update balance error");
            e.printStackTrace();
        }

    }

    public void updateInfo(User user) {
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
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            pst.close();
            con.close();
        }catch (SQLException e){
            log.error("user update info error");
            e.printStackTrace();
        }

    }

    public User findUserByPhoneNumber(String number) {
        String query = "select * from user where telNumber = ?";
        User newUser = null;
        try{
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
        }catch (SQLException e){
            log.error("user find user by phone number error");
            e.printStackTrace();
        }
        return newUser;
    }
}
