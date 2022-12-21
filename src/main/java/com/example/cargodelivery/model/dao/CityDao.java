package com.example.cargodelivery.model.dao;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao{

    public  List<City> listSelect() throws SQLException {
        String query = "SELECT * FROM city";
        ArrayList<City> list = new ArrayList<>();
        City newCity = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs= pst.executeQuery();
            while (rs.next()) {
                newCity = City.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .idRegion(rs.getInt("idRegion"))
                        .build();
                list.add(newCity);
            }
        rs.close();
        con.close();
        return list;
    }

    public  List<City> listSelectSenderCity(int userId) throws SQLException {
        String query = "SELECT city.name FROM city, orders where city.id = orders.senderCityId and orders.userId = 1;";
        ArrayList<City> list = new ArrayList<>();
        City newCity = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            newCity = City.builder()
                    .name(rs.getString("name"))
                    .build();
            list.add(newCity);
        }
        rs.close();
        con.close();
        return list;
    }

    public City findCity(City city) throws SQLException{
        String query = "select * from city where name = ?";
        City newCity = null;
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,city.getName());
        ResultSet rs = pst.executeQuery();

        if(rs.next()){
            newCity = City.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .idRegion(rs.getInt("idRegion"))
                    .build();
        }
        rs.close();
        con.close();
        return newCity;
    }
}
