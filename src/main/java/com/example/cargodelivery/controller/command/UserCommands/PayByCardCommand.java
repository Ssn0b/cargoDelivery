package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_PAY;
@Log4j
public class PayByCardCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("PayByCardCommand page loaded");

        int orderId = Integer.parseInt(request.getParameter("statusButton"));

        HttpSession session = request.getSession();
        session.setAttribute("orderId", orderId);

        return PAGE_PAY;
    }
}
