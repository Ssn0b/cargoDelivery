package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_HOME;
import static com.example.cargodelivery.controller.Validation.Validation.ReplenishTheBalanceValidation;

public class ReplenishTheBalanceCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");
        UserDao userDao = new UserDao();

        User newUser = userDao.findUserById(userId);

        if (ReplenishTheBalanceValidation(request, request.getParameter("deposit"), request.getParameter("name"),
                request.getParameter("email"), request.getParameter("address"), request.getParameter("city"),
                request.getParameter("state"), request.getParameter("zip"), request.getParameter("nameOnCard"), request.getParameter("card"),
                request.getParameter("month"), request.getParameter("year"), request.getParameter("cvv"))) {
            return Path.PAGE_REPLENISH_BALANCE;
        }

        double addSum = Double.parseDouble(request.getParameter("deposit"));
        userDao.updateBalance(newUser, addSum);
        return PAGE_HOME;
    }
}
