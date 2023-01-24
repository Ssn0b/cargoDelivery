package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import static com.example.cargodelivery.controller.Path.PAGE_HOME;
import static com.example.cargodelivery.controller.Path.PAGE_REPLENISH_BALANCE;
@Log4j
public class PayByBalanceCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("PayByBalanceCommand started");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");
        UserDao userDao = new UserDao();

        User newUser = userDao.findUserById(userId);
        int orderId = Integer.parseInt(request.getParameter("statusButton"));

        double diff;
        OrderDao orderDao = new OrderDao();
        Order newOrder = orderDao.findOrderById(orderId);
        if (newOrder.getOrderStatusId() != 3) {
            if (newUser.getBalance() >= newOrder.getPrice()) {
                diff = (-newOrder.getPrice());
                userDao.updateBalance(newUser, diff);
                orderDao.updateToPaid(orderId);

                Timestamp ts = new Timestamp(System.currentTimeMillis());
                Calendar cal = Calendar.getInstance();
                cal.setTime(ts);
                cal.add(Calendar.DAY_OF_WEEK, newOrder.getDaysToDeliver());
                Timestamp ts1 = new Timestamp(cal.getTime().getTime());
                orderDao.updateDateOfArrival(newOrder, ts1);
                log.info("PayByBalanceCommand user successfully payed");
                return PAGE_HOME;
            } else {
                log.info("PayByBalanceCommand do not have enough in balance to pay");
                return PAGE_REPLENISH_BALANCE;
            }
        }else {
            return PAGE_HOME;
        }
    }
}
