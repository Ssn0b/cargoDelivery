package com.example.cargodelivery.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", value = "/*")
public class LocaleFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale = request.getParameter("lang");
        String defaultLocale = "en";

        if(locale != null){
            session.setAttribute("lang", locale);
        }
        else if (session.getAttribute("lang")==null){
            session.setAttribute("lang",defaultLocale);
        }
        chain.doFilter(servletRequest,servletResponse);
    }
}
