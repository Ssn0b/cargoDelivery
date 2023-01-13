package testCommands;

import com.example.cargodelivery.controller.Validation.Validation;
import com.example.cargodelivery.controller.command.RegisterCommand;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;


import static com.example.cargodelivery.controller.Validation.Validation.RegisterValidation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TestRegisterCommand {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    private final HttpSession session = mock(HttpSession.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getParameter("name")).thenReturn("vlad");
        when(request.getParameter("lastName")).thenReturn("levchuk");
        when(request.getParameter("email")).thenReturn("vv@gmail.com");
        when(request.getParameter("pass")).thenReturn("1234");
        when(request.getParameter("confirm_pass")).thenReturn("1234");
        when(request.getParameter("contact")).thenReturn("0931235678");

        UserDao userDao = new UserDao();

        assertEquals("/jsp/login.jsp", new RegisterCommand().execute(request, response));

        User userFromDB = userDao.findUserByEmail("vv@gmail.com");
        userDao.deleteUser(userFromDB);
    }
}
