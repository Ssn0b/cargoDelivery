package com.example.cargodelivery.controller.command.PageCommands;

import com.example.cargodelivery.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.cargodelivery.controller.Path.PAGE_REPLENISH_BALANCE;
@Log4j
public class ReplenishTheBalancePageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("ReplenishTheBalancePageCommand page loaded");
        return PAGE_REPLENISH_BALANCE;
    }
}
