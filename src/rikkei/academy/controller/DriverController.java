package rikkei.academy.controller;

import rikkei.academy.model.Driver;
import rikkei.academy.service.driver.DriverServiceIMPL;
import rikkei.academy.service.driver.IDriverService;

import java.util.List;

public class DriverController {

    IDriverService driverService = new DriverServiceIMPL();
    public List<Driver> showListDriver(){
        return driverService.findAll();
    }
    public void createDriver(Driver driver) {
        driverService.save(driver);
    }
    public Driver detailDriver(int id) {
        return driverService.findById(id);
    }
}
