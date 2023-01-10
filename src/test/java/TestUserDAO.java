import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class TestUserDAO {

    private static double balance;
    private static String updatedName;
    private static String updatedEmail;

    private static User user;


    @BeforeAll
    public static void beforeAll(){
        String firstName = "Vlad";
        String lastName = "Levchuk";
        String email = "levchuk@gmail.com";
        String password = "12345";
        String number = "0630009876";
        balance = 25;
        updatedName = "Oleh";
        updatedEmail = "pop@gmail.com";
        user = User.builder()
                .name(firstName)
                .lastname(lastName)
                .email(email)
                .password(password)
                .number(number)
                .roleId(1)
                .build();
    }

    @Test
    public void newUserRegistrationTest(){
        UserDao userDao = new UserDao();
        User newUser = user;
        userDao.insert(newUser);
        User userFromDB = userDao.findUser(newUser);
        assertEquals(newUser.getName(), userFromDB.getName());
        assertEquals(newUser.getLastname(), userFromDB.getLastname());
        assertEquals(newUser.getEmail(), userFromDB.getEmail());
        assertEquals(newUser.getNumber(), userFromDB.getNumber());
        userDao.deleteUser(userFromDB);
    }

    @Test
    public void findUserByEmailTest(){
        UserDao userDao = new UserDao();
        User newUser = user;
        userDao.insert(newUser);
        User userFromDB = userDao.findUserByEmail(newUser.getEmail());
        assertEquals(newUser.getName(), userFromDB.getName());
        assertEquals(newUser.getLastname(), userFromDB.getLastname());
        assertEquals(newUser.getEmail(), userFromDB.getEmail());
        assertEquals(newUser.getNumber(), userFromDB.getNumber());
        userDao.deleteUser(userFromDB);
    }

    @Test
    public void findUserByPhoneNumberTest(){
        UserDao userDao = new UserDao();
        User newUser = user;
        userDao.insert(newUser);
        User userFromDB = userDao.findUserByPhoneNumber(newUser.getNumber());
        assertEquals(newUser.getName(), userFromDB.getName());
        assertEquals(newUser.getLastname(), userFromDB.getLastname());
        assertEquals(newUser.getEmail(), userFromDB.getEmail());
        assertEquals(newUser.getNumber(), userFromDB.getNumber());
        userDao.deleteUser(userFromDB);
    }

    @Test
    public void updateBalanceTest(){
        UserDao userDao = new UserDao();
        User newUser = user;
        userDao.insert(newUser);
        User userFromDB = userDao.findUserByEmail(newUser.getEmail());
        userDao.updateBalance(userFromDB,balance);
        User updatedUser = userDao.findUserById(userFromDB.getId());
        assertEquals(balance, updatedUser.getBalance());
        userDao.deleteUser(userFromDB);
    }

    @Test
    public void updateInfoTest(){
        UserDao userDao = new UserDao();
        User newUser = user;
        userDao.insert(newUser);
        User userFromDB = userDao.findUser(newUser);
        User updatedInfoUser = User.builder()
                .id(userFromDB.getId())
                .name(updatedName)
                .lastname("")
                .email(updatedEmail)
                .number("")
                .password("")
                .build();
        userDao.updateInfo(updatedInfoUser);
        User updatedUser = userDao.findUserById(userFromDB.getId());
        assertEquals(updatedName, updatedUser.getName());
        assertEquals(updatedEmail, updatedUser.getEmail());
        userDao.deleteUser(userFromDB);
    }
}
