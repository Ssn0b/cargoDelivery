package com.example.cargodelivery.controller;

import com.example.cargodelivery.controller.command.Command;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String commandName = request.getParameter("action");
       // Command command = CommandContainer.get
    }
}
