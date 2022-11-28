package com.example.cargodelivery.controller.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String,Command> commands = new TreeMap<String,Command>();

    static {
        commands.put("register",new RegisterCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}
