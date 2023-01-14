package testDAO;

import com.example.cargodelivery.db.DBUtil;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TestOrderDAO {
    private static final Order order = Mockito.mock(Order.class);

    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);
    @BeforeAll
    public static void beforeAll(){

    }

    public static Order getTestOrder() {
        return Order.builder()
                .id(1)
                .cargoId(1)
                .userId(1)
                .receiverNum("receiverNum")
                .description("description")
                .senderCityId(1)
                .receiverCityId(1)
                .orderStatusId(1)
                .dateOfRegister(Timestamp.valueOf("2021-03-24 16:34:26.666"))
                .daysToDeliver(1)
                .price(1.0)
                .build();
    }
    public static Order getOrderIncomings(){
        return Order.builder()
                .id(1)
                .receiverNum("receiverNum")
                .description("description")
                .senderCityName("name")
                .senderCityNameUa("name_ua")
                .receiverCityName("name1")
                .receiverCityNameUa("name_ua1")
                .orderStatusName("orderName")
                .dateOfArrival(Timestamp.valueOf("2021-03-24 16:34:26.666"))
                .dateOfRegister(Timestamp.valueOf("2021-03-24 16:34:26.666"))
                .price(1.0)
                .build();
    }

    public static Order getOrderByDateAndCitiesTest(){
        return Order.builder()
                .id(1)
                .userName("name")
                .userLastName("name")
                .receiverNum("receiverNum")
                .description("description")
                .senderCityName("name")
                .senderCityNameUa("name_ua")
                .receiverCityName("name1")
                .receiverCityNameUa("name_ua1")
                .orderStatusName("orderName")
                .dateOfArrival(Timestamp.valueOf("2021-03-24 16:34:26.666"))
                .dateOfRegister(Timestamp.valueOf("2021-03-24 16:34:26.666"))
                .price(1.0)
                .build();
    }

    @Test
    public void insertTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));
        doNothing().when(statement).setInt(eq(2), eq(1));
        doNothing().when(statement).setString(eq(3), any());
        doNothing().when(statement).setString(eq(4), any());
        doNothing().when(statement).setInt(eq(5), eq(1));
        doNothing().when(statement).setInt(eq(6), eq(1));
        doNothing().when(statement).setInt(eq(7), eq(1));
        doNothing().when(statement).setTimestamp(eq(8), any());
        doNothing().when(statement).setInt(eq(9), eq(1));
        doNothing().when(statement).setDouble(eq(10), eq(1.0));

        when(statement.execute()).thenReturn(true);

        OrderDao orderDao = new OrderDao();

        assertDoesNotThrow(() -> orderDao.insert(order));
    }

    @Test
    public void findByIdTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(true)
                .thenReturn(false);

        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf("2021-03-24 16:34:26.666"));
        Mockito.when(resultSet.getDouble(any())).thenReturn(1.0);
        Mockito.when(resultSet.getString(any()))
                .thenReturn("receiverNum").thenReturn("description");

        OrderDao orderDao = new OrderDao();

        Order resultOrder = orderDao.findOrderById(1);
        assertNotNull(resultOrder);
        assertEquals(getTestOrder(), resultOrder);

    }

    @Test
    public void selectIncomingsTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setString(eq(1), any());


        Mockito.when(resultSet.getString(any())).thenReturn("receiverNum").thenReturn("description")
                .thenReturn("name").thenReturn("name_ua").thenReturn("name1").thenReturn("name_ua1")
                .thenReturn("orderName");
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(1.0);
        Mockito.when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf("2021-03-24 16:34:26.666"));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(statement.executeQuery(any())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        OrderDao orderDao = new OrderDao();

        List<Order> orderResult = orderDao.selectIncomings("receiverNum",0,1);
        assertEquals(1, orderResult.size());
        assertEquals(getOrderIncomings(), orderResult.get(0));
    }

    @Test
    public void updateTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setInt(eq(1), eq(1));
        when(statement.execute()).thenReturn(true);

        OrderDao orderDao = new OrderDao();

        assertDoesNotThrow(() -> orderDao.updateToPaid(1));
        assertDoesNotThrow(() -> orderDao.updateToWaitForPaid(1));
        assertDoesNotThrow(() -> orderDao.updateToDecline(1));

    }


    @Test
    public void selectByDateAndCitiesTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(order.getSenderCityName()).thenReturn("name");
        when(order.getReceiverCityName()).thenReturn("name");
        when(order.getDateOfRegister()).thenReturn(Timestamp.valueOf("2021-03-24 16:34:26.666"));

        doNothing().when(statement).setString(eq(1), any());


        Mockito.when(resultSet.getString(any())).thenReturn("name").thenReturn("name")
                .thenReturn("receiverNum").thenReturn("description")
                .thenReturn("name").thenReturn("name_ua").thenReturn("name1").thenReturn("name_ua1")
                .thenReturn("orderName");
        Mockito.when(resultSet.getInt(any())).thenReturn(1);
        Mockito.when(resultSet.getDouble(any())).thenReturn(1.0);
        Mockito.when(resultSet.getTimestamp(any())).thenReturn(Timestamp.valueOf("2021-03-24 16:34:26.666"));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(statement.executeQuery(any())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        OrderDao orderDao = new OrderDao();

        List<Order> orderResult = orderDao.selectByDateAndCities(order,0,1);
        assertEquals(1, orderResult.size());
        assertEquals(getOrderByDateAndCitiesTest(), orderResult.get(0));
    }

    @Test
    public void updateDateOfArrivalTest() throws SQLException {
        when(DBUtil.getConnection()).thenReturn(jdbcConnection);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        doNothing().when(statement).setTimestamp(eq(1), any());
        doNothing().when(statement).setInt(eq(2), eq(1));

        when(statement.execute()).thenReturn(true);

        OrderDao orderDao = new OrderDao();

        assertDoesNotThrow(() -> orderDao.updateDateOfArrival(order, Timestamp.valueOf("2021-03-24 16:34:26.666")));
    }

}
