package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Validation.Validation.LoginValidation;

public class LoginCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(LoginValidation(request,email)){
            return Path.PAGE_LOGIN;
        }

        HttpSession session = request.getSession();

        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        UserDao userDao = new UserDao();

        User newUser = userDao.findUser(user);

        if(newUser == null){
            request.setAttribute("message", "Incorrect password or email");
            return Path.PAGE_LOGIN;
        }else {
            session.setAttribute("currentUserId",newUser.getId());
            session.setAttribute("role",newUser.getRoleId());
            return "redirect:controller?action=home";
        }

    }
}
