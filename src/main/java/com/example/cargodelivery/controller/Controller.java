package com.example.cargodelivery.controller;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.controller.command.CommandContainer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String commandName = request.getParameter("action");
        Command command = CommandContainer.get(commandName);

        String page = command.execute(request,response);
        if (page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else{
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
