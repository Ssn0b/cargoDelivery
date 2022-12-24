package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_PAY;

public class PayByCardCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int orderId = Integer.parseInt(request.getParameter("statusButton"));

        HttpSession session = request.getSession();
        session.setAttribute("orderId",orderId);

        return PAGE_PAY;
    }
}
