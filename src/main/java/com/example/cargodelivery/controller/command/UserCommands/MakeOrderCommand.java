package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.CargoDao;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        HttpSession session = request.getSession();

        double price = Double.parseDouble(request.getParameter("priceName"));
        int arrivalDate = Integer.parseInt((request.getParameter("arrivalDate")));
        String citySender = request.getParameter("sender");
        String cityReceiver = request.getParameter("receiver");
        String cargoType = request.getParameter("flexRadioDefault");

        String fullName= request.getParameter("nameReceiver");
        String idenPackage= request.getParameter("idenPackage");

        String description= "Package identifier: " + idenPackage+"\n"+"Receiver name: " + fullName;

        double weight = 0;
        double height = 0;
        double length = 0;
        double width = 0;

        if (!request.getParameter("weight").isEmpty()) weight = Double.parseDouble(request.getParameter("weight"));
        if (!request.getParameter("height").isEmpty()) height = Double.parseDouble(request.getParameter("height"));
        if (!request.getParameter("length").isEmpty()) length = Double.parseDouble(request.getParameter("length"));
        if (!request.getParameter("width").isEmpty())  width = Double.parseDouble(request.getParameter("width"));

        City cityS = City.builder()
                .name(citySender)
                .build();
        CityDao citySenderDao = new CityDao();
        City newCitySender = citySenderDao.findCity(cityS);

        City cityR = City.builder()
                        .name(cityReceiver)
                        .build();
        CityDao cityReceiverDao = new CityDao();
        City newCityReceiver = cityReceiverDao.findCity(cityR);

        switch (cargoType) {
            case "Document": {
                Cargo cargo = Cargo.builder()
                        .type(2)
                        .build();
                CargoDao cargoDao = new CargoDao();
                cargoDao.insert(cargo);
                break;
            }
            case "Cargo": {
                Cargo cargo = Cargo.builder()
                        .type(1)
                        .width(width)
                        .weight(weight)
                        .height(height)
                        .length(length)
                        .build();
                CargoDao cargoDao = new CargoDao();
                cargoDao.insert(cargo);
                break;
            }
            case "Parcel": {
                Cargo cargo = Cargo.builder()
                        .type(3)
                        .width(width)
                        .weight(weight)
                        .height(height)
                        .length(length)
                        .build();
                CargoDao cargoDao = new CargoDao();
                cargoDao.insert(cargo);
                break;
            }
        }


        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, arrivalDate);
        Timestamp ts1 = new Timestamp(cal.getTime().getTime());


        CargoDao cargoDao = new CargoDao();
        Cargo newCargo = cargoDao.selectLastCargo();

        Order order = Order.builder()
                .cargoId(newCargo.getId())
                .userId((Integer) session.getAttribute("currentUserId"))
                .description(description)
                .senderCityId(newCitySender.getId())
                .receiverCityId(newCityReceiver.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();
        OrderDao orderDao = new OrderDao();
        System.out.println(order.getDescription());
        orderDao.insert(order);
        return Path.PAGE_HOME;
    }
}
