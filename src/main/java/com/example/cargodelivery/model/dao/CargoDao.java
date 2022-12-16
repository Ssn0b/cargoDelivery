package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CargoDao {
    public void insert(Cargo cargo) throws SQLException {
        String query = "insert into cargo(idCargoType,weight, length, width,height) values (?,?,?,?,?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, cargo.getType());
        pst.setDouble(2,cargo.getWeight());
        pst.setDouble(3, cargo.getLength());
        pst.setDouble(4, cargo.getWidth());
        pst.setDouble(5, cargo.getHeight());
        pst.executeUpdate();
    }

    public Cargo selectLastCargo() throws SQLException{
        String query = "select * from cargo ORDER BY id DESC LIMIT 1";
        Cargo newCargo = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newCargo = Cargo.builder()
                    .id(rs.getInt("id"))
                    .type(rs.getInt("idCargoType"))
                    .weight(rs.getDouble("weight"))
                    .width(rs.getDouble("width"))
                    .height(rs.getDouble("height"))
                    .length(rs.getDouble("length")).build();
        }
        return newCargo;
    }
}