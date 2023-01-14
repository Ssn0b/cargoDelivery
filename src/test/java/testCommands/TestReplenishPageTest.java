package testCommands;

import com.example.cargodelivery.controller.command.PageCommands.HomePageCommand;
import com.example.cargodelivery.controller.command.PageCommands.ReplenishTheBalancePageCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TestReplenishPageTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        assertEquals("/jsp/replenishBalance.jsp", new ReplenishTheBalancePageCommand().execute(request, response));
    }
}
