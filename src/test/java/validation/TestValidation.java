package validation;

import com.example.cargodelivery.controller.Validation.Validation;
import com.example.cargodelivery.controller.command.PageCommands.ChangeInfoPageCommand;
import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestValidation {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);


    @Test
    void testExecute() throws ServletException, SQLException, IOException {
        User user = mock(User.class);
        UserDao userDao = mock(UserDao.class);

        boolean check = Validation.MakeOrderValidation(request, String.valueOf("Document"), "1234567891",
                "idenPackage", "Warsaw", "Warsaw",  "2.0",  "2.0",
                "2.0", "2.0");
        boolean checkPay = Validation.PayValidation(request, "Vlad Levchuk", "email@gmail.com",
                "address", "Warsaw", "Warsaw",  "12345",  "vlad",
                "1234567891234567", "january","2022","123");

        assertFalse(check);
        assertFalse(checkPay);
    }
}
