package com.example.cargodelivery.controller.command.PageCommands;

import com.example.cargodelivery.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_REPORTS;

public class ReceiveReportsPage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        return PAGE_REPORTS;
    }
}
