package testCommands;

import com.example.cargodelivery.controller.command.PageCommands.LoginPageCommand;
import com.example.cargodelivery.controller.command.RegisterCommand;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLoginPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        assertEquals("/jsp/login.jsp", new LoginPageCommand().execute(request, response));
    }

}
