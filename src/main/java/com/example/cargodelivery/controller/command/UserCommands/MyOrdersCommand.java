package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@Log4j
public class MyOrdersCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("MyOrdersCommand page loaded");

        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 7;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));

        OrderDao orderDao = new OrderDao();
        List<Order> listCategory = orderDao.listSelect((Integer) session.getAttribute("currentUserId"), (page - 1) * recordsPerPage,
                recordsPerPage);

        int noOfRecords = orderDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("listCategory", listCategory);
        return Path.PAGE_MY_ORDERS;
    }
}
