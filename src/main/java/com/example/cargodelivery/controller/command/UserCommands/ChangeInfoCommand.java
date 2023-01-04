package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_HOME;
import static com.example.cargodelivery.controller.Validation.Validation.ChangeInfoValidation;

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

        if (ChangeInfoValidation(request, firstName, lastName, email, number, newPass, oldPass, user)) {
            return Path.PAGE_CHANGE_INFO;
        }
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
