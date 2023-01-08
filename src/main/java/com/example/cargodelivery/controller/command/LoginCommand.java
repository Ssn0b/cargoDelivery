package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Validation.Validation.LoginValidation;

@Log4j
public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("started");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (LoginValidation(request, email)) {
            log.info("LoginCommand validation failed");
            return Path.PAGE_LOGIN;
        }

        HttpSession session = request.getSession();

        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        UserDao userDao = new UserDao();

        User newUser = userDao.findUser(user);

        if (newUser == null) {
            request.setAttribute("message", "Incorrect password or email");
            log.info("the user is not logged in");
            return Path.PAGE_LOGIN;
        } else {
            session.setAttribute("currentUserId", newUser.getId());
            session.setAttribute("role", newUser.getRoleId());
            log.info("user successfully logged in");
            return "redirect:controller?action=home";
        }

    }
}
