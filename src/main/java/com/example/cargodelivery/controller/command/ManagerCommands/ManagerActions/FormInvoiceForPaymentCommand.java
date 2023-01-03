package com.example.cargodelivery.controller.command.ManagerCommands.ManagerActions;

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
import java.sql.SQLException;
import java.util.List;

import static com.example.cargodelivery.controller.Path.PAGE_HOME;
import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;

public class FormInvoiceForPaymentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int orderId = Integer.parseInt(request.getParameter("statusButton"));
        OrderDao orderDao = new OrderDao();
        orderDao.updateToWaitForPaid(orderId);

        String action= request.getParameter("action1");

        int page = 1;

        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));

        List<Order> listOrders = orderDao.listAll(0,
                recordsPerPage);

        CityDao cityDao = new CityDao();
        List<City> listCities = cityDao.listSelect();

        int noOfRecords = orderDao.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("listCities", listCities);
        if(action == null) {
            listOrders = orderDao.listAll(0,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        }
        else{
            listOrders = orderDao.listAll((Integer.parseInt(action)-1)*recordsPerPage,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        }
    }
}
