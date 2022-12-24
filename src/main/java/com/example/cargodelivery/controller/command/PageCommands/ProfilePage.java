package com.example.cargodelivery.controller.command.PageCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_PROFILE;

public class ProfilePage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");
        UserDao userDao = new UserDao();

        User newUser = userDao.findUserById(userId);
        request.setAttribute("currentUser", newUser);

        return PAGE_PROFILE;
    }
}
