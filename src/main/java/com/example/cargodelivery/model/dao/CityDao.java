package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.City;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class CityDao {

    public List<City> listSelect() {
        String query = "SELECT * FROM city";
        ArrayList<City> list = new ArrayList<>();
        City newCity;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                newCity = City.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .name_ua(rs.getString("name_ua"))
                        .idRegion(rs.getInt("idRegion"))
                        .build();
                list.add(newCity);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            log.error("city orderSelect error");
            e.printStackTrace();
        }
        return list;
    }

    public City findCity(City city) {
        String query = "select * from city where name = ? or name_ua = ?";
        City newCity = null;
        try{
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, city.getName());
            pst.setString(2, city.getName());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                newCity = City.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .name_ua(rs.getString("name_ua"))
                        .idRegion(rs.getInt("idRegion"))
                        .build();
            }
            rs.close();
            con.close();
        }catch (SQLException e){
            log.error("city find city error");
            e.printStackTrace();
        }
        return newCity;
    }
}
