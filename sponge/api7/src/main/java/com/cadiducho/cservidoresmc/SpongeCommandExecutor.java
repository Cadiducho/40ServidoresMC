package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSConsoleSender;
import com.cadiducho.cservidoresmc.cmd.CSCommand;
import com.cadiducho.cservidoresmc.cmd.CSCommandManager;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpongeCommandExecutor implements CommandCallable {

    private final CSCommandManager commandManager;
    private final CSCommand command;

    public SpongeCommandExecutor(CSCommandManager commandManager, CSCommand command) {
        this.commandManager = commandManager;
        this.command = command;
    }

    @Override
    public CommandResult process(CommandSource source, String args) {
        CSCommandSender csCommandSender;
        if (source instanceof ConsoleSource || source instanceof CommandBlockSource) {
            csCommandSender = new CSConsoleSender(commandManager.getPlugin());
        } else {
            csCommandSender = new SpongeCommandSender(source, commandManager.getPlugin());
        }
        commandManager.executeCommand(csCommandSender, command.getName(), Arrays.asList(args.split(" ")));
        return CommandResult.success();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String args, Location<World> location) {
        CSCommandSender sender = new SpongeCommandSender(source, commandManager.getPlugin());
        return command.tabCompleteCommand(sender, Arrays.asList(args.split(" ")));
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return source.hasPermission(command.getPermission());
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of(command.getShortDescription()));
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        return Optional.of(Text.of(command.getHelp()));
    }

    @Override
    public Text getUsage(CommandSource source) {
        return Text.of(command.getHelp());
    }

}
