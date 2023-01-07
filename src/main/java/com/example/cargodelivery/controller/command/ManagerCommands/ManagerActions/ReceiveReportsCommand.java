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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;
@Log4j
public class ReceiveReportsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("ReceiveReportsCommand started");
        HttpSession session = request.getSession();

        String action = request.getParameter("action1");

        int page = 1;

        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));

        String citySender = "";
        String cityReceiver = "";
        String dateOfRegister = "";
        String dateOfRegisterForPag = "";
        Order order;

        if (request.getParameter("sender") != null && !request.getParameter("sender").isEmpty()) {
            citySender = request.getParameter("sender");
        }
        if (request.getParameter("sender") != null && !request.getParameter("receiver").isEmpty()) {
            cityReceiver = request.getParameter("receiver");
        }

        if (request.getParameter("sender") != null && !request.getParameter("dateOfRegister").isEmpty()) {
            dateOfRegister = request.getParameter("dateOfRegister");
            dateOfRegisterForPag = request.getParameter("dateOfRegister");
            dateOfRegister += " 00:00:00.0";
            order = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .dateOfRegister(Timestamp.valueOf(dateOfRegister))
                    .build();

        } else {
            order = Order.builder()
                    .senderCityName(citySender)
                    .receiverCityName(cityReceiver)
                    .build();
        }

        OrderDao orderDao = new OrderDao();
        List<Order> listOrders = orderDao.selectByDateAndCities(order, 0,
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
        log.info("ReceiveReportsCommand reports successfully received");
        if (action == null) {
            listOrders = orderDao.selectByDateAndCities(order, 0,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        } else {
            listOrders = orderDao.selectByDateAndCities(order, (Integer.parseInt(action) - 1) * recordsPerPage,
                    recordsPerPage);
            request.setAttribute("listOrders", listOrders);

            return PAGE_REPORTS;
        }

    }
}
