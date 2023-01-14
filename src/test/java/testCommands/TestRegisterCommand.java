package testCommands;

import com.example.cargodelivery.controller.Validation.Validation;
import com.example.cargodelivery.controller.command.RegisterCommand;
import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestRegisterCommand {

    private static final User user = Mockito.mock(User.class);

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getParameter("name")).thenReturn("vlad");
        when(request.getParameter("lastName")).thenReturn("levchuk");
        when(request.getParameter("email")).thenReturn("vv@gmail.com");
        when(request.getParameter("pass")).thenReturn("1234");
        when(request.getParameter("confirm_pass")).thenReturn("1234");
        when(request.getParameter("contact")).thenReturn("0931235678");
        UserDao userDao = mock(UserDao.class);

        when(userDao.findUserByEmail(any())).thenReturn(null);
        when(userDao.findUserByPhoneNumber(any())).thenReturn(null);
        when(user.getNumber()).thenReturn(null);

        doNothing().when(userDao).insert(any());
        assertEquals("/jsp/login.jsp", new RegisterCommand().execute(request, response));
    }
}
