package com.example.cargodelivery.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public abstract class Command implements Serializable {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException;
}
