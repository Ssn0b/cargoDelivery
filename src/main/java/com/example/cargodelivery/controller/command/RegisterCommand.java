package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_LOGIN;
import static com.example.cargodelivery.controller.Path.PAGE_REGISTER;
import static com.example.cargodelivery.controller.Validation.Validation.RegisterValidation;

@Log4j
public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("started");

        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("confirm_pass");
        String number = request.getParameter("contact");

        User user = User.builder()
                .name(name)
                .lastname(lastName)
                .email(email)
                .password(password)
                .number(number)
                .roleId(1)
                .build();
        if (RegisterValidation(request, name, lastName, email, password, confirmPassword, number, user)) {
            log.info("validation failed");
            return PAGE_REGISTER;
        }
        UserDao userDao = new UserDao();
        userDao.insert(user);
        log.info("user successfully registered");
        return PAGE_LOGIN;
    }
}
