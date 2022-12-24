package com.example.cargodelivery.controller.command.ManagerCommands.ManagerActions;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;

public class FormInvoiceForPaymentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int orderId = Integer.parseInt(request.getParameter("statusButton"));

        OrderDao orderDao = new OrderDao();
        orderDao.updateToWaitForPaid(orderId);

        List<Order> listOrders = orderDao.listAll();

        CityDao cityDao = new CityDao();
        List<City> listCities = cityDao.listSelect();

        request.setAttribute("listCities", listCities);
        request.setAttribute("listOrders", listOrders);
        return PAGE_REPORTS;
    }
}
