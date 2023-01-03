package com.example.cargodelivery.controller.Validation;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.dao.CityDao;
import com.example.cargodelivery.model.dao.UserDao;
import com.example.cargodelivery.model.entity.City;
import com.example.cargodelivery.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.Objects;

import static com.example.cargodelivery.controller.Path.PAGE_CHANGE_INFO;
import static com.example.cargodelivery.controller.Path.PAGE_REGISTER;

public class Validation {
    public static boolean ChangeInfoValidation(HttpServletRequest request, String changeName, String changeLastName,
                                               String changeEmail, String changePhone, String changePassword,
                                               String currentPass, User user) throws SQLException {
        UserDao userDao = new UserDao();
        if (!(changeName.isEmpty()) && (!changeName.matches("^[A-Za-z]+$"))){
            request.setAttribute("message", "Please write your first name in correct way");
            return true;
        }
        if (!(changeLastName.isEmpty()) && (!changeLastName.matches("^[A-Za-z]+$"))){
            request.setAttribute("message", "Please write your last name in correct way");
            return true;
        }
        if (!(changeEmail.isEmpty()) && (!changeEmail.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))){
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        if (userDao.findUserByEmail(changeEmail) != null) {
            request.setAttribute("message", "User with same email already exists");
            return true;
        }
        if (!(changePhone.isEmpty()) && changePhone.length()!=10){
            request.setAttribute("message", "Please write your phone number in correct way");
            return true;
        }
        if (userDao.findUserByPhoneNumber(changePhone) != null) {
            request.setAttribute("message", "User with same telephone number already exists");
            return true;
        }
        if (!(changePassword.isEmpty()) && (changePassword.length()<4)
                || (changePassword.length()>16)){
            request.setAttribute("message", "Your new password must be 4 to 16 characters");
            return true;
        }
        if (!(currentPass.isEmpty()) && !Objects.equals(user.getPassword(), currentPass)){
            request.setAttribute("message", "Invalid password");
            return true;
        }
        if (!Objects.equals(currentPass, user.getPassword())) {
            request.setAttribute("message", "Passwords do not match");
            return true;
        }
        return false;
    }
    public static boolean ReplenishTheBalanceValidation(HttpServletRequest request, String deposit, String name, String email,
                                                        String address, String city, String state, String zip, String nameOnCard,
                                                        String card, String month,String year, String cvv) {
        if (deposit.isEmpty() || Double.parseDouble(deposit) < 1
                || Double.parseDouble(deposit) > 1000){
            request.setAttribute("message", "You did not enter the deposit amount or you entered a negative amount or at least you enter too large amount");
            return true;
        }
        if (name.isEmpty() || !name.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$")){
            request.setAttribute("message", "Please write your name in correct way");
            return true;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        if (address.isEmpty()){
            request.setAttribute("message", "Please write your address in correct way");
            return true;
        }
        if (city.isEmpty() || !city.matches("^[a-zA-Z]{3,}+$")){
            request.setAttribute("message", "Please write your city in correct way");
            return true;
        }
        if (state.isEmpty() || !state.matches("^[a-zA-Z]{3,}+$")){
            request.setAttribute("message", "Please write your country state in correct way");
            return true;
        }
        if (zip.isEmpty() || !zip.matches("^\\d{5}$")){
            request.setAttribute("message", "Please write your zip code in correct way");
            return true;
        }
        if (nameOnCard.isEmpty() || !nameOnCard.matches("^[a-zA-Z\\.\\s]{3,}+$")){
            request.setAttribute("message", "Please write your name on card in correct way");
            return true;
        }
        if (card.isEmpty() || !card.matches("^\\d{15}$")){
            request.setAttribute("message", "Please write your card number in correct way");
            return true;
        }
        if (month.isEmpty() || !month.matches("^[A-Za-z]+$")){
            request.setAttribute("message", "Please write your exp month in correct way");
            return true;
        }
        if (year.isEmpty() || !year.matches("^[2][0][2]\\d{1}$")){
            request.setAttribute("message", "Please write your exp year in correct way");
            return true;
        }
        if (cvv.isEmpty() || !cvv.matches("^\\d{3}$")){
            request.setAttribute("message", "Please write your cvv in correct way");
            return true;
        }
        return false;
    }

    public static boolean PayValidation(HttpServletRequest request, String name, String email,
                                                        String address, String city, String state, String zip, String nameOnCard,
                                                        String card, String month,String year, String cvv) {

        if (name.isEmpty() || !name.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$")){
            request.setAttribute("message", "Please write your name in correct way");
            return true;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        if (address.isEmpty()){
            request.setAttribute("message", "Please write your address in correct way");
            return true;
        }
        if (city.isEmpty() || !city.matches("^[a-zA-Z]{3,}+$")){
            request.setAttribute("message", "Please write your city in correct way");
            return true;
        }
        if (state.isEmpty() || !state.matches("^[a-zA-Z]{3,}+$")){
            request.setAttribute("message", "Please write your country state in correct way");
            return true;
        }
        if (zip.isEmpty() || !zip.matches("^\\d{5}$")){
            request.setAttribute("message", "Please write your zip code in correct way");
            return true;
        }
        if (nameOnCard.isEmpty() || !nameOnCard.matches("^[a-zA-Z\\.\\s]{3,}+$")){
            request.setAttribute("message", "Please write your name on card in correct way");
            return true;
        }
        if (card.isEmpty() || !card.matches("^\\d{15}$")){
            request.setAttribute("message", "Please write your card number in correct way");
            return true;
        }
        if (month.isEmpty() || !month.matches("^[A-Za-z]+$")){
            request.setAttribute("message", "Please write your exp month in correct way");
            return true;
        }
        if (year.isEmpty() || !year.matches("^[2][0][2]\\d{1}$")){
            request.setAttribute("message", "Please write your exp year in correct way");
            return true;
        }
        if (cvv.isEmpty() || !cvv.matches("^\\d{3}$")){
            request.setAttribute("message", "Please write your cvv in correct way");
            return true;
        }
        return false;
    }

    public static boolean LoginValidation(HttpServletRequest request, String email) {
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        return false;
    }

    public static boolean RegisterValidation(HttpServletRequest request,String name,String lastName, String email,String password,
                                             String confirmPassword,String number, User user) throws SQLException {
        UserDao userDao = new UserDao();
        if (name.isEmpty() || !name.matches("^[A-Za-z]+$")){
            request.setAttribute("message", "Please write your first name in correct way");
            return true;
        }
        if (lastName.isEmpty() || !lastName.matches("^[A-Za-z]+$")){
            request.setAttribute("message", "Please write your last name in correct way");
            return true;
        }
        if (name.length() > 35 || lastName.length() > 35) {
            request.setAttribute("message", "Your first name or last name is too long");
            return true;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            request.setAttribute("message", "Please write your email in correct way");
            return true;
        }
        if (password.length() < 4 || password.length() > 16){
            request.setAttribute("message", "Your password must have at least 4 length and not be larger than 16");
            return true;
        }
        if (!password.equals(confirmPassword)){
            request.setAttribute("message", "Passwords don't match");
            return true;
        }
        if (!(number.isEmpty()) && number.length()!=10){
            request.setAttribute("message", "Please write your phone number in correct way");
            return true;
        }
        if (userDao.findUserByEmail(user.getEmail()) != null) {
            request.setAttribute("message", "User with same email already exists");
            return true;
        }
        if (userDao.findUserByPhoneNumber(user.getNumber()) != null) {
            request.setAttribute("message", "User with same telephone number already exists");
            return true;
        }
        return false;
    }
    public static boolean MakeOrderValidation(HttpServletRequest request,String type,String receiverName, String idenPackage,
                                              String citySender,String cityReceiver,String weight, String width, String height,
                                              String length) throws SQLException {
        if (type == null){
            request.setAttribute("message", "Please choose cargo type");
            return true;
        }
        if (receiverName.isEmpty() || !receiverName.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$") ||
                idenPackage.isEmpty() || !idenPackage.matches("^[A-Za-z]+$")) {
            request.setAttribute("message", "Please specify receiver and identify package correctly");
            return true;
        }
        if (!(Objects.equals(type, "Document"))) {
            if (weight.isEmpty() || height.isEmpty() || width.isEmpty() || length.isEmpty()) {
                request.setAttribute("message", "Please input right parameters");
                return true;
            }

            double Dweight = Double.parseDouble(weight);
            double Dheight = Double.parseDouble(height);
            double Dlength = Double.parseDouble(length);
            double Dwidth = Double.parseDouble(width);

            if (Objects.equals(type, "Parcel")) {
                if (Dweight < 0.5 || Dweight > 25) {
                    request.setAttribute("message", "Please input right weight (possible weight is between 0.5 kg and 25 kg)");
                    return true;
                } else if (Dheight < 2 || Dheight > 500) {
                    request.setAttribute("message", "Please input right height (possible height is between 2 cm and 500 cm)");
                    return true;
                }else if (Dlength < 2 || Dlength > 500) {
                    request.setAttribute("message", "Please input right length (possible length is between 2 cm and 500 cm)");
                    return true;
                }else if (Dwidth < 2 || Dwidth > 500) {
                    request.setAttribute("message", "Please input right width (possible width is between 2 cm and 500 cm)");
                    return true;
                }
            } else if (Objects.equals(type, "Cargo")) {
                if (Dweight < 25 || Dweight > 200) {
                    request.setAttribute("message", "Please input right weight (possible weight is between 25 kg and 200 kg)");
                    return true;
                } else if (Dheight < 2 || Dheight > 1000) {
                    request.setAttribute("message", "Please input right height (possible height is between 2 cm and 1000 cm)");
                    return true;
                }else if (Dlength < 2 || Dlength > 1000) {
                    request.setAttribute("message", "Please input right length (possible length is between 2 cm and 1000 cm)");
                    return true;
                }else if (Dwidth < 2 || Dwidth > 1000) {
                    request.setAttribute("message", "Please input right width (possible width is between 2 cm and 1000 cm)");
                    return true;
                }
            }
        }

        if (citySender.isEmpty() || cityReceiver.isEmpty()) {
            request.setAttribute("message", "Please input cities");
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
            request.setAttribute("message", "Unfortunately, we do not work in this city, please try another");
            return true;
        }

        return false;
    }

}
