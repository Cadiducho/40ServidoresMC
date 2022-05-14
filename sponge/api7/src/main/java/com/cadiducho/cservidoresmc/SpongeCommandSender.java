package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import lombok.RequiredArgsConstructor;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.net.MalformedURLException;
import java.net.URL;

@RequiredArgsConstructor
public class SpongeCommandSender implements CSCommandSender {

    private final CommandSource commandSource;

    private final CSPlugin plugin;
    
    @Override
    public String TAG() {
        return plugin.getCSConfiguration().getTag();
    }

    @Override
    public void sendMessage(String string) {
        commandSource.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(string));
    }

    @Override
    public void sendNotVotedTodayLink(String message, String web) {
        try {
            commandSource.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(message).concat(
                    Text.builder(web).color(TextColors.GREEN).onClick(TextActions.openUrl(new URL(web))).toText()
            ));
        } catch (MalformedURLException ignored) {}
    }

    @Override
    public String getName() {
        return commandSource.getName();
    }

    @Override
    public boolean hasPermission(String string) {
        return commandSource.hasPermission(string);
    }
}
