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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class TestLogoutCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpSession session = mock(HttpSession.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        session.setAttribute("currentUserId", null);
        session.setAttribute("lang", null);
        assertEquals("/jsp/login.jsp", new LogoutCommand().execute(request, response));
        assertNull(session.getAttribute("lang"));
    }

}
