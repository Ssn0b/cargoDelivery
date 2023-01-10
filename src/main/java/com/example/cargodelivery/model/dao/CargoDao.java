package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.User;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class CargoDao {
    public void insert(Cargo cargo) {
        String query = "insert into cargo(idCargoType,weight, length, width,height) values (?,?,?,?,?)";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cargo.getType());
            pst.setDouble(2, cargo.getWeight());
            pst.setDouble(3, cargo.getLength());
            pst.setDouble(4, cargo.getWidth());
            pst.setDouble(5, cargo.getHeight());
            pst.executeUpdate();
            pst.close();
            con.close();
        }catch (SQLException e){
            log.error("cargo insert error");
            e.printStackTrace();
        }

    }

    public Cargo selectLastCargo() {
        String query = "select * from cargo ORDER BY id DESC LIMIT 1";
        Cargo newCargo = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newCargo = Cargo.builder()
                        .id(rs.getInt("id"))
                        .type(rs.getInt("idCargoType"))
                        .weight(rs.getDouble("weight"))
                        .width(rs.getDouble("width"))
                        .height(rs.getDouble("height"))
                        .length(rs.getDouble("length")).build();
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("cargo select last cargo error");
            e.printStackTrace();
        }
        return newCargo;
    }

    public void deleteCargo(Cargo cargo){
        String query = "DELETE FROM `cargo_delivery`.`cargo` WHERE (`id` = ?);";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cargo.getId());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
