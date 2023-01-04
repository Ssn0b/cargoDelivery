package com.example.cargodelivery.controller.Validation;

import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class Validation {
    public static String localeText(String text, Object locale) {
        ResourceBundle res = ResourceBundle.getBundle("locale_" + locale);
        return res.getString(text);
    }

    public static boolean ChangeInfoValidation(HttpServletRequest request, String changeName, String changeLastName,
                                               String changeEmail, String changePhone, String changePassword,
                                               String currentPass, User user) throws SQLException {
        UserDao userDao = new UserDao();

        if (!(changeName.isEmpty()) && ((!changeName.matches("\"^[a-zA-Z\\\\u0430-\\\\u044f\\\\u0410-\\\\u042f\\\\u0456\\\\u0457\\\\u0406\\\\u0407\\\\s]+\"")))) {
            request.setAttribute("message", localeText("invalid.registerName", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changeLastName.isEmpty()) && (!changeLastName.matches("\"^[a-zA-Z\\\\u0430-\\\\u044f\\\\u0410-\\\\u042f\\\\u0456\\\\u0457\\\\u0406\\\\u0407\\\\s]+\""))) {
            request.setAttribute("message", localeText("invalid.registerLastName", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changeEmail.isEmpty()) && (!changeEmail.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))) {
            request.setAttribute("message", localeText("invalid.registerEmail", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changeEmail.isEmpty()) && userDao.findUserByEmail(changeEmail) != null) {
            request.setAttribute("message", localeText("invalid.registerSameEmail", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changePhone.isEmpty()) && changePhone.length() != 10) {
            request.setAttribute("message", localeText("invalid.registerNumber", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changePhone.isEmpty()) && userDao.findUserByPhoneNumber(changePhone) != null) {
            request.setAttribute("message", localeText("invalid.registerSameNumber", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(changePassword.isEmpty()) && (changePassword.length() < 4)
                || (changePassword.length() > 16)) {
            request.setAttribute("message", localeText("invalid.changeProfilePassSize", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(currentPass.isEmpty()) && !BCrypt.checkpw(currentPass, user.getPassword())) {
            request.setAttribute("message", localeText("invalid.changePassPass", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!BCrypt.checkpw(currentPass, user.getPassword())) {
            request.setAttribute("message", localeText("invalid.registerPasswordDontMatch", request.getSession().getAttribute("lang")));
            return true;
        }
        return false;
    }

    public static boolean ReplenishTheBalanceValidation(HttpServletRequest request, String deposit, String name, String email,
                                                        String address, String city, String state, String zip, String nameOnCard,
                                                        String card, String month, String year, String cvv) {
        if (deposit == null || deposit.isEmpty() || Double.parseDouble(deposit) < 1
                || Double.parseDouble(deposit) > 1000) {
            request.setAttribute("message", localeText("invalid.cardDeposit", request.getSession().getAttribute("lang")));
            return true;
        }
        return PayValidation(request, name, email, address, city, state,  zip, nameOnCard, card, month, year, cvv);

    }

    public static boolean PayValidation(HttpServletRequest request, String name, String email,
                                        String address, String city, String state, String zip, String nameOnCard,
                                        String card, String month, String year, String cvv) {

        if (name == null || name.isEmpty() || !name.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$")) {
            request.setAttribute("message", localeText("invalid.cardName", request.getSession().getAttribute("lang")));
            return true;
        }
        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            request.setAttribute("message", localeText("invalid.cardEmail", request.getSession().getAttribute("lang")));
            return true;
        }
        if (address == null || address.isEmpty()) {
            request.setAttribute("message", localeText("invalid.cardAddress", request.getSession().getAttribute("lang")));
            return true;
        }
        if (city == null || city.isEmpty() || !city.matches("^[a-zA-Z]{3,}+$")) {
            request.setAttribute("message", localeText("invalid.cardCity", request.getSession().getAttribute("lang")));
            return true;
        }
        if (state == null || state.isEmpty() || !state.matches("^[a-zA-Z]{3,}+$")) {
            request.setAttribute("message", localeText("invalid.cardState", request.getSession().getAttribute("lang")));
            return true;
        }
        if (zip == null || zip.isEmpty() || !zip.matches("^\\d{5}$")) {
            request.setAttribute("message", localeText("invalid.cardZIP", request.getSession().getAttribute("lang")));
            return true;
        }
        if (nameOnCard == null || nameOnCard.isEmpty() || !nameOnCard.matches("^[a-zA-Z\\.\\s]{3,}+$")) {
            request.setAttribute("message", localeText("invalid.cardNameOnCard", request.getSession().getAttribute("lang")));
            return true;
        }
        if (card == null || card.isEmpty() || !card.matches("^\\d{15}$")) {
            request.setAttribute("message", localeText("invalid.cardNumber", request.getSession().getAttribute("lang")));
            return true;
        }
        if (month == null || month.isEmpty() || !month.matches("^[A-Za-z]+$")) {
            request.setAttribute("message", localeText("invalid.cardMonth", request.getSession().getAttribute("lang")));
            return true;
        }
        if (year == null || year.isEmpty() || !year.matches("^[2][0][2]\\d{1}$")) {
            request.setAttribute("message", localeText("invalid.cardYear", request.getSession().getAttribute("lang")));
            return true;
        }
        if (cvv == null || cvv.isEmpty() || !cvv.matches("^\\d{3}$")) {
            request.setAttribute("message", localeText("invalid.cardCvv", request.getSession().getAttribute("lang")));
            return true;
        }
        return false;
    }

    public static boolean LoginValidation(HttpServletRequest request, String email) {
        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            request.setAttribute("message", localeText("invalid.login", request.getSession().getAttribute("lang")));
            return true;
        }
        return false;
    }

    public static boolean RegisterValidation(HttpServletRequest request, String name, String lastName, String email, String password,
                                             String confirmPassword, String number, User user) throws SQLException {
        UserDao userDao = new UserDao();
        if (name == null || name.isEmpty() || !name.matches("^[a-zA-Za-åa-ö-w-я]+$")) {
            request.setAttribute("message", localeText("invalid.registerName", request.getSession().getAttribute("lang")));
            return true;
        }
        if (lastName == null || lastName.isEmpty() || !lastName.matches("^[a-zA-Za-åa-ö-w-я]+$")) {
            request.setAttribute("message", localeText("invalid.registerLastName", request.getSession().getAttribute("lang")));
            return true;
        }
        if (name.length() > 200 || lastName.length() > 200) {
            request.setAttribute("message", localeText("invalid.nameSize", request.getSession().getAttribute("lang")));
            return true;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            request.setAttribute("message", localeText("invalid.registerEmail", request.getSession().getAttribute("lang")));
            return true;
        }
        if (password.length() < 4 || password.length() > 16) {
            request.setAttribute("message", localeText("invalid.registerPasswordSize", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", localeText("invalid.registerPasswordDontMatch", request.getSession().getAttribute("lang")));
            return true;
        }
        if (number.length() != 10) {
            request.setAttribute("message", localeText("invalid.registerNumber", request.getSession().getAttribute("lang")));
            return true;
        }
        if (userDao.findUserByEmail(user.getEmail()) != null) {
            request.setAttribute("message", localeText("invalid.registerSameEmail", request.getSession().getAttribute("lang")));
            return true;
        }
        if (userDao.findUserByPhoneNumber(user.getNumber()) != null) {
            request.setAttribute("message", localeText("invalid.registerSameNumber", request.getSession().getAttribute("lang")));
            return true;
        }
        return false;
    }

    public static boolean MakeOrderValidation(HttpServletRequest request, String type, String receiverNum, String idenPackage,
                                              String citySender, String cityReceiver, String weight, String width, String height,
                                              String length) throws SQLException {
        if (type == null) {
            request.setAttribute("message", localeText("invalid.makeOrderChooseCargo", request.getSession().getAttribute("lang")));
            return true;
        }
        if (receiverNum.isEmpty() || !receiverNum.matches("^[+0-9]{10,13}") ||
                idenPackage.isEmpty() || !idenPackage.matches("^[a-zA-Z\\u0430-\\u044f\\u0410-\\u042f\\u0456\\u0457\\u0406\\u0407\\s]+")) {
            request.setAttribute("message", localeText("invalid.makeOrderSpecify", request.getSession().getAttribute("lang")));
            return true;
        }
        if (!(Objects.equals(type, "Document"))) {
            if (weight.isEmpty() || height.isEmpty() || width.isEmpty() || length.isEmpty()) {
                request.setAttribute("message", localeText("invalid.makeOrderRightParameters", request.getSession().getAttribute("lang")));
                return true;
            }

            double Dweight = Double.parseDouble(weight);
            double Dheight = Double.parseDouble(height);
            double Dlength = Double.parseDouble(length);
            double Dwidth = Double.parseDouble(width);

            if (Objects.equals(type, "Parcel")) {
                if (Dweight < 0.5 || Dweight > 25) {
                    request.setAttribute("message", localeText("invalid.makeOrderParcelWeight", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dheight < 2 || Dheight > 500) {
                    request.setAttribute("message", localeText("invalid.makeOrderParcelHeight", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dlength < 2 || Dlength > 500) {
                    request.setAttribute("message", localeText("invalid.makeOrderParcelLength", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dwidth < 2 || Dwidth > 500) {
                    request.setAttribute("message", localeText("invalid.makeOrderParcelWidth", request.getSession().getAttribute("lang")));
                    return true;
                }
            } else if (Objects.equals(type, "Cargo")) {
                if (Dweight < 25 || Dweight > 200) {
                    request.setAttribute("message", localeText("invalid.makeOrderCargoWeight", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dheight < 2 || Dheight > 1000) {
                    request.setAttribute("message", localeText("invalid.makeOrderCargoHeight", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dlength < 2 || Dlength > 1000) {
                    request.setAttribute("message", localeText("invalid.makeOrderCargoLength", request.getSession().getAttribute("lang")));
                    return true;
                } else if (Dwidth < 2 || Dwidth > 1000) {
                    request.setAttribute("message", localeText("invalid.makeOrderCargoWidth", request.getSession().getAttribute("lang")));
                    return true;
                }
            }
        }

        if (citySender.isEmpty() || cityReceiver.isEmpty()) {
            request.setAttribute("message", localeText("invalid.makeOrderInputCities", request.getSession().getAttribute("lang")));
            return true;
        }

        City cityS = City.builder()
                .name(citySender)
                .build();
        CityDao cityReceiverDao = new CityDao();
        City newCityS = cityReceiverDao.findCity(cityS);

        City cityR = City.builder()
                .name(citySender)
                .build();
        City newCityR = cityReceiverDao.findCity(cityR);

        if (newCityS == null || newCityR == null) {
            request.setAttribute("message", localeText("invalid.makeOrderNullCities", request.getSession().getAttribute("lang")));
            return true;
        }

        return false;
    }
}
