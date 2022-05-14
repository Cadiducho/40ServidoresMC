package com.cadiducho.cservidoresmc.api;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CSConsoleSender implements CSCommandSender {

    private final CSPlugin plugin;

    @Override
    public String TAG() {
        return "";
    }

    @Override
    public void sendMessage(String string) {
        plugin.log(string);
    }

    @Override
    public void sendMessageWithTag(String string) {
        sendMessage(string);
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public boolean hasPermission(String string) {
        return true;
    }

    @Override
    public boolean isConsole() {
        return true;
    }
}
