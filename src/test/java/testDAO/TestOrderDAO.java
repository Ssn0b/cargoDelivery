package testDAO;

import com.example.cargodelivery.model.dao.CargoDao;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestOrderDAO {

    private static Cargo cargo;
    private static User user;
    private static City city1;
    private static City city2;

    private static String receiverNum;
    private static String idenPackage;
    private static Timestamp ts;
    private static int arrivalDate;
    private static double price;
    private static String paid;
    private static String waitForPaid;
    private static String declined;
    private static String cityName1;
    private static String cityName2;



    @BeforeAll
    public static void beforeAll(){
        cityName1 = "Warsaw";
        cityName2 = "London";
        city1 = City.builder()
                .name(cityName1)
                .build();
        city2 = City.builder()
                .name(cityName2)
                .build();

        String firstName = "Vlad";
        String lastName = "Levchuk";
        String email = "levchuk@gmail.com";
        String password = "12345";
        String number = "0630009876";
        user = User.builder()
                .name(firstName)
                .lastname(lastName)
                .email(email)
                .password(password)
                .number(number)
                .roleId(1)
                .build();

        double length = 60;
        double width = 60;
        double weight = 60;
        double height = 60;
        cargo = Cargo.builder()
                .type(1)
                .width(width)
                .weight(weight)
                .height(height)
                .length(length)
                .build();

        receiverNum = "0976000853";
        idenPackage = "table";

        arrivalDate = 4;
        ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, arrivalDate);

        price = 13.5;
        paid = "Paid";
        waitForPaid = "Waiting for payment";
        declined = "Declined";
    }

    @Test
    public void insertTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        orderDao.insert(newOrder);
        List<Order> orderFromDB = orderDao.orderSelect(userFromDB.getId(),0,1);

        assertEquals(newOrder.getReceiverNum(), orderFromDB.get(0).getReceiverNum());
        assertEquals(newOrder.getDescription(), orderFromDB.get(0).getDescription());
        assertEquals(newOrder.getPrice(), orderFromDB.get(0).getPrice());

        orderDao.deleteOrder(orderFromDB.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }

    @Test
    public void findByIdTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        orderDao.insert(newOrder);
        List<Order> orderFromDB = orderDao.orderSelect(userFromDB.getId(),0,1);
        Order order = orderDao.findOrderById(orderFromDB.get(0).getId());

        assertEquals(newOrder.getUserId(), order.getUserId());
        assertEquals(newOrder.getOrderStatusId(), order.getOrderStatusId());
        assertEquals(newOrder.getPrice(), order.getPrice());

        orderDao.deleteOrder(orderFromDB.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }

    @Test
    public void selectIncomingsTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        orderDao.insert(newOrder);
        List<Order> orderFromDB1 = orderDao.orderSelect(userFromDB.getId(),0,1);
        List<Order> orderFromDB2 = orderDao.selectIncomings(newOrder.getReceiverNum(),0,1);

        assertEquals(orderFromDB1.get(0).getReceiverNum(), orderFromDB2.get(0).getReceiverNum());
        assertEquals(newOrder.getDescription(), orderFromDB2.get(0).getDescription());
        assertEquals(newOrder.getPrice(), orderFromDB2.get(0).getPrice());

        orderDao.deleteOrder(orderFromDB1.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }

    @Test
    public void updateTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        orderDao.insert(newOrder);

        List<Order> orderFromDB1 = orderDao.orderSelect(userFromDB.getId(),0,1);

        orderDao.updateToPaid(orderFromDB1.get(0).getId());
        List<Order> orderFromDB2 = orderDao.orderSelect(userFromDB.getId(),0,1);
        assertEquals(paid, orderFromDB2.get(0).getOrderStatusName());

        orderDao.updateToWaitForPaid(orderFromDB1.get(0).getId());
        orderFromDB2 = orderDao.orderSelect(userFromDB.getId(),0,1);
        assertEquals(waitForPaid, orderFromDB2.get(0).getOrderStatusName());

        orderDao.updateToDecline(orderFromDB1.get(0).getId());
        orderFromDB2 = orderDao.orderSelect(userFromDB.getId(),0,1);
        assertEquals(declined, orderFromDB2.get(0).getOrderStatusName());

        orderDao.deleteOrder(orderFromDB1.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }


    @Test
    public void selectByDateAndCitiesTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        Order newOrder2 = Order.builder()
                .senderCityName(cityName1)
                .receiverCityName(cityName2)
                .build();

        orderDao.insert(newOrder);
        List<Order> orderFromDB1 = orderDao.orderSelect(userFromDB.getId(),0,1);
        List<Order> orderFromDB2 = orderDao.selectByDateAndCities(newOrder2,0,1);

        assertEquals(orderFromDB1.get(0).getOrderStatusName(), orderFromDB2.get(0).getOrderStatusName());
        assertEquals(orderFromDB1.get(0).getReceiverNum(), orderFromDB2.get(0).getReceiverNum());
        assertEquals(orderFromDB1.get(0).getPrice(), orderFromDB2.get(0).getPrice());



        orderDao.deleteOrder(orderFromDB1.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }

    @Test
    public void updateDateOfArrivalTest(){
        UserDao userDao = new UserDao();
        CargoDao cargoDao = new CargoDao();
        OrderDao orderDao = new OrderDao();

        userDao.insert(user);
        User userFromDB = userDao.findUser(user);

        cargoDao.insert(cargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();

        CityDao cityDao = new CityDao();
        City newCity1 = city1;
        City cityFromDB1 = cityDao.findCity(newCity1);

        City newCity2 = city2;
        City cityFromDB2 = cityDao.findCity(newCity2);

        Order newOrder = Order.builder()
                .cargoId(cargoFromDB.getId())
                .userId(userFromDB.getId())
                .receiverNum(receiverNum)
                .description(idenPackage)
                .senderCityId(cityFromDB1.getId())
                .receiverCityId(cityFromDB2.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();

        orderDao.insert(newOrder);

        List<Order> orderFromDB1 = orderDao.orderSelect(userFromDB.getId(),0,1);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, newOrder.getDaysToDeliver());
        Timestamp ts1 = new Timestamp(cal.getTime().getTime());
        Date date = new Date(ts1.getTime());
        String testTime = date + " 00:00:00.0";
        orderDao.updateDateOfArrival(orderFromDB1.get(0), ts1);
        List<Order> orderFromDB2 = orderDao.orderSelect(userFromDB.getId(),0,1);
        assertEquals(testTime, orderFromDB2.get(0).getDateOfArrival().toString());

        orderDao.deleteOrder(orderFromDB1.get(0));
        userDao.deleteUser(userFromDB);
        cargoDao.deleteCargo(cargoFromDB);
    }

}
