package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import static com.example.cargodelivery.controller.Path.PAGE_HOME;
import static com.example.cargodelivery.controller.Path.PAGE_PAY;
import static com.example.cargodelivery.controller.Validation.Validation.PayValidation;
@Log4j
public class PayCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("PayCommand started");

        HttpSession session = request.getSession();
        int orderId = (int) session.getAttribute("orderId");

        if (PayValidation(request, request.getParameter("name"), request.getParameter("email"),
                request.getParameter("address"), request.getParameter("city"),
                request.getParameter("state"), request.getParameter("zip"), request.getParameter("nameOnCard"),
                request.getParameter("card"), request.getParameter("month"), request.getParameter("year"),
                request.getParameter("cvv"))) {
            log.info("PayCommand validation failed");
            return PAGE_PAY;
        }

        OrderDao orderDao = new OrderDao();
        Order newOrder = orderDao.findOrderById(orderId);
        orderDao.updateToPaid(orderId);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, newOrder.getDaysToDeliver());
        Timestamp ts1 = new Timestamp(cal.getTime().getTime());
        orderDao.updateDateOfArrival(newOrder, ts1);
        log.info("PayCommand user successfully payed");
        return PAGE_HOME;

    }
}
