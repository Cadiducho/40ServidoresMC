package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import lombok.Getter;

import java.util.List;

public abstract class CSCommand {

    @Getter private final String name;
    @Getter private final String permission;
    @Getter private final List<String> aliases;

    protected CSCommand(String name, String permission, List<String> aliases) {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
    }

    public abstract CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args);

    //ToDo: TabClomplete


    public boolean isAuthorized(CSCommandSender sender) {
        return this.permission == null || sender.hasPermission(this.permission);
    }

    public enum CommandResult {
        NO_PERMISSION,
        ONLY_PLAYER,
        SUCCESS,
        COOLDOWN,
        ERROR
    }
}
