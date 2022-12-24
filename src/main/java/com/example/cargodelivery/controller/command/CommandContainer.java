package com.example.cargodelivery.controller.command;

import com.example.cargodelivery.controller.command.ManagerCommands.ManagerActions.FormInvoiceForPaymentCommand;
import com.example.cargodelivery.controller.command.ManagerCommands.ManagerActions.RejectOrderCommand;
import com.example.cargodelivery.controller.command.ManagerCommands.ReceiveReportsCommand;
import com.example.cargodelivery.controller.command.PageCommands.*;
import com.example.cargodelivery.controller.command.UserCommands.*;

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
        commands.put("myOrders",new MakeOrderCommand());
        commands.put("myOrdersPage",new MyOrdersCommand());
        commands.put("pay",new PayCommand());
        commands.put("reportPage",new ReceiveReportsPage());
        commands.put("selectReports",new ReceiveReportsCommand());
        commands.put("invoiceForPayment",new FormInvoiceForPaymentCommand());
        commands.put("rejectOrder",new RejectOrderCommand());
        commands.put("profilePage",new ProfilePage());

        commands.put("replenishBalance",new ReplenishTheBalanceCommand());
        commands.put("payPage",new ReplenishTheBalancePageCommand());

        commands.put("payByBalance",new PayByBalanceCommand());
        commands.put("payByCard",new PayByCardCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}
