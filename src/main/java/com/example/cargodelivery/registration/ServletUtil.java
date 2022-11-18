package com.example.cargodelivery.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletUtil {
    static String Error = "Invalid data";
    static String SucessMessage = "Done";

    public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }


    public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect(page);
    }



    public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("exception", e);
        ServletUtil.forward("Error Message", request, response);
        e.printStackTrace();
    }




    public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(Error, msg);
    }
    public static String getErrorMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(Error);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(SucessMessage, msg);
    }
    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(SucessMessage);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
    public static String getParameter(String property, HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

}