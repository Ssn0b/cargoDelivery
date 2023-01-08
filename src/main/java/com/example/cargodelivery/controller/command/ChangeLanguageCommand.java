package com.example.cargodelivery.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.ArrayList;
@Log4j
public class ChangeLanguageCommand extends Command {
    private static final String LOCALE = "lang";
    private static final String SESSION_LOCALE = "lang";
    private static final String ENGLISH = "en";
    private static final String UKRAINE = "ua";
    private final ArrayList<String> supportedLanguages = new ArrayList<>();

    public ChangeLanguageCommand() {
        supportedLanguages.add(ENGLISH);
        supportedLanguages.add(UKRAINE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("started");
        String locale = request.getParameter(LOCALE);
        HttpSession session = request.getSession(false);
        if (locale != null) {
            if (!supportedLanguages.contains(locale)) {
                locale = ENGLISH;
            }
            session.setAttribute(SESSION_LOCALE, locale);
        }
        String url = request.getHeader("Referer");
        log.info("language changed");
        return "redirect:" + url.replace("http://localhost:8080/CargoDelivery/", "");
    }
}
