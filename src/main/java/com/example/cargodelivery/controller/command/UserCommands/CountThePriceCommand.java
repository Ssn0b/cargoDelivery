package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.entity.City;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CountThePriceCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        CityDao cityDao = new CityDao();
        List<City> listCategory = cityDao.listSelect();
        request.setAttribute("listCategory", listCategory);
        return Path.PAGE_PRICE;
    }
}
