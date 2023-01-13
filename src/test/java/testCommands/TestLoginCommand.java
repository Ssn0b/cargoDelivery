package testCommands;

import com.example.cargodelivery.controller.command.LoginCommand;
import com.example.cargodelivery.controller.command.LogoutCommand;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLoginCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        when(request.getParameter("email")).thenReturn("v@gmail.com");
        when(request.getParameter("password")).thenReturn("1234");
        UserDao userDao = mock(UserDao.class);
        User user = User.builder()
                .email("v@gmail.com")
                .password("1234")
                .build();
        when(userDao.findUser(any())).thenReturn(user);

        session.setAttribute("currentUserId", 1);
        session.setAttribute("currentUserNumber", "0976000853");
        session.setAttribute("role", 1);

        assertEquals("/jsp/login.jsp", new LoginCommand().execute(request, response));
    }
}
