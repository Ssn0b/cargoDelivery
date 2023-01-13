import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.entity.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCityDAO {

    private static City city1;
    private static City city2;
    private static City city3;

    @BeforeAll
    public static void beforeAll(){
        String name1 = "Lviv";
        String name2 = "Warsaw";
        String name3 = "Київ";
        city1 = City.builder()
                .name(name1)
                .build();
        city2 = City.builder()
                .name(name2)
                .build();
        city3 = City.builder()
                .name(name3)
                .build();
    }

    @Test
    public void findCityTest(){
        CityDao cityDao = new CityDao();
        City newCity = city1;
        City cityFromDB = cityDao.findCity(newCity);
        assertEquals(newCity.getName(), cityFromDB.getName());
    }

    @Test
    public void listSelectTest(){
        CityDao cityDao = new CityDao();
        City cityFromDB1 = cityDao.findCity(city1);
        City cityFromDB2 = cityDao.findCity(city2);
        City cityFromDB3 = cityDao.findCity(city3);
        List<City> cityFromDB = cityDao.listSelect();
        List<City> collection = Arrays.asList(cityFromDB.get(4),cityFromDB.get(1), cityFromDB.get(0));
        assertThat(collection, containsInAnyOrder(cityFromDB1, cityFromDB2, cityFromDB3));
    }
}
