package testCommands;

import com.example.cargodelivery.controller.command.PageCommands.ChangeInfoPageCommand;
import com.example.cargodelivery.controller.command.PageCommands.ReplenishTheBalancePageCommand;
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

public class TestChangeInfoPageCommand {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        User user = mock(User.class);
        UserDao userDao = mock(UserDao.class);
        when(userDao.findUserById(any(Integer.class))).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentUserId")).thenReturn(1);

        assertEquals("/jsp/changeProfileInfo.jsp", new ChangeInfoPageCommand().execute(request, response));
    }
}
