package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String forward = Path.PAGE_REGISTER;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String number = request.getParameter("contact");

        User user = User.builder()
                .username(name)
                .email(email)
                .password(password)
                .number(number)
                .roleId(1)
                .build();

        UserDao userDao = new UserDao();
        userDao.insert(user);
        forward = Path.PAGE_REGISTER;

        return forward;
    }
}
