package testDAO;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class TestUserDAO {
    private static final User user = Mockito.mock(User.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);

    @BeforeAll
    public static void beforeAll() {

    }

    public static User getTestUser() {
        return User.builder()
                .id(1)
                .name("name")
                .lastname("lastName")
                .email("email")
                .password("password")
                .number("telNumber")
                .roleId(1)
                .balance(2.0)
                .build();
    }

    @Test
    public void mockUserInsertTest() throws SQLException {
        doNothing().when(statement).setString(eq(1), any());
        doNothing().when(statement).setString(eq(2), any());
        doNothing().when(statement).setString(eq(3), any());
        doNothing().when(statement).setString(eq(4), any());
        doNothing().when(statement).setInt(eq(5), eq(1));
        doNothing().when(statement).setString(eq(6), any());

        when(user.getName()).thenReturn("name");
        when(user.getLastname()).thenReturn("lname");
        when(user.getEmail()).thenReturn("vww@gmail.com");
        when(user.getPassword()).thenReturn("1234");
        when(user.getNumber()).thenReturn("5654645");

        when(DBUtil.getConnection()).thenReturn(jdbcConnection); // mock static
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);

        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);

        when(statement.execute()).thenReturn(true);
        UserDao userDao = new UserDao();

        assertDoesNotThrow(() -> userDao.insert(user));
    }

    @Test
    public void mockFindUserTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("lastName")
                .thenReturn("email").thenReturn("password")
                .thenReturn("telNumber");

        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(BCrypt.checkpw(any(), any())).thenReturn(true);
        doNothing().when(statement).setString(eq(1), any());

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUser(user);
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }

    @Test
    public void mockUserByEmailTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("lastName")
                .thenReturn("email").thenReturn("password")
                .thenReturn("telNumber");

        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(BCrypt.checkpw(any(), any())).thenReturn(true);
        doNothing().when(statement).setString(eq(1), any());

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUserByEmail("email");
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }

    @Test
    public void mockUserByIdTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("lastName")
                .thenReturn("email").thenReturn("password")
                .thenReturn("telNumber");

        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(BCrypt.checkpw(any(), any())).thenReturn(true);
        doNothing().when(statement).setInt(eq(1), eq(1));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUserById(1);
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }

    @Test
    public void mockUserByPhoneTest() throws SQLException {
        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("lastName")
                .thenReturn("email").thenReturn("password")
                .thenReturn("telNumber");

        doNothing().when(statement).setString(eq(1), any());
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(BCrypt.checkpw(any(), any())).thenReturn(true);
        doNothing().when(statement).setString(eq(1), any());

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        UserDao userDao = new UserDao();


        User resultUser = userDao.findUserByPhoneNumber("telNumber");
        assertNotNull(resultUser);
        assertEquals(getTestUser(), resultUser);
    }

    @Test
    public void mockUpdateBalanceTest() throws SQLException {
        doNothing().when(statement).setDouble(eq(1), eq(2.0));
        doNothing().when(statement).setInt(eq(2), eq(1));

        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(2.0);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);

        when(BCrypt.checkpw(any(), any())).thenReturn(true);

        UserDao userDao = new UserDao();

        assertDoesNotThrow(() -> userDao.updateBalance(user,2.0));
    }

    @Test
    public void mockUpdateInfo() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);

        when(user.getName()).thenReturn("true");
        when(user.getLastname()).thenReturn("true");
        when(user.getEmail()).thenReturn("true");
        when(user.getNumber()).thenReturn("true");
        when(user.getPassword()).thenReturn("true");

        when(BCrypt.checkpw(any(), any())).thenReturn(true);

        UserDao userDao = new UserDao();

        assertDoesNotThrow(() -> userDao.updateInfo(user));
    }
}