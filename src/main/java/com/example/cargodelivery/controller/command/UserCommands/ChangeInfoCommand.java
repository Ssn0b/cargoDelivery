package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.cargodelivery.controller.Path.*;

public class ChangeInfoCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");
        UserDao userDao = new UserDao();
        User user = userDao.findUserById(userId);

        User newUser;

        String firstName = request.getParameter("changeName");
        String lastName = request.getParameter("changeLastName");
        String email = request.getParameter("changeEmail");
        String number = request.getParameter("changePhone");
        String newPass = request.getParameter("changePassword");
        String oldPass = request.getParameter("currentPass");
        System.out.println(oldPass + " " + user.getPassword());
        if (!Objects.equals(oldPass, user.getPassword())) {
            request.setAttribute("currentUser", user);
            return PAGE_CHANGE_INFO;
        }else {
            request.setAttribute("currentUser", user);
            newUser = User.builder()
                    .id(userId)
                    .name(firstName)
                    .lastname(lastName)
                    .email(email)
                    .number(number)
                    .password(newPass)
                    .build();
            userDao.updateInfo(newUser);
            return PAGE_HOME;
        }
    }
}
