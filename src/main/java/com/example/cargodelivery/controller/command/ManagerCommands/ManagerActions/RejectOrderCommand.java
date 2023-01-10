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
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;
@Log4j
public class RejectOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        log.info("started");
        int orderId = Integer.parseInt(request.getParameter("statusButton"));

        String citySender = request.getParameter("sender");
        String cityReceiver = request.getParameter("receiver");
        String dateOfRegister = request.getParameter("dateOfRegister");
        String dateOfRegisterForPag = request.getParameter("dateOfRegister");

        Order order2;
        if (dateOfRegister != null && !dateOfRegister.isEmpty()) {
            dateOfRegister += " 00:00:00.0";
            order2 = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .dateOfRegister(Timestamp.valueOf(dateOfRegister))
                    .build();

        } else {
            order2 = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .build();
        }

        OrderDao orderDao = new OrderDao();
        Order order = orderDao.findOrderById(orderId);
        if (order.getOrderStatusId() != 3) {
            orderDao.updateToDecline(orderId);
        }

        String action = request.getParameter("page");

        int page = Integer.parseInt(request.getParameter("page"));

        int recordsPerPage = 5;

        List<Order> listOrders = orderDao.selectByDateAndCities(order2,0,
                recordsPerPage);

        CityDao cityDao = new CityDao();
        List<City> listCities = cityDao.listSelect();

        int noOfRecords = orderDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("listCities", listCities);

        session.setAttribute("senderParameter", citySender);
        session.setAttribute("receiverParameter", cityReceiver);
        session.setAttribute("dateParameter", dateOfRegisterForPag);
        log.info("order successfully rejected");
        if (action == null) {
            listOrders = orderDao.selectByDateAndCities(order2,0,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        } else {
            listOrders = orderDao.selectByDateAndCities(order2,(Integer.parseInt(action) - 1) * recordsPerPage,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        }
    }
}
