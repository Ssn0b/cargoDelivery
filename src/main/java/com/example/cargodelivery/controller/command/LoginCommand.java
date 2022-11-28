/*
package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.Path;
import com.example.cargodelivery.model.entity.User;
import com.example.cargodelivery.registration.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

public class LoginCommand extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String forward = Path.PAGE_LOGIN;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

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
                    dispatcher = request.getRequestDispatcher("/jsp/index.jsp");
                }else{
                    request.setAttribute("status","failed");
                    dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
                }
                dispatcher.forward(request,response);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }



            String operation = "" + request.getParameter("operation");
            try{
                if(operation.equalsIgnoreCase("logout")){
                    session=request.getSession(false);
                    session.invalidate();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ServletUtil.forward("/jsp/login.jsp", request,response);

}
*/
