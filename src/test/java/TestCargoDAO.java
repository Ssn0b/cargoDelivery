import com.example.cargodelivery.model.dao.CargoDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCargoDAO {

    private static Cargo cargo;

    @BeforeAll
    public static void beforeAll(){
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
    }

    @Test
    public void cargoInsertTest(){
        CargoDao cargoDao = new CargoDao();
        Cargo newCargo = cargo;
        cargoDao.insert(newCargo);
        Cargo cargoFromDB = cargoDao.selectLastCargo();
        assertEquals(newCargo.getType(), cargoFromDB.getType());
        assertEquals(newCargo.getWidth(), cargoFromDB.getWidth());
        assertEquals(newCargo.getLength(), cargoFromDB.getLength());
        assertEquals(newCargo.getHeight(), cargoFromDB.getHeight());
        assertEquals(newCargo.getWeight(), cargoFromDB.getWeight());
        cargoDao.deleteCargo(cargoFromDB);
    }
}
