package testDAO;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.CargoDao;
import com.example.cargodelivery.model.entity.Cargo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestCargoDAO {

    private static final Cargo cargo = Mockito.mock(Cargo.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);

    @BeforeAll
    public static void beforeAll(){
        Mockito.mockStatic(DBUtil.class);
        Mockito.mockStatic(BCrypt.class);
    }

    public static Cargo getCargo() {
        return Cargo.builder()
                .id(1)
                .type(1)
                .weight(1.0)
                .length(1.0)
                .height(1.0)
                .width(1.0)
                .build();
    }

    @Test
    public void mockCargoInsertTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);

        doNothing().when(statement).setInt(eq(1), eq(1));
        doNothing().when(statement).setDouble(eq(2), eq(1.0));
        doNothing().when(statement).setDouble(eq(3), eq(1.0));
        doNothing().when(statement).setDouble(eq(4), eq(1.0));
        doNothing().when(statement).setDouble(eq(5), eq(1.0));

        CargoDao cargoDao = new CargoDao();
        assertDoesNotThrow(() -> cargoDao.insert(cargo));
    }

    @Test
    public void mockFindLastCargo() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(1.0);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        CargoDao cargoDao = new CargoDao();

        Cargo newCargo = cargoDao.selectLastCargo();
        assertEquals(getCargo(), newCargo);
    }
}
