package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import lombok.RequiredArgsConstructor;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.serializer.TextSerializers;

@RequiredArgsConstructor
public class SpongeCommandSender implements CSCommandSender {

    private final CommandSource commandSource;

    @Override
    public void sendMessage(String string) {
        commandSource.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(string));
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
