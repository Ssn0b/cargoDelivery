package com.example.cargodelivery.controller.myTags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class MessageTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException{
        JspWriter out = getJspContext().getOut();
        out.print("<ul class=\"nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">\n" +
                "                <li><a href=\"controller?action=changeLanguage&lang=en\" class=\"nav-link px-2 text-white\">en</a></li>\n" +
                "                <li><a href=\"controller?action=changeLanguage&lang=ua\" class=\"nav-link px-2 text-white\">ua</a></li>\n" +
                "            </ul>");
    }
}
