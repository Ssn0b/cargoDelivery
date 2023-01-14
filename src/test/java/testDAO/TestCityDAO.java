package testDAO;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.entity.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestCityDAO {
    private static final City city = Mockito.mock(City.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);

    public static City getCity() {
        return City.builder()
                .id(1)
                .name("name")
                .name_ua("name_ua")
                .idRegion(1)
                .build();
    }

    @BeforeAll
    public static void beforeAll(){

    }

    @Test
    public void mockFindCityTest() throws SQLException {
        doNothing().when(statement).setString(eq(1), any());
        doNothing().when(statement).setString(eq(2), any());

        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("name_ua");
        Mockito.when(resultSet.getInt(any())).thenReturn(1);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(false);

        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);


        CityDao cityDao = new CityDao();

        City cityFromDB = cityDao.findCity(city);
        assertEquals(getCity(), cityFromDB);
    }


    @Test
    public void mockListSelectTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getString(any()))
                .thenReturn("name").thenReturn("name_ua");
        Mockito.when(resultSet.getInt(any())).thenReturn(1);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);;

        CityDao cityDao = new CityDao();

        List<City> cityFromDB = cityDao.listSelect();
        assertEquals(1, cityFromDB.size());
        assertEquals(getCity(), cityFromDB.get(0));
    }
}
