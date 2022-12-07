package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.command.PageCommands.HomeCommand;
import com.example.cargodelivery.controller.command.PageCommands.LoginPageCommand;
import com.example.cargodelivery.controller.command.PageCommands.RegisterPageCommand;
import com.example.cargodelivery.controller.command.UserCommands.CountThePriceCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String,Command> commands = new TreeMap<String,Command>();

    static {
        commands.put("register",new RegisterCommand());
        commands.put("registerpage",new RegisterPageCommand());
        commands.put("home",new HomeCommand());
        commands.put("login",new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("loginpage",new LoginPageCommand());
        commands.put("pricePage", new CountThePriceCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}
