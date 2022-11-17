package com.example.cargodelivery.registration;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uemail = request.getParameter("username");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargodelivery?useSSL=false","root","vladgo!2003");
                PreparedStatement pst = con.prepareStatement("select * from authorizedUser where email = ? and password = ?");
            pst.setString(1,uemail);
            pst.setString(2,upwd);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                session.setAttribute("name",rs.getString("name"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            }else{
                request.setAttribute("status","failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}