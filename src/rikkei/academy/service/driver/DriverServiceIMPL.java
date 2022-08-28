package rikkei.academy.service.driver;

import rikkei.academy.config.Config;
import rikkei.academy.model.Driver;
import rikkei.academy.service.IGenericService;

import java.util.List;

public class DriverServiceIMPL implements IDriverService {
    public static String PATH_DRIVER = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\driver.txt";
    public static List<Driver> driverList = new Config<Driver>().readFile(PATH_DRIVER);

    @Override
    public List<Driver> findAll() {
        new Config<Driver>().writeFile(PATH_DRIVER, driverList);
        return driverList;
    }

    @Override
    public void save(Driver driver) {
        driverList.add(driver);
    }

    @Override
    public Driver findById(int id) {
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == id) {
                return driverList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == id) {
                driverList.remove(i);
            }
        }
    }

    @Override
    public void updateData() {

    }

    @Override
    public void remove(int id) {

    }
}
