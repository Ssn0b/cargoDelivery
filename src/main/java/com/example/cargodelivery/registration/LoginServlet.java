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
        String uEmail = request.getParameter("username");
        String uPwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargo_delivery?useSSL=false","root","vladgo!2003");
                PreparedStatement pst = con.prepareStatement("select * from user where email = ? and password = ?");
            pst.setString(1,uEmail);
            pst.setString(2,uPwd);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                session.setAttribute("name",rs.getString("username"));
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String operation = "" + request.getParameter("operation");
        try{
            if(operation.equalsIgnoreCase("logout")){
                HttpSession session=request.getSession(false);
                session.invalidate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ServletUtil.forward("login.jsp", request,response);
    }
}