package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class LogoutCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }
}
