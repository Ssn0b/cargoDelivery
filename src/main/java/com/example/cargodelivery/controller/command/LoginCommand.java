package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String forward = null;

        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        UserDao userDao = new UserDao();

        User newUser = userDao.findUser(user);

        if(newUser == null){
            return Path.PAGE_LOGIN;
        }else {
            session.setAttribute("role",newUser.getRoleId());
            return "redirect:controller?action=home";
        }

    }
}
