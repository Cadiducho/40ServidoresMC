package com.cadiducho.cservidoresmc.api;

public interface CSCommandSender {

    void sendMessage(String string);

    String getName();

    default boolean isConsole() {
        return false;
    }

    boolean hasPermission(String string);

}
