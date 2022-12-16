package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.*;

public class PayCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int orderId = (int) session.getAttribute("orderId");

        OrderDao orderDao = new OrderDao();
        orderDao.update(orderId);
        System.out.println(orderId);
        return PAGE_HOME;
    }
}
