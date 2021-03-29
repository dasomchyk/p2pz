package com.stormnet.serverservice.web.impl;

import com.stormnet.serverservice.web.common.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private Map<String, Command> commands = new HashMap<>();

    public static CommandFactory get() {
        return instance;
    }

    public CommandFactory() {
        commands.put("get-all-records", new GetAllRecordsCommand());
        commands.put("save-records", new SaveRecordCommand());
        commands.put("get-records", new GetRecordCommand());
        commands.put("delete-records", new DeleteRecordCommand());
        commands.put("update-records",  new UpdateRecordCommand());

        commands.put("get-client-by-login-and-password", new GetClientByLoginPasswordCommand());
        commands.put("get-all-clients", new GetAllClientsCommand());
        commands.put("save-client", new SaveClientCommand());
        commands.put("get-client", new GetClientCommand());
        commands.put("delete-client", new DeleteClientCommand());
        commands.put("update-client", new UpdateClientCommand());

        commands.put("get-master-by-login-and-password", new GetMasterByLoginPasswordCommand());
        commands.put("get-all-masters", new GetAllMastersCommand());
        commands.put("save-master", new SaveMasterCommand());
        commands.put("get-master", new GetMasterCommand());
        commands.put("delete-master", new DeleteMasterCommand());
        commands.put("update-master", new UpdateMasterCommand());

        commands.put("get-admin-by-login-and-password", new GetAdminByLoginPasswordCommand());
        commands.put("get-all-admins", new GetAllAdminsCommand());
        commands.put("save-admin", new SaveAdminCommand());
        commands.put("get-admin", new GetAdminCommand());
        commands.put("delete-admin", new DeleteAdminCommand());
        commands.put("update-admin", new UpdateAdminCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
