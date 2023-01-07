package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;
@Log4j
public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("currentUserId", null);
            session.setAttribute("role", null);
        }
        log.info("LogoutCommand user log out");
        return Path.PAGE_LOGIN;
    }
}
