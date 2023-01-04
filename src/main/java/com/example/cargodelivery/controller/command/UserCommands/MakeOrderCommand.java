package com.example.cargodelivery.controller.command.UserCommands;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.controller.command.Command;
import com.example.cargodelivery.model.dao.CargoDao;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.OrderDao;
import com.example.cargodelivery.model.entity.Cargo;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.example.cargodelivery.controller.Validation.Validation.MakeOrderValidation;

public class MakeOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        CityDao cityDao = new CityDao();
        List<City> listCategory = cityDao.listSelect();
        request.setAttribute("listCategory", listCategory);

        String cargoType = request.getParameter("flexRadioDefault");
        String numReceiver = request.getParameter("numReceiver");
        String idenPackage = request.getParameter("idenPackage");
        String citySender = request.getParameter("sender");
        String cityReceiver = request.getParameter("receiver");
        String stringWeight = request.getParameter("weight");
        String stringWidth = request.getParameter("width");
        String stringHeight = request.getParameter("height");
        String stringLength = request.getParameter("length");

        if (MakeOrderValidation(request, cargoType, numReceiver, idenPackage, citySender, cityReceiver, stringWeight, stringWidth, stringHeight,
                stringLength)) {
            return Path.PAGE_PRICE;
        }

        double weight = 0, height = 0, length = 0, width = 0;
        if (!Objects.equals(cargoType, "Document")) {
            weight = Double.parseDouble(stringWeight);
            height = Double.parseDouble(stringHeight);
            length = Double.parseDouble(stringLength);
            width = Double.parseDouble(stringWidth);
        }

        int arrivalDate = Integer.parseInt((request.getParameter("arrivalDate")));

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

        double price = Double.parseDouble(request.getParameter("priceName"));

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
/*
        Timestamp ts1 = new Timestamp(cal.getTime().getTime());
*/

        CargoDao cargoDao = new CargoDao();
        Cargo newCargo = cargoDao.selectLastCargo();

        Order order = Order.builder()
                .cargoId(newCargo.getId())
                .userId((Integer) session.getAttribute("currentUserId"))
                .receiverNum(numReceiver)
                .description(idenPackage)
                .senderCityId(newCitySender.getId())
                .receiverCityId(newCityReceiver.getId())
                .orderStatusId(1)
                .dateOfRegister(ts)
                .daysToDeliver(arrivalDate)
                .price(price)
                .build();
        System.out.println(order.getDescription());
        OrderDao orderDao = new OrderDao();
        orderDao.insert(order);
        return Path.PAGE_HOME;
    }
}
