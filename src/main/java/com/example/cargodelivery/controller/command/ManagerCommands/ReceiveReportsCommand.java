package com.example.cargodelivery.controller.command.ManagerCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.Proxy;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.example.cargodelivery.controller.Path.PAGE_MY_ORDERS;
import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;

public class ReceiveReportsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        String citySender = "";
        String cityReceiver = "";
        String dateOfRegister = "";
        Order order;
        if (!request.getParameter("sender").isEmpty()){
            citySender = request.getParameter("sender");
        }
        if (!request.getParameter("receiver").isEmpty()){
            cityReceiver = request.getParameter("receiver");
        }

        if (!request.getParameter("dateOfRegister").isEmpty()){
            dateOfRegister = request.getParameter("dateOfRegister");
            dateOfRegister += " 00:00:00.0";

            order = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .dateOfRegister(Timestamp.valueOf(dateOfRegister))
                    .build();
        }else {
            order = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .build();
        }

        OrderDao orderDao = new OrderDao();
        List<Order> listOrders = orderDao.selectByDateAndCities(order);

        CityDao cityDao = new CityDao();
        List<City> listCities = cityDao.listSelect();

        request.setAttribute("listCities", listCities);
        request.setAttribute("listOrders", listOrders);
        return PAGE_REPORTS;
    }
}
