package com.example.cargodelivery.registration;

import com.example.cargodelivery.db.DBUtil;
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
        String uName = request.getParameter("name");
        //System.out.println(uName);
        String uEmail = request.getParameter("email");
        String uPwd = request.getParameter("pass");
        String uMobile = request.getParameter("contact");
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DBUtil.getDataSource().getConnection();
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargo_delivery?useSSL=false","root","vladgo!2003");
            PreparedStatement pst = con.prepareStatement("insert into user(username, email, password,roleId,telNumber) values (?,?,?,?,?)");
            pst.setString(1, uName);
            pst.setString(2, uEmail);
            pst.setString(3, uPwd);
            pst.setInt(4, 1);
            pst.setString(5, uMobile);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("/jsp/registration.jsp");
            if (rowCount > 0) {
                request.setAttribute("status", "success");

            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
       /* } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //  try {
            //con.close();
            // } catch (SQLException e) {
            //   throw new RuntimeException(e);
            //}
            //}
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ServletUtil.forward("/jsp/registration.jsp",request,response);
       // doPost(request,response);
    }
}
