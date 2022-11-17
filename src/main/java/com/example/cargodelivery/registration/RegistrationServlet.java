package com.example.cargodelivery.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        String ucity = request.getParameter("city");
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargodelivery?useSSL=false","root","vladgo!2003");
            PreparedStatement pst = con.prepareStatement("insert into authorizedUser(name, email, password,telephoneNUmber,city) values (?,?,?,?,?)");
            pst.setString(1,uname);
            pst.setString(2,uemail);
            pst.setString(3,upwd);
            pst.setString(4,umobile);
            pst.setString(5,ucity);

            int rowCount = pst.executeUpdate();
            dispatcher =request.getRequestDispatcher("registration.jsp");
            if(rowCount>0){
                request.setAttribute("status","success");

            }else{
                request.setAttribute("status","failed");
            }
            dispatcher.forward(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
