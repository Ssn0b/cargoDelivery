package com.example.cargodelivery.controller.ErrorHandler;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static com.example.cargodelivery.controller.Path.PAGE_ERROR;
import static jakarta.servlet.RequestDispatcher.*;

@WebServlet(name = "ErrorHandlerServlet", value = "/AppExceptionHandler")
public class ErrorHandlerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("code", request.getAttribute(ERROR_STATUS_CODE));
        this.getServletContext().getRequestDispatcher( PAGE_ERROR ).forward( request , response );
    }
}
